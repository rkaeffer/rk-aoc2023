package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.List;

public final class Day3 {

    public static long getSumOfNumberWithAdjacentSymbol(List<String> input) {
        Gondola gondola = new Gondola(input);
        long sum = 0L;
        for(GondolaNumber gn : gondola.gondolaNumbers) {
            if(gn.hasAdjacentSymbol(gondola.gondola)) {
                sum += gn.value;
            }
        }
        return sum;
    }

    public static long getSumOfGearRatio(List<String> input) {
        Gondola gondola = new Gondola(input);
        long sum = 0L;
        //Calculate AdjacentGear
        for(GondolaNumber gn : gondola.gondolaNumbers) {
            gn.hasAdjacentSymbol(gondola.gondola);
        }
        //Extract All Adjacent
        List<AdjacentGear> adjacentGears = new ArrayList<>();
        for(GondolaNumber gn : gondola.gondolaNumbers) {
            for(AdjacentGear ag : gn.adjacentGears) {
                adjacentGears.add(ag);
            }
        }
        //Detect sameGear and attach other GondolaNumber
        for(AdjacentGear ag : adjacentGears) {
            for(GondolaNumber gn : gondola.gondolaNumbers) {
                if(gn != ag.gn) {
                    for(AdjacentGear ag2 : gn.adjacentGears) {
                        if(ag.isEqual(ag2)) {
                            ag.otherGondolaNumber.add(gn);
                        }
                    }
                }
            }
        }
        for(AdjacentGear ag : adjacentGears) {
            if(ag.otherGondolaNumber.size() == 1) {
                sum += (ag.gn.value * ag.otherGondolaNumber.get(0).value);
            }
        }
        return sum / 2;
    }


    static class Gondola {
        Character[][] gondola;
        List<GondolaNumber> gondolaNumbers;

        public Gondola(List<String> input) {
            gondolaNumbers = new ArrayList<>();
            gondola = new Character[input.size()][input.get(0).length()];
            for(int i=0; i<input.size(); i++) {
                String line = input.get(i);
                for (int j=0; j<line.length(); j++) {
                    gondola[i][j] = line.charAt(j);
                    if(Character.isDigit(line.charAt(j))) {
                        int xStart=j;
                        String toNumb = line.charAt(j) + "";
                        while(j+1 < line.length() && Character.isDigit(line.charAt(j+1))) {
                            toNumb += line.charAt(j+1) + "";
                            j++;
                        }
                        int xEnd = j;
                        gondolaNumbers.add(new GondolaNumber(i, xStart, xEnd, Long.parseLong(toNumb)));
                    }
                }
            }
        }
    }

    static class GondolaNumber {
        int xStart;
        int xEnd;
        int y;
        long value;
        List<AdjacentGear> adjacentGears;

        GondolaNumber(int y, int xStart, int xEnd, long value) {
            this.y = y;
            this.xStart = xStart;
            this.xEnd = xEnd;
            this.value = value;
            adjacentGears = new ArrayList<>();
        }

        public boolean hasAdjacentSymbol(Character[][] gondola) {
            int yMax = gondola.length - 1;
            int xMax = gondola[0].length - 1;
            int lineStart = (this.xStart - 1 >= 0) ? this.xStart - 1 : this.xStart;
            int lineEnd = (this.xEnd + 1 <= xMax) ? this.xEnd + 1 : this.xEnd;
            //Check upperLine
            if(this.y - 1 >= 0) {
                for(int i=lineStart; i<=lineEnd; i++) {
                    if(isSymbol(gondola[this.y-1][i])) {
                        if(gondola[this.y-1][i] == '*') {
                            adjacentGears.add(new AdjacentGear(i, this.y - 1, this));
                        }
                        return true;
                    }
                }
            }
            //Check underline
            if(this.y + 1 <= yMax) {
                for(int i=lineStart; i<=lineEnd; i++) {
                    if(isSymbol(gondola[this.y+1][i])) {
                        if(gondola[this.y+1][i] == '*') {
                            adjacentGears.add(new AdjacentGear(i, this.y + 1, this));
                        }
                        return true;
                    }
                }
            }
            //Check right
            if(this.xEnd + 1 <= xMax) {
                if(isSymbol(gondola[this.y][this.xEnd+1])) {
                    if(gondola[this.y][this.xEnd+1] == '*') {
                        adjacentGears.add(new AdjacentGear(this.xEnd + 1, this.y, this));
                    }
                    return true;
                }
            }
            //Check Left
            if(this.xStart - 1 >= 0) {
                if(isSymbol(gondola[this.y][this.xStart-1])) {
                    if(gondola[this.y][this.xStart - 1] == '*') {
                        adjacentGears.add(new AdjacentGear(this.xStart-1, this.y, this));
                    }
                    return true;
                }
            }
            return false;
        }

        public boolean isSymbol(char c) {
            return c != '.';
        }
    }

    static class AdjacentGear {
        int x;
        int y;
        GondolaNumber gn;
        List<GondolaNumber> otherGondolaNumber;

        public AdjacentGear(int x, int y, GondolaNumber gn) {
            this.x = x;
            this.y = y;
            this.gn = gn;
            this.otherGondolaNumber = new ArrayList<>();
        }

        public boolean isEqual(AdjacentGear ag) {
            return this.x == ag.x && this.y == ag.y;
        }
    }
}
