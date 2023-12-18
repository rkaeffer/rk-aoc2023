package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.List;

public final class Day18 {

    public static long getLavaVolume(List<String> input, boolean useColor) {
        LavaPool lavaPool = new LavaPool(input, useColor);
        return lavaPool.getPerimeter() + lavaPool.getInnerPoints();
    }

    static class LavaPool {
        List<Pos> positions;

        LavaPool(List<String> input, boolean useColor) {
            this.positions = new ArrayList<>();
            int curX = 0;
            int curY = 0;
            this.positions.add(new Pos(0,0));
            for(String s : input) {
                String[] splitted = s.split(" ");
                int length = useColor ? Integer.parseInt(splitted[2].substring(2,7), 16): Integer.parseInt(splitted[1]);
                switch(useColor ? getCharAsDirection(splitted[2].charAt(splitted[2].length()-2)) : splitted[0]) {
                    case "R":
                        curX+=length;
                        break;
                    case "D":
                        curY-=length;
                        break;
                    case "U":
                        curY+=length;
                        break;
                    case "L":
                        curX-=length;
                        break;
                    default:
                        break;
                }
                this.positions.add(new Pos(curX, curY));

            }
            this.positions.remove(0);
        }

        //Pick Theorem
        //A = i + b/2 - 1
        // A = area
        // i = innerpoints
        // b = boundary (path length)
        // i = A - b/2 + 1
        //A se calcule avec le formule de shoelace
        public long getInnerPoints() {
            return getArea() - (getPerimeter() / 2) + 1;
        }

        public long getPerimeter() {
            long perimeter = 0L;
            for(int i=0; i<positions.size() - 1; i++) {
                if(positions.get(i).x == positions.get(i+1).x) {
                    perimeter+= Math.abs(positions.get(i).y - positions.get(i+1).y);
                } else {
                    perimeter+= Math.abs(positions.get(i).x - positions.get(i+1).x);
                }
            }
            if(positions.get(positions.size()-1).x == positions.get(0).x) {
                perimeter+= Math.abs(positions.get(positions.size()-1).y - positions.get(0).y);
            } else {
                perimeter+= Math.abs(positions.get(positions.size()-1).x - positions.get(0).x);
            }
            return perimeter;
        }
        //Shoelace formula
        public long getArea() {
            long sum1= 0L;
            long sum2 = 0L;

            for(int i=0; i<positions.size() - 1; i++) {
                sum1 = sum1 + (long) positions.get(i).x * positions.get(i+1).y;
                sum2 = sum2 + (long) positions.get(i).y * positions.get(i+1).x;
            }
            sum1 = sum1 + (long) positions.get(positions.size() - 1).x *positions.get(0).y;
            sum2 = sum2 + (long) positions.get(0).x *positions.get(positions.size()-1).y;
            return Math.abs(sum1 - sum2) / 2;
        }

        String getCharAsDirection(char c) {
            if (c == '0') return "R";
            if (c == '1') return "D";
            if (c == '2') return "L";
            if (c == '3') return "U";
            return "";
        }

    }

    static class Pos {
        int x;
        int y;
        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
