package fr.rk.aoc.challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Day1 {

    static Map<Character, String> digitAsLetter = new HashMap<>();

    static {
        digitAsLetter.put('1', "one");
        digitAsLetter.put('2', "two");
        digitAsLetter.put('3', "three");
        digitAsLetter.put('4', "four");
        digitAsLetter.put('5', "five");
        digitAsLetter.put('6', "six");
        digitAsLetter.put('7', "seven");
        digitAsLetter.put('8', "eight");
        digitAsLetter.put('9', "nine");
    }


    public static long getCalibrationsSum(List<String> input, boolean withLetter) {
        return input.stream().mapToLong(calibration -> {
           char first = getFirstDigit(calibration, withLetter, false);
           char second = getLastDigit(calibration, withLetter);
           return Long.parseLong(first + "" + second);
        }).sum();
    }

    private static char getFirstDigit(String calibration, boolean withLetter, boolean isReverse) {
        int firstdigitIndex = Integer.MAX_VALUE;
        char resChar = 0;
        for(int i=0; i<calibration.length(); i++) {
            char currChar = calibration.charAt(i);
            if (Character.isDigit(currChar)) {
                resChar = currChar;
                firstdigitIndex = i;
                break;
            }
        }
        if(!withLetter) {
            return resChar;
        }
        for (Map.Entry<Character, String> entry : digitAsLetter.entrySet()) {
            String value = isReverse ? new StringBuilder(entry.getValue()).reverse().toString() : entry.getValue();
            int stringIndex = calibration.indexOf(value);
            if (stringIndex != -1 && stringIndex < firstdigitIndex) {
                resChar = entry.getKey();
                firstdigitIndex = calibration.indexOf(value);
            }
        }
        return resChar;
    }

    private static char getLastDigit(String calibration, boolean withLetter) {
        return getFirstDigit(new StringBuilder(calibration).reverse().toString(), withLetter, true);
    }
}
