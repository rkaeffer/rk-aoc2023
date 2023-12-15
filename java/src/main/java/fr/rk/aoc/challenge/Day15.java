package fr.rk.aoc.challenge;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Day15 {

    private static HashMap<Integer, LinkedHashMap<String, Integer>> boxes;
    public static long getSumOfHash(String input) {
        long sum = 0L;
        for(String s : input.split(",")) {
            sum += applyAlgorithm(s);
        }
        return sum;
    }

    public static long getFocusingPower(String input) {
        initBoxes();
        for(String s : input.split(",")) {
            char last = s.charAt(s.length()-1);
            if (last == '-') {
                String key = s.split("-")[0];
                int box = applyAlgorithm(key);
                HashMap<String, Integer> curBox = boxes.get(box);
                if(curBox.get(key) != null) {
                    curBox.remove(key);
                }
            } else {
                String key = s.split("=")[0];
                int box = applyAlgorithm(key);
                HashMap<String, Integer> curBox = boxes.get(box);
                curBox.put(key,  Character.getNumericValue(last));
            }
        }
        return calculateFocusingPower();
    }

    private static void initBoxes() {
        boxes = new HashMap<>();
        for(int i=0; i<256; i++) {
            boxes.put(i, new LinkedHashMap<>());
        }
    }

    private static long calculateFocusingPower() {
        long focusingPower = 0L;
        for (int i=0; i<256; i++) {
            int index=1;
            HashMap<String, Integer> box = boxes.get(i);
            for(Map.Entry<String, Integer> focal : box.entrySet()) {
                focusingPower += (long) (i + 1) *index*focal.getValue();
                index++;
            }
        }
        return focusingPower;
    }

    private static int applyAlgorithm(String seq) {
        int currentValue = 0;
        for(char c : seq.toCharArray()) {
            currentValue += (int) c;
            currentValue *= 17;
            currentValue %= 256;
        }
        return currentValue;
    }
}
