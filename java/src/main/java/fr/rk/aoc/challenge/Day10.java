package fr.rk.aoc.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class Day10 {

    public static long getMaxDistanceFromStart(List<String> input, char start) {
        PipeMaze pm = new PipeMaze(input, start);
        return pm.runMainLoop() / 2;
    }

    public static long getNbTiles(List<String> input, char start) {
        PipeMaze pm = new PipeMaze(input, start);
        pm.runMainLoop();
        return pm.getInnerPoints();
    }

    static class PipeMaze {
        char[][] matrix;
        int xStart;
        int yStart;

        List<Pos> positions;

        PipeMaze(List<String> input, char startChar) {
            this.matrix = new char[input.size()][input.get(0).length()];
            this.positions = new ArrayList<>();
            for(int y=0; y<input.size(); y++) {
                for(int x=0; x<input.get(y).length(); x++) {
                    this.matrix[y][x] = input.get(y).charAt(x);
                    if(this.matrix[y][x] == 'S') {
                        this.xStart = x;
                        this.yStart = y;
                        this.matrix[y][x] = startChar;
                    }
                }
            }
        }

        public long runMainLoop() {
            long nbStep = 0;
            int curX = xStart;
            int curY = yStart;
            int lastX = -1;
            int lastY = -1;
            do {
                nbStep++;
                this.positions.add(new Pos(curX, curY));
                char curChar = this.matrix[curY][curX];
                int tmpX = curX;
                int tmpY = curY;
                if(curChar == '|') {
                    if(lastX == -1) {
                        curY++;
                    } else {
                        if(curY == lastY + 1) {
                            curY++;
                        } else {
                            curY--;
                        }
                    }
                } else if (curChar == '-') {
                    if(lastX == -1) {
                        curX++;
                    } else {
                        if(curX == lastX + 1) {
                            curX++;
                        } else {
                            curX--;
                        }
                    }
                } else if (curChar == '7') {
                    if(lastX == -1) {
                        curY++;
                    } else {
                        if(curX == lastX + 1) {
                            curY++;
                        } else {
                            curX--;
                        }
                    }
                } else if (curChar == 'J') {
                    if(lastX == -1) {
                        curY++;
                    } else {
                        if(curX == lastX + 1) {
                            curY--;
                        } else {
                            curX--;
                        }
                    }
                } else if (curChar == 'L') {
                    if(lastX == -1) {
                        curY++;
                    } else {
                        if(curY == lastY + 1) {
                            curX++;
                        } else {
                            curY--;
                        }
                    }
                } else if (curChar == 'F') {
                    if(lastX == -1) {
                        curX++;
                    } else {
                        if(curY == lastY - 1) {
                            curX++;
                        } else {
                            curY++;
                        }
                    }
                }
                lastX = tmpX;
                lastY = tmpY;
            } while (xStart != curX || yStart != curY);
            return nbStep;
        }

        //Pick Theorem
        //A = i + b/2 - 1
        // A = area
        // i = innerpoints
        // b = boundary (path length)
        // i = A - b/2 + 1
        //A se calcule avec le formule de shoelace
        public long getInnerPoints() {
            return getArea() - (positions.size() / 2) + 1;
        }

        //Shoelace formula
        public long getArea() {
            long sum1= 0l;
            long sum2 = 0l;

            for(int i=0; i<positions.size() - 1; i++) {
                sum1 = sum1 + (long) positions.get(i).x * positions.get(i+1).y;
                sum2 = sum2 + (long) positions.get(i).y * positions.get(i+1).x;
            }
            sum1 = sum1 + (long) positions.get(positions.size() - 1).x *positions.get(0).y;
            sum2 = sum2 + (long) positions.get(0).x *positions.get(positions.size()-1).y;
            return Math.abs(sum1 - sum2) / 2;
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
