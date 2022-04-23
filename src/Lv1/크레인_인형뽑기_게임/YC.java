package Lv1.크레인_인형뽑기_게임;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.IntStream;

public class YC {

    // 문제 접근 방식

    // 객체 종류
    // 보드, 인형, 바구니, 크레인

    // 객체 속성
    // 보드 : 인형 타입의 이차원 배열(없는 경우 null)
    // 인형 : 고유 번호
    // 바구니 : 인형 타입의 스택, 이전 인형, 터진 인형 갯수
    // 크레인 : 뽑을 위치 배열, 담을 바구니

    // 객체 함수
    // 보드 : 인형 담기, 뽑힌 인형의 처리
    // 바구니 : 인형 담기, 인형 터뜨리기
    // 크레인 : 보드의 뽑을 위치에서 맨 위에 있는 인형 뽑기, 뽑은 인형을 바구니에 담기

    public static void main(String[] args) {
        int[][] board = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        int solution = new YC().solution(board, moves);
        System.out.println(solution);
    }

    public int solution(int[][] board, int[] moves) {

        // 보드 생성
        final Board boardOfDoll = new Board(board);
        // 바구니 생성
        final Basket basket = new Basket();
        // 크레인 생성
        final Crane crane = new Crane(boardOfDoll, basket, moves);
        crane.pullAll();

        return basket.getCountOfBurstDolls();
    }

    public static class Board {

        private final Doll[][] boardOfDoll;

        public Board(final int[][] board) {
            boardOfDoll = getBoardOfDoll(board);
        }

        private Doll[][] getBoardOfDoll(final int[][] board) {
            final Doll[][] localBoardOfDoll = new Doll[board.length][board[0].length];
            IntStream.range(0, board.length)
                .forEach(x ->
                    IntStream.range(0, board[0].length)
                        .forEach(y -> {
                            if(board[x][y] == 0)
                                localBoardOfDoll[x][y] = null;
                            else
                                localBoardOfDoll[x][y] = new Doll(board[x][y]);
                        })
                );
            return localBoardOfDoll;
        }

        public Pair<Boolean, Doll> pulledUp(final int y) {

            final int targetLine = y-1;

            int locationOfDoll =
                IntStream.range(0, boardOfDoll[0].length)
                    .filter(idx -> boardOfDoll[idx][targetLine] != null)
                    .findFirst()
                    .orElse(-1);
            if(locationOfDoll != -1) {
                final Doll doll = boardOfDoll[locationOfDoll][targetLine];
                boardOfDoll[locationOfDoll][targetLine] = null;
                return new Pair<>(Boolean.TRUE, doll);
            } else {
                return new Pair<>(Boolean.FALSE, Doll.getDefault());
            }

        }

    }

    public static class Doll {

        private int number;

        public Doll(final int number) {
            this.number = number;
        }

        public static Doll getDefault() {
            return new Doll(-1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Doll doll = (Doll) o;
            return number == doll.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }

    }

    public static class Basket {

        private final Stack<Doll> stackOfDoll = new Stack<>();
        private Doll dollOfJustInTheBoxedIn;
        private int numberOfBurstDoll = 0;

        public void put(final Doll doll) {
            stackOfDoll.push(doll);
            if(isBeInSuccession(doll)) {
                burst();
            } else {
                dollOfJustInTheBoxedIn = stackOfDoll.peek();
            }
        }

        private boolean isBeInSuccession(final Doll doll) { // 인형이 연속되어 담기는지 여부 확인
            return doll.equals(dollOfJustInTheBoxedIn);
        }

        private void burst() {
            stackOfDoll.pop();
            stackOfDoll.pop();
            numberOfBurstDoll += 2;
            if(!stackOfDoll.empty()) {
                dollOfJustInTheBoxedIn = stackOfDoll.peek();
            } else {
                dollOfJustInTheBoxedIn = null;
            }
        }

        public int getCountOfBurstDolls() {
            return numberOfBurstDoll;
        }

    }

    public static class Crane {

        private final Board board;
        private final Basket basket;
        private int[] locationToPullOuts;

        public Crane(final Board board, final Basket basket, final int[] locationToPullOuts) {
            this.board = board;
            this.basket = basket;
            this.locationToPullOuts = locationToPullOuts;
        }

        public void pullAll() {
            Arrays.stream(locationToPullOuts)
                .forEach(this::pull);
        }

        private void pull(final int y) {
            final Pair<Boolean, Doll> dollPair = board.pulledUp(y);
            if(dollPair.getFirst())
                basket.put(dollPair.second);
        }
    }

    public static class Pair<A, B> {

        protected final A first;
        protected final B second;

        public Pair(final A first, final B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

    }

}
