package Lv1.체육복;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JY {

    //점심시간에 도둑이 들어서 도난당했다
    // 여벌의 체육복 있는애들이 빌려준댄다
    // 학생들의 번호는 체격순으로 매겨져있다.
    // 바로 앞번호나 뒷번호 학생에게만 빌려줄수 있다.
    // 체육복이없으면 수업을 들을수 없다 최대한 많은 학생이 수업을 들어야한다.
    // 전체 학생은 n 도난당한 학생들의 배열은 lost 여벌친구들은 reserve
    // 최댓값 return
    // 학생 2명이상 30명 이하
    // 도난 1명이상 n명 이하
    // 여벌만 빌려주는거가능
    // 여벌친구중 도난당했으면 걘 못빌려줌


    //n	 lost	reserve  	return
    //5	[2, 4]	[1, 3, 5]	5
    //5	[2, 4]	[3]	        4
    //3	[3]	    [1]     	2
    public static void main(String[] ags) {
        Solution solution = new Solution();
        int case1 = 5;
        int[] case1lost = {2,4};
        int[] case1reserve = {1,3,5};
        int case2 = 5;
        int[] case2lost = {2,4};
        int[] case2reserve = {3};

        System.out.println(solution.solution(case2,case2lost,case2reserve));
    }

    // 1. School
    // 필드 : 학생들
    // 함수 : 옷을더가져온학생,옷을도난당한학생,학생,앞학생,뒤학생
    // 2. 나눔계산기
    // 필드 : 학교,최대학생수
    // 함수 : 그리드구하기,나눔,왼쪽학생확인,오른쪽학생확인,옷을받거나줄수있는boolean계산
    // 3. 학생
    // 필드 : 학번, 옷
    // 함수 : 받거나 주거나 , 옷을더가져오거나 , 옷을 도난당하거나
    // 4. EnumContainer
    // 각 에트리뷰트의 Enum을 가지고있음

    public static class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            School school = new School(n)
                    .reserveStudent(reserve)
                    .lostStudent(lost);
            CalcShare calc = new CalcShare(school);
            calc.greed();
            return (int)school.students
                    .entrySet()
                    .stream()
                    .filter(fl->fl.getValue()
                            .clothers!=0)
                    .count();
        }
        public class School{
            public HashMap<Integer,Student> students = new HashMap<>();
            public School(int student){
               students= (HashMap<Integer, Student>) IntStream.range(1, student+1)
                       .mapToObj(Student::new)
                       .collect(Collectors.<Student,Integer,Student>toMap(Student::getNumber,Student::getThis));
                       // forEach(fe-> students.put(fe.number,fe));
            }
            public School reserveStudent(final int[] reserve){
                Arrays.stream(reserve)
                        .filter(fl->students.containsKey(fl))
                        .forEach(fe-> students.get(fe)
                                .reserve(true));
                return this;
            }
            public School lostStudent(final int[] lost){
                Arrays.stream(lost)
                        .filter(fl->students.containsKey(fl))
                        .forEach(fe->students.get(fe)
                                .lost(true));
                return this;
            }
            public Student getBeforeStudent(final Student student){
                return students.getOrDefault(student.number-1,student);
            }
            public Student getAfterStudent(final Student student){
                return students.getOrDefault(student.number+1,student);
            }
            public Student getStudent(final int num){
                return students.getOrDefault(num,new Student(0));
            }
        }
        public class CalcShare {
            private final School school;
            private final int maxStudentCount;
            public CalcShare(School school){
                this.school = school;
                this.maxStudentCount = this.school.students.size();
            }
            public void greed() {
                IntStream.range(1,maxStudentCount+1)
                        .forEach(fe->shares(fe));
            }
            private void shares(final int num){
                Student student = school.getStudent(num);
                if(student.number==0) return;
                if(onLeft(student)) return;
                onRight(student);
            }
            private boolean onLeft(final Student student){
                if(student.number==1) return false;
                if(isShare(student)&&isGive(school.getBeforeStudent(student))) {
                    student.giveto();
                    school.getBeforeStudent(student).togive();
                    return true;
                }
                return false;
            }
            private boolean onRight(final Student student){
                if(student.number==maxStudentCount) return false;
                if(isShare(student)&& isGive(school.getAfterStudent(student))) {
                    student.giveto();
                    school.getAfterStudent(student).togive();
                    return true;
                }
                return false;
            }

            private boolean isGive(final Student student){
                if (isHaveClothers(student)) return false;
                return true;
            }
            private boolean isShare(final Student student){
                if (isNotHaveMoreClothers(student)) return false;
                return true;
            }
            private boolean isHaveClothers(final Student student){
                return student.clothers > 0;
            }
            private boolean isHaveMoreClothers(final Student student){
                return student.clothers == 2;
            }
            private boolean isNotHaveMoreClothers(final Student student){
                return !isHaveMoreClothers(student);
            }
        }

        public class Student {
            private final int number;
            private int clothers;
            public Student(final int number){
                this.number = number;
                this.clothers = 1;
            }
            public int getNumber(){return number;}
            public Student getThis(){return this;}
            public Student reserve(final boolean bool){
                if(bool)clothers+=1; return this;
            }
            public Student lost(final boolean bool){
                if(bool)clothers-=1; return this;
            }
            public void giveto(){
                clothers-=1;
            }
            public void togive(){
                clothers+=1;
            }
        }
    }
}
