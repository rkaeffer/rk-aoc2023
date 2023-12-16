package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Day16 {

    public static long getMaxNbTileEnergized(List<String> input) {
        long maxEnergized = 0L;
        //LEFT / RIGHT
        for(int i=0; i<input.size(); i++) {
            long nbEnergizedFromRight = getNbTileEnergized(input, 0, i, Direction.RIGHT);
            long nbEnergizedFromLeft = getNbTileEnergized(input, input.get(0).length() - 1, i, Direction.LEFT);
            if(maxEnergized < Math.max(nbEnergizedFromRight, nbEnergizedFromLeft)) {
                maxEnergized = Math.max(nbEnergizedFromRight, nbEnergizedFromLeft);
            }
        }
        //DOWN / TOP
        for(int i=0; i<input.get(0).length(); i++) {
            long nbEnergizedFromTop = getNbTileEnergized(input, i, 0, Direction.DOWN);
            long nbEnergizedFromBottom = getNbTileEnergized(input, i, input.size() - 1, Direction.UP);
            if(maxEnergized < Math.max(nbEnergizedFromTop, nbEnergizedFromBottom)) {
                maxEnergized = Math.max(nbEnergizedFromTop, nbEnergizedFromBottom);
            }
        }
        return maxEnergized;
    }

    public static long getNbTileEnergized(List<String> input, int xStart, int yStart, Direction direction) {
        Grid g = new Grid(input, xStart, yStart, direction);
        while(g.beams.size() > 0) {
            g.runGridOnce();
        }
        return g.getNbEnergized();
    }

    static class Grid {
        Cell[][] cells;
        List<Beam> beams;

        Grid(List<String> input, int xStart, int yStart, Direction direction) {
            beams = new ArrayList<>();
            beams.add(new Beam(xStart, yStart, direction));
            cells = new Cell[input.size()][input.get(0).length()];
            for(int i=0; i<input.size(); i++) {
                for(int j=0; j<input.get(i).length(); j++) {
                    cells[i][j] = new Cell(j, i, input.get(i).charAt(j));
                }
            }
        }

        void runGridOnce() {
            List<List<Beam>> newBeams = new ArrayList<>();
            for(Beam b : beams) {
                Cell curCell = this.cells[b.curY][b.curX];
                newBeams.add(curCell.runBeam(b));
                curCell.energized = true;
            }
            List<Beam> flatBeams = newBeams.stream().flatMap(List::stream).collect(Collectors.toList());
            flatBeams.removeIf(b -> !b.isBeamValid(cells));
            this.beams = flatBeams;
        }

        long getNbEnergized() {
            long nbEnergized = 0L;
            for(int i=0; i<cells.length; i++) {
                for(int j=0;j<cells[i].length; j++) {
                    if(cells[i][j].energized) {
                        nbEnergized++;
                    }
                }
            }
            return nbEnergized;
        }
    }

    static class Cell {
        int x;
        int y;
        boolean energized;
        char c;
        Boolean hasDivide;

        Cell(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.energized = false;
            this.hasDivide = Boolean.FALSE;
        }

        List<Beam> runBeam(Beam b) {
            List<Beam> newBeams = new ArrayList<>();
            switch (c) {
                case '/':
                    if(b.direction == Direction.RIGHT) {
                        b.direction = Direction.UP;
                    } else if(b.direction == Direction.LEFT) {
                        b.direction = Direction.DOWN;
                    } else if(b.direction == Direction.UP) {
                        b.direction = Direction.RIGHT;
                    } else if(b.direction == Direction.DOWN) {
                        b.direction = Direction.LEFT;
                    }
                    newBeams.add(b);
                    break;
                case '\\':
                    if(b.direction == Direction.RIGHT) {
                        b.direction = Direction.DOWN;
                    } else if(b.direction == Direction.LEFT) {
                        b.direction = Direction.UP;
                    } else if(b.direction == Direction.UP) {
                        b.direction = Direction.LEFT;
                    } else if(b.direction == Direction.DOWN) {
                        b.direction = Direction.RIGHT;
                    }
                    newBeams.add(b);
                    break;
                case '-':
                    if(b.direction == Direction.UP || b.direction == Direction.DOWN) {
                        if (!hasDivide) {
                            newBeams.add(new Beam(b.curX, b.curY, Direction.LEFT));
                            newBeams.add(new Beam(b.curX, b.curY, Direction.RIGHT));
                            hasDivide = Boolean.TRUE;
                        }
                    } else {
                        newBeams.add(b);
                    }
                    break;
                case '|':
                    if(b.direction == Direction.LEFT || b.direction == Direction.RIGHT) {
                        if (!hasDivide) {
                            newBeams.add(new Beam(b.curX, b.curY, Direction.UP));
                            newBeams.add(new Beam(b.curX, b.curY, Direction.DOWN));
                            hasDivide = Boolean.TRUE;
                        }
                    } else {
                        newBeams.add(b);
                    }
                    break;
                default:
                    newBeams.add(b);
                    break;
            }
            for(Beam newBeam : newBeams) {
                newBeam.curY += newBeam.direction.yDelta;
                newBeam.curX += newBeam.direction.xDelta;
            }
            return newBeams;
        }
    }

    static class Beam {
        Direction direction;
        int curX;
        int curY;

        Beam(int curX, int cury, Direction d) {
            this.curY = cury;
            this.curX = curX;
            this.direction = d;
        }

        boolean isBeamValid(Cell[][] grid) {
            return this.curX >= 0 && this.curY >=0 && this.curX < grid[0].length && this.curY < grid.length;
        }
    }

    static enum Direction {
        UP(0,-1),
        DOWN(0,1),
        RIGHT(1,0),
        LEFT(-1,0);

        final int xDelta;
        final int yDelta;

        Direction(int xDelta, int yDelta) {
            this.xDelta = xDelta;
            this.yDelta = yDelta;
        }
    }
}
