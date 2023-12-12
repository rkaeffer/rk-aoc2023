package fr.rk.aoc.challenge;

import java.util.*;
import java.util.stream.Collectors;

public final class Day12 {

    //On va mémoriser les résultats pour un ensemble de spring associé à ses groupes
    //Example : ???.### 1,1,3
    private static final Map<String, Long> permutationsMap = new HashMap<>();

    private static String calculateMapKey(String spring, List<Integer> pattern) {
        return spring + " " + pattern.stream().map(a -> Integer.toString(a)).collect(Collectors.joining(","));
    }

    private static long countPermutations(String springs, List<Integer> conditions) {
        String key = calculateMapKey(springs, conditions);
        if (permutationsMap.containsKey(key)) {
            return permutationsMap.get(key);
        }

        if (springs.isBlank()) {
            return conditions.isEmpty() ? 1 : 0;
        }

        char firstChar = springs.charAt(0);
        long permutations = 0;
        if (firstChar == '.') {
            permutations = countPermutations(springs.substring(1), conditions);
        } else if (firstChar == '?') {
            permutations = countPermutations("#" + springs.substring(1), conditions)
                    + countPermutations("." + springs.substring(1), conditions);
        } else {
            if (conditions.size() > 0) {
                int nbBroken = conditions.get(0);
                if (nbBroken <= springs.length()
                        && springs.chars().limit(nbBroken).allMatch(c -> c == '#' || c == '?')) {
                    List<Integer> newGroups = conditions.subList(1, conditions.size());
                    if (nbBroken == springs.length()) {
                        permutations = newGroups.isEmpty() ? 1 : 0;
                    } else if (springs.charAt(nbBroken) == '?') {
                        permutations = countPermutations("." + springs.substring(nbBroken + 1), newGroups);
                    } else if (springs.charAt(nbBroken) == '.') {
                        permutations = countPermutations(springs.substring(nbBroken + 1), newGroups);
                    }
                }
            }
        }
        permutationsMap.put(key, permutations);
        return permutations;
    }

    public static long getSumOfPossibleArrangments(List<String> input, boolean withFolding) {
        long sum = 0L;
        for(String in : input) {

            String[] machine = in.split(" ");
            List<Integer> conditions = Arrays.stream(machine[1].trim().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            if(withFolding) {
                List<Integer> conditionFold = new ArrayList<>();
                for(int i=0; i<5; i++) {
                    conditionFold.addAll(conditions);
                }
                conditions = conditionFold;
            }
            String toCheck;
            if(withFolding) {
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<4; i++) {
                    sb.append(machine[0].trim());
                    sb.append("?");
                }
                sb.append(machine[0].trim());
                toCheck = sb.toString();
            } else {
                toCheck = machine[0].trim();
            }
            sum += countPermutations(toCheck, conditions);
        }
        return sum;
    }
}
