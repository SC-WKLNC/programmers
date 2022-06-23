package Lv1.체육복;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * 파라미터
 * n = 전체 학생수
 * lost = 체육복을 가지고오지않은 학생 번호 배열
 * reserve = 여분의 체육복을 가지고온 학생 번호 배열
 *
 * 여분의 체육복을 가지고온 학생은 자신의 번호 +- 1 의 숫자의 학생에게만 체육복을 빌려줄수있다.
 *
 * 객체
 * 교실 - 학생들이 있다.
 * 학생 - 자신의 번호와 체육복을 가지고있다.
 */
public class JH {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = new int[]{2,4};
        int[] reserve = new int[]{3};

        JH jh = new JH();
        jh.solution(n,lost,reserve);

    }

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;


        ClassRoom classRoom = new ClassRoom();
        IntStream.range(1, n + 1)
                    .forEach(item-> classRoom.addStudent(item));


        Arrays.stream(reserve).forEach(target ->classRoom.reserveSportsDressStudent(target));
        Arrays.stream(lost).forEach(target ->classRoom.loseSportsDressStudent(target));

        classRoom.sportsDressDivision();// 빌려라
        return classRoom.getSportsDressStudentCount();
    }

    class ClassRoom{
        final List<Student> students= new ArrayList<>();

        public void addStudent(int studentNumber){
            this.students.add(new Student(this,studentNumber));
        }
        public void loseSportsDressStudent(int studentNumber){
            for (Student student : students) {
                if(student.getSequence() == studentNumber){
                    student.loseSportsDress();
                    break;
                }
            }
        }
        public void reserveSportsDressStudent(int studentNumber){
            for (Student student : students) {
                if(student.getSequence() == studentNumber){
                    student.reserveSportsDress();
                    break;
                }
            }
        }

        public void sportsDressDivision(){
            for (Student student : students) {
                if(! student.isSportsDress()){ //체육복이 없는 학생인 경우
                    student.sportsDressDivision(); //빌려라

                }
            }
        }
        public int getStudentSize() {
            return students.size();
        }
        public Optional<Student> getStudent(int index) {
            Student student = students.get(index - 1);
            return Optional.ofNullable(student);
        }

        //채육복을 가진 학생 수 반환
        public int getSportsDressStudentCount(){
            return (int)students.stream().filter(Student::isSportsDress).count();
        }
    }

    class Student{
        public final ClassRoom classRoom;
        public final int sequence;
        final List<Integer> sportsDressList = new ArrayList<>();
        public Student(ClassRoom classRoom, int sequence) {
            this.classRoom = classRoom;
            this.sequence = sequence;
            this.sportsDressList.add(sequence);
        }

        public int getSequence() {
            return sequence;
        }

        public boolean isSportsDressDivision() { //빌려줄수 있는가
            return sportsDressList.size() >=2 ? true : false;
        }
        public void sportsDressDivision(){ //빌려줄 친구를 찾는다

            Optional<Student> student = this.classRoom.getStudent(this.sequence - 1);

            if(student.isPresent()&& student.get().isSportsDressDivision()) {
                student.get().loseSportsDress(); //빌려주는 친구에서 체육복을 빼고
                this.reserveSportsDress(); // 자신에게 추가
                return;
            }


            student = this.classRoom.getStudent(this.sequence + 1);
            if (student.isPresent()&& student.get().isSportsDressDivision()) {
                student.get().loseSportsDress();
                this.reserveSportsDress();
                return;
            }

        }
        public boolean isSportsDress() {
            return sportsDressList.size() >=1 ? true : false;
        }

        public void loseSportsDress(){
            this.sportsDressList.remove(0);

        }
        public void reserveSportsDress(){
            this.sportsDressList.add(sequence);
        }

    }

}
