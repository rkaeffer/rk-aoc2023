package fr.rk.aoc.challenge;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Day9 {

    public static long getSumOfRightExtrapolatedValues(List<String> input) {
        List<ReportLine> reportLines = input.stream().map(ReportLine::new).collect(Collectors.toList());
        return reportLines.stream().mapToLong(r -> r.rightExtrapolatedValue).sum();
    }

    public static long getSumOfLeftExtrapolatedValue(List<String> input) {
        List<ReportLine> reportLines = input.stream().map(ReportLine::new).collect(Collectors.toList());
        return reportLines.stream().mapToLong(r -> r.leftExtrapolatedValue).sum();
    }

    static class ReportLine {
        int[] values;
        ReportLine subline;

        long rightExtrapolatedValue;
        long leftExtrapolatedValue;

        ReportLine(String input) {
            this.values = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            this.calculateExtrapolatdValue();
        }

        ReportLine(int[] values) {
            this.values = new int[values.length -1];
            for(int i=0; i<values.length-1; i++) {
                this.values[i] = values[i+1] - values[i];
            }
            this.calculateExtrapolatdValue();
        }

        private void calculateExtrapolatdValue() {
            boolean allZeros = true;
            for(int value : values) {
                if(value != 0) {
                    allZeros = false;
                    break;
                }
            }
            if(allZeros) {
                this.rightExtrapolatedValue = 0;
                this.leftExtrapolatedValue = 0;
            } else {
                this.subline = new ReportLine(this.values);
                this.rightExtrapolatedValue = this.values[values.length-1] + this.subline.rightExtrapolatedValue;
                this.leftExtrapolatedValue = this.values[0] - this.subline.leftExtrapolatedValue;
          }

        }
    }
}
