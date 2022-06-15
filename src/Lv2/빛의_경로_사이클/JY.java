package Lv2.빛의_경로_사이클;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.IntStream;

public class JY {

    // 각칸마다 S ,L 또는 R 써있는 격자 격자에서 빛을 쏘려함
    // S인경우 직진 L인경우 좌회전 R인경우 우회전
    // 끝을 넘어갈경우 반대쪽끝으로 롤백
    // 1행에서 행이 줄어드는 방향으로 이동할경우 열의 반대쪽끝행으로
    // 격자 정보주는 문자열배열 grid
    // 만들어지는 빛의 경로 사이클의 모든 길이를 배열에담아 오름차순
    // 길이 500보다 작음 문자열의길이 500보다 작음 문자열길의 서로같음
    public static void main(String[] ags) {
        Solution solution = new Solution();
        String[] s= new String[] {"SLR","LRR"};
        solution.solution(s);
    }
    // East West South Norht
    public static class Solution {
        public int[] solution(String[] grid) {
            Paper paper = new Paper(grid);
            Razer razer = new Razer(paper);
            razer.startRazer(0,1, EnumContainer.Cardinal.North);
            var firstGo =  razer.enGo();
            int[] answer = {};
            return answer;
        }

            //SL , LR
        public class Razer{
            private final Paper paper;
            private Block currentBlock;
            public Razer(Paper paper){
                this.paper = paper;
            }
            public Razer startRazer(int x , int y , EnumContainer.Cardinal cardinal){
                var blocks =  paper.getBlocks();
                blocks[x][y].setStartPoint(cardinal);
                blocks[x][y].setJoinPoint(cardinal);
                currentBlock = blocks[x][y];
                return this;
            }
            public EnumContainer.Cardinal enGo(){
               var cardinal = new CardinalChanger(currentBlock).change();
               return cardinal;
            }

        }

        public class CardinalChanger{
            private final Block block;
            public CardinalChanger(Block block){
                this.block = block;
            }
            public EnumContainer.Cardinal change() {
                switch (block.joinPoint) {
                    case East:
                        return east(block.rule);
                    case West:
                        return west(block.rule);
                    case North:
                        return north(block.rule);
                    case South:
                        return south(block.rule);
                    default:
                        return EnumContainer.Cardinal.NONE;
                }
            }
            public EnumContainer.Cardinal east(EnumContainer.Rule rule){
                switch (rule) {
                    case S:
                        return EnumContainer.Cardinal.West;
                    case L:
                        return EnumContainer.Cardinal.South;
                    case R:
                        return EnumContainer.Cardinal.North;
                    default:
                        return EnumContainer.Cardinal.NONE;
                }
            }
            public EnumContainer.Cardinal west(EnumContainer.Rule rule){
                switch (rule) {
                    case S:
                        return EnumContainer.Cardinal.East;
                    case L:
                        return EnumContainer.Cardinal.North;
                    case R:
                        return EnumContainer.Cardinal.South;
                    default:
                        return EnumContainer.Cardinal.NONE;
                }
            }
            public EnumContainer.Cardinal north(EnumContainer.Rule rule){
                switch (rule) {
                    case S:
                        return EnumContainer.Cardinal.South;
                    case L:
                        return EnumContainer.Cardinal.East;
                    case R:
                        return EnumContainer.Cardinal.West;
                    default:
                        return EnumContainer.Cardinal.NONE;
                }
            }
            public EnumContainer.Cardinal south(EnumContainer.Rule rule){
                switch (rule) {
                    case S:
                        return EnumContainer.Cardinal.North;
                    case L:
                        return EnumContainer.Cardinal.West;
                    case R:
                        return EnumContainer.Cardinal.East;
                    default:
                        return EnumContainer.Cardinal.NONE;
                }
            }
        }
        public class Paper {
            private Block[][] blocks;
            private final int x;
            private final int y;
            public Paper(String[] grid){
                this.x = grid[0].length();
                this.y = grid.length;
                this.blocks = new Block[x][y];
                addblock(grid);

            }
            public void addblock(String[] grid){
                IntStream.range(0,x)
                        .forEach(fe-> IntStream.range(0,y)
                                .forEach(fe2->blocks[fe][fe2] = new Block(grid[fe2].charAt(fe),fe,fe2)));
            }
            public Block[][] getBlocks(){
                return  blocks;
            }
        }
        public class Block {
            private boolean north = false;
            private boolean south = false;
            private boolean west = false;
            private boolean east = false;
            private final int x;
            private final int y;
            private EnumContainer.Cardinal startPoint;
            private EnumContainer.Cardinal joinPoint;
            private final EnumContainer.Rule rule;
            public Block(Character rule,int x,int y) {
                this.x = x;
                this.y = y;
                this.rule = EnumContainer.Rule.setValue(rule);
                this.startPoint = EnumContainer.Cardinal.NONE;
            }
            public void setStartPoint(EnumContainer.Cardinal cardinal){
                this.startPoint = cardinal;
            }
            public void setJoinPoint(EnumContainer.Cardinal cardinal){
                this.joinPoint = cardinal;
            }
        }


        public static class EnumContainer{
            enum Rule{
                S('S'),L('L'),R('R'),NONE('N');
                private final Character value;
                Rule(Character value){this.value = value;}
                public static Rule setValue(Character value){
                    return Arrays.stream(values())
                            .filter(fl -> fl.value.equals(value))
                            .findFirst()
                            .orElse(NONE);
                }
            }

            enum Cardinal{
                // 북 남 동 서
                North("North"),South("South"),East("East"),West("West"),NONE("NONE");
                private final String value;
                Cardinal(String value){this.value = value;}
            }
        }


    }
}

