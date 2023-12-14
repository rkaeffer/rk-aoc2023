package fr.rk.aoc.challenge;

import java.util.List;

public final class Day14 {

    public static long getSumOfWeight(List<String> input, boolean withCycle, long nbCycle) {
        ReflectorDish reflectorDish = new ReflectorDish(input);
        if(!withCycle) {
            reflectorDish.rollNorth();
            return reflectorDish.calculateWeight();
        } else {
            for(long i=0; i<nbCycle; i++) {
                if(i%100000 == 0) {
                    System.out.println("Cycle " + i);
                    System.out.println(reflectorDish.calculateWeight());
                }
                reflectorDish.rollNorth();
                reflectorDish.rollWest();
                reflectorDish.rollSouth();
                reflectorDish.rollEast();
            }
            return reflectorDish.calculateWeight();
        }
    }

    static class ReflectorDish {
        char[][] reflector;

        ReflectorDish(List<String> input) {
            this.reflector = new char[input.size()][input.get(0).length()];
            for(int i=0; i<input.size(); i++) {
                for(int j=0; j<input.get(i).length(); j++) {
                    reflector[i][j] = input.get(i).charAt(j);
                }
            }
        }

        public void printReflector() {
            System.out.println();
            for(int i=0; i<reflector.length; i++) {
                for(int j=0; j<reflector[i].length; j++) {
                    System.out.print(reflector[i][j]);
                }
                System.out.println();
            }
        }

        public void rollNorth() {
            for(int j=0; j<reflector[0].length; j++) {
                int curTop = 0;
                int nbRolling =0;
                 for(int i=0; i<=reflector.length; i++) {
                    if(i == reflector.length || reflector[i][j] == '#') {
                        for(int k=curTop; k<curTop+nbRolling; k++) {
                            reflector[k][j] = 'O';
                        }
                        for(int k=curTop + nbRolling; k<i; k++) {
                            reflector[k][j] = '.';
                        }
                        curTop = i+1;
                        nbRolling = 0;
                    } else if(reflector[i][j] == 'O') {
                        nbRolling++;
                    }
                }
            }
        }

        public void rollSouth() {
            for(int j=0; j<reflector[0].length; j++) {
                int curTop = reflector[0].length-1;
                int nbRolling =0;
                for(int i=reflector.length-1; i>=-1; i--) {
                    if(i == -1 || reflector[i][j] == '#') {
                        for(int k=curTop; k>curTop-nbRolling; k--) {
                            reflector[k][j] = 'O';
                        }
                        for(int k=curTop - nbRolling; k>i; k--) {
                            reflector[k][j] = '.';
                        }
                        curTop = i-1;
                        nbRolling = 0;
                    } else if(reflector[i][j] == 'O') {
                        nbRolling++;
                    }
                }
            }
        }

        public void rollWest() {
            for(int i=0; i<reflector.length; i++) {
                int curTop = 0;
                int nbRolling =0;
                for(int j=0; j<=reflector[i].length; j++) {
                    if(j == reflector.length || reflector[i][j] == '#') {
                        for(int k=curTop; k<curTop+nbRolling; k++) {
                            reflector[i][k] = 'O';
                        }
                        for(int k=curTop + nbRolling; k<j; k++) {
                            reflector[i][k] = '.';
                        }
                        curTop = j+1;
                        nbRolling = 0;
                    } else if(reflector[i][j] == 'O') {
                        nbRolling++;
                    }
                }
            }
        }

        public void rollEast() {
            for(int i=0; i<reflector.length; i++) {
                int curTop = reflector.length-1;;
                int nbRolling =0;
                for(int j=reflector[i].length-1; j>=-1; j--) {
                    if(j == -1 || reflector[i][j] == '#') {
                        for(int k=curTop; k>curTop-nbRolling; k--) {
                            reflector[i][k] = 'O';
                        }
                        for(int k=curTop - nbRolling; k>j; k--) {
                            reflector[i][k] = '.';
                        }
                        curTop = j-1;
                        nbRolling = 0;
                    } else if(reflector[i][j] == 'O') {
                        nbRolling++;
                    }
                }
            }
        }

        public long calculateWeight() {
            long sum = 0L;
            for(int i=0; i<reflector.length; i++) {
                for(int j=0; j<reflector[i].length; j++) {
                    if(reflector[i][j] == 'O') {
                        sum += (reflector.length - i);
                    }
                }
            }
            return sum;
        }

    }
}
