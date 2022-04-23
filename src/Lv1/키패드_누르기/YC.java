package Lv1.키패드_누르기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YC {

    // 키패드 누르기.
    // 키패드의 키가 추가될 수 있다고 가정해보자 ( 그렇기 때문에 거리는 미리 계산해 놓지 않는다 )

    // 접근 방식.
    // 1. 객체의 종류
    // 키패드, 손
    // 2. 객체의 속성
    // - 키패드 : 각 키패드 간의 거리 (point), 가로, 세로 1 단위.
    //          : 각 키는 좌표와 값을 가지는데 '*' 를 기준으로 잡는다. (0,0)
    // - 사람 : 오른손잡이 or 왼손잡이, 오른쪽 엄지손가락, 왼쪽 엄지손가락
    // 3. 액션의 종류
    // 키패드 누르기
    // - 키패드 간의 거리 계산 구하기
    //    > 현재 위치에서 누르려는 좌표를 각각 빼고 절대값으로 계산 후 더하기
    // - 각 숫자의 담당 엄지손가락이 존재
    // - 엄지손가락의 위치에서 대상 키패드의 거리가 같을 경우 어떤 손가락으로 누를지 판단

    public String solution(int[] numbers, String hand) {

        final Human human = new Human(Human.Type.byName(hand)); // 사람 생성

        final KeyPad keyPad = new KeyPad(); // 키패드 생성

        keyPad.addKeys( // 키 생성 및 키패드 조립
            List.of(
                new KeyPad.Key(keyPad, 0, new Coordinate(1, 0)),
                new KeyPad.Key(keyPad, 1, new Coordinate(0, 3)),
                new KeyPad.Key(keyPad, 2, new Coordinate(1, 3)),
                new KeyPad.Key(keyPad, 3, new Coordinate(2, 3)),
                new KeyPad.Key(keyPad, 4, new Coordinate(0, 2)),
                new KeyPad.Key(keyPad, 5, new Coordinate(1, 2)),
                new KeyPad.Key(keyPad, 6, new Coordinate(2, 2)),
                new KeyPad.Key(keyPad, 7, new Coordinate(0, 1)),
                new KeyPad.Key(keyPad, 8, new Coordinate(1, 1)),
                new KeyPad.Key(keyPad, 9, new Coordinate(2, 1))
            )
        );

        Arrays.stream(numbers).forEach(number -> { // 누를 키를 찾고 어떤 손을 사용할지 판단한 다음 누르는걸 반복
            final KeyPad.Key key = keyPad.getKey(number);
            human.pressKey(key);
        });

        return keyPad.getInputHandValue();
    }

    public static class KeyPad {

        private final Map<Integer, Key> keyMap = new HashMap<>();
        private String inputHandValue = "";
        private final List<Key> pressedKeys = new ArrayList<>();

        public void pressedKey(final Key key, final Human.Hand hand) {
            inputHandValue += hand.getTypeStr();
            pressedKeys.add(key);
        }

        public void addKeys(final List<Key> keys) {
            keys.forEach(key -> keyMap.put(key.value, key));
        }

        public Key getKey(final int number) {
            final Key key = keyMap.getOrDefault(number, Key.getDefault());
            if(key.value == -1)
                throw new IllegalStateException(number + " 에 해당하는 key 가 없습니다.");
            return key;
        }

        public String getInputHandValue() {
            return inputHandValue;
        }

        public static class Key { // 키는 자기 자신의 좌표와 값 속성이 존재

            private final KeyPad keyPad;
            private int value;
            private final Coordinate coordinate;

            public Key(final KeyPad keyPad, final int value, final Coordinate coordinate) {
                this.keyPad = keyPad;
                this.value = value;
                this.coordinate = coordinate;
            }

            public static Key getDefault() {
                return new Key(new KeyPad(), -1, new Coordinate(-9999, -9999));
            }

            public void pressed(final Human.Hand hand) {
                keyPad.pressedKey(this, hand);
            }

            public Coordinate getCoordinate() {
                return coordinate;
            }
        }

    }

    public static class Human {

        private final Type type;
        private final Hand handOfLeft = new Hand(Type.LEFT, new Coordinate(0,0));
        private final Hand handOfRight = new Hand(Type.RIGHT, new Coordinate(2,0));
        private final List<Integer> numbersOfLeftHand = List.of(1,4,7);  // 왼손 엄지손가락 key 숫자 리스트
        private final List<Integer> numbersOfRightHand = List.of(3,6,9); // 오른손 엄지손가락 key 숫자 리스트

        enum Type {
            NONE("NONE", ""),
            LEFT("left", "L"),
            RIGHT("right", "R");
            private final String name;
            private final String value;
            Type(final String name, final String value) {
                this.name = name;
                this.value = value;
            }

            public static Type byName(final String name) {
                return Arrays.stream(values())
                    .filter(type -> type.name.equals(name))
                    .findFirst()
                    .orElse(NONE);
            }

            public Boolean isLeft() {
                return this == LEFT;
            }
            public Boolean isRight() {
                return this == RIGHT;
            }
        }

        public Human(final Type type) {
            this.type = type;
        }

        private Hand getHandOfType() {
            return type.isLeft() ? handOfLeft : handOfRight;
        }

        public void pressKey(final KeyPad.Key key) {
            getHandToPress(key).press(key);
        }

        public Hand getHandToPress(final KeyPad.Key key) { // 어떤 손으로 누를지 구하기
            if(isLeft(key.value))
                return handOfLeft;
            else if(isRight(key.value))
                return handOfRight;
            else
                return getHandOfElse(key);
        }

        private boolean isLeft(final int number) {
            return numbersOfLeftHand.contains(number);
        }

        private boolean isRight(final int number) {
            return numbersOfRightHand.contains(number);
        }

        private Hand getHandOfElse(KeyPad.Key key) {
            int distanceOfLeft = handOfLeft.getCoordinate().getDistance(key.getCoordinate());
            int distanceOfRight = handOfRight.getCoordinate().getDistance(key.getCoordinate());
            if(distanceOfLeft < distanceOfRight) {
                return handOfLeft;
            } else if(distanceOfRight < distanceOfLeft) {
                return handOfRight;
            } else {
                return getHandOfType();
            }
        }


        public static class Hand {

            private final Type type; // 엄지손가락 타입
            private Coordinate coordinate; // 현재 위치

            public Hand(final Type type, final Coordinate coordinate) {
                this.type = type;
                this.coordinate = coordinate;
            }

            public void press(final KeyPad.Key key) {
                key.pressed(this);
                coordinate = key.getCoordinate();
            }

            public String getTypeStr() {
                return this.type.value;
            }

            public Coordinate getCoordinate() {
                return coordinate;
            }

        }

    }

    public static class Coordinate { // 좌표 객체

        private final int x;
        private final int y;

        public Coordinate(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public int getDistance(final Coordinate target) {
            final int xDistance = Math.abs(x - target.x);
            final int yDistance = Math.abs(y - target.y);
            return xDistance + yDistance;
        }

    }

}
