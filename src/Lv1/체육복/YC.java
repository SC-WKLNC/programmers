package Lv1.체육복;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class YC {

    // start 2022/05/28 13:50
    // end 15:30

    // 학생
    // -> 체육복 갯수, 번호, 체격

    // 여벌 체육복을 갖고 있는 학생이 도난당한 것은 1개만 도난 당한것.
    // 따라서 빌려줄 수 없다.

    // 최대한 많이 분배

    // 탐욕 알고리즘 적용
    // 이 경우 지역적 최적해를 구했을때 전체적으로도 최적의 해 를 구할 수 있다.
    // 선택 절차 -> 적절성 검사 -> 해답 검사

    // 객체 모델링
    // : 학생
    // -> 필드 : 번호, 도난 당함 여부, 가진 체육복 갯수, 빌림 여부, 빌려준 학생리스트, 빌린 학생 리스트
    // -> 함수 : 도난 당함, 체육복이 필요한지 여부 확인, 체육복 빌리기, 체육복 빌려주기

    public static void main(String[] args) {
        YC yc = new YC();
        int solution = yc.solution(5, new int[]{2, 4}, new int[]{1, 3, 5});
        System.out.println(solution);
    }

    public int solution(int n, int[] lost, int[] reserve) {
        final Class clazz = new Class(n);
        clazz.reserve(reserve);
        clazz.stolen(lost);
        clazz.tryLent();
        return clazz.countOfAvailableLearn();
    }

    public static class Class {
        private final List<Student> students;
        public Class(final int n) {
            this.students = IntStream.range(1, n + 1)
                .mapToObj(Student::new)
                .collect(Collectors.toList());
        }

        public void reserve(final int[] reserve) {
            students.stream()
                .filter(
                    student -> Arrays.stream(reserve)
                        .anyMatch(student::isEqualsNumber)
                )
                .forEach(Student::addGymSuit);
        }

        public void stolen(final int[] lost) {
            students.stream()
                .filter(
                    student -> Arrays.stream(lost)
                        .anyMatch(student::isEqualsNumber)
                )
                .forEach(Student::beStolen);
        }

        public int countOfAvailableLearn() {
            return (int) this.students.stream()
                .filter(Student::isAvailableLearn)
                .count();
        }

        public void tryLent() {
            this.students.forEach(this::lent);
        }

        // 빌려주기
        private void lent(final Student student) {
            searchTargetStudent(student)
                .ifPresent(student::lentGymSuit);
        }

        // 빌려줄 대상 찾기 (앞, 뒤)
        private Optional<Student> searchTargetStudent(final Student student) {
            if(student.isAvailableLent()) {
                int number = student.getNumber();
                final Optional<Student> beforeStudent = getStudent(number - 1);
                if(beforeStudent.isPresent())
                    return beforeStudent;
                final Optional<Student> afterStudent = getStudent(number + 1);
                if(afterStudent.isPresent())
                    return afterStudent;
            }
            return Optional.empty();
        }

        private Optional<Student> getStudent(final int number) {
            int index = number - 1;
            if(index >= 0 && index < students.size()) {
                final Student student = students.get(index);
                if(student.isRequired())
                    return Optional.of(student);
            }
            return Optional.empty();
        }

    }

    public static class Student {

        private final int number;
        private int gymSuitCount = 1;
        private final List<Student> listOfStudentLent = new ArrayList<>();
        private final List<Student> listOfStudentBorrow = new ArrayList<>();

        public Student(final int number) {
            this.number = number;
        }

        public void addGymSuit() {
            this.gymSuitCount++;
        }

        public void beStolen() {
            this.gymSuitCount--;
        }

        public boolean isRequired() {
            return this.gymSuitCount < 1;
        }

        public boolean isAvailableLent() {
            return this.gymSuitCount > 1;
        }

        public boolean isAvailableLearn() {
            return this.gymSuitCount > 0;
        }

        private void borrowGymSuit(final Student student) {
            this.listOfStudentBorrow.add(student);
            this.gymSuitCount++;
        }

        public void lentGymSuit(final Student targetStudent) {
            this.listOfStudentLent.add(targetStudent);
            targetStudent.borrowGymSuit(targetStudent);
            this.gymSuitCount--;
        }

        public boolean isEqualsNumber(final int number) {
            return this.number == number;
        }

        public int getNumber() {
            return number;
        }
    }



}
