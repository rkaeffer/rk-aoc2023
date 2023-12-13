package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.List;

import static fr.rk.aoc.challenge.Day13.Mirror.summarize;

public final class Day13 {

    public static long getMirrorSummarize(List<String> input, boolean withSmudge) {
        List<String> mirror = new ArrayList<>();
        List<Mirror> mirrors = new ArrayList<>();
        for(String s : input) {
            if(s.equals("")) {
                mirrors.add(new Mirror(mirror, withSmudge ? 1 : 0));
                mirror = new ArrayList<>();
            } else {
                mirror.add(s);
            }
        }
        mirrors.add(new Mirror(mirror, withSmudge ? 1 : 0));
        return summarize(mirrors);
    }

    static class Mirror {
        List<String> mirrorAsRow;
        List<String> mirrorAsCol;
        boolean isRow = false;
        boolean isCol = false;
        int nbLeft = -1;
        int nbTop = -1;

        Mirror(List<String> input, int tolerance) {
            mirrorAsCol = new ArrayList<>();
            for(int i=0; i<input.size(); i++) {
                mirrorAsRow = new ArrayList<>(input);
            }
            for(int i=0; i<input.get(0).length(); i++) {
                mirrorAsCol.add("");
                for(int j=0; j<input.size(); j++) {
                    String s =  mirrorAsCol.get(i);
                    s += input.get(j).charAt(i);
                    mirrorAsCol.set(i, s);
                }
            }
            identifyMirroring(tolerance);
        }

        private void identifyMirroring(int nbTolerance) {
            int nb = checkMirror(mirrorAsCol, nbTolerance);
            if(nb!=-1) {
                this.isCol = true;
                this.nbTop = nb;
            }
            nb = checkMirror(mirrorAsRow, nbTolerance);
            if(nb!=-1) {
                this.isRow = true;
                this.nbLeft = nb;
            }
        }

        private int checkMirror(List<String> toCheck, int nbTolerance) {
           for(int i=1; i<toCheck.size(); i++) {
                int curTolerance = nbTolerance;
                List<String> withMirror = new ArrayList<>(toCheck);
                withMirror.add(i, "-");
                boolean isMirror = true;
                int limit = i <= toCheck.size()/2 ? i : toCheck.size() - i;
                for(int j=1; j<=limit; j++) {
                    StringCompare compare = isEquals(withMirror.get(i-j), withMirror.get(i+j), curTolerance);
                    isMirror = compare.equals;
                    curTolerance = compare.tolerance;
                    if(!isMirror) {
                        break;
                    }
                }
                if(isMirror && curTolerance == 0) {
                    return i;
                }
           }
           return -1;
        }

        static long summarize(List<Mirror> mirrors) {
            long summarize = 0L;
            for(Mirror m : mirrors) {
                if(m.isRow) {
                    summarize+= 100L * m.nbLeft;
                } else {
                    summarize+= m.nbTop;
                }
            }
            return summarize;
        }

        static StringCompare isEquals(String a, String b, Integer nbTolerance) {
            if(nbTolerance == 0) {
                return new StringCompare(a.equals(b), 0);
            } else {
                boolean equals = true;
                for(int i=0; i<a.length(); i++) {
                    if(a.charAt(i) != b.charAt(i)) {
                        if(nbTolerance > 0) {
                            nbTolerance--;
                        } else {
                            equals = false;
                        }
                    }
                }
                return new StringCompare(equals, nbTolerance);
            }
        }

        static class StringCompare {
            int tolerance;
            boolean equals;

            StringCompare(boolean equals, int tolerance) {
                this.equals = equals;
                this.tolerance = tolerance;
            }
        }
    }
}
