package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day3Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598.."
    ));

    @Test
    public void testSumOfNumberWithAdjecentSymbol() {
        MatcherAssert.assertThat("Sum of number with adajacent symbol is 4361", Day3.getSumOfNumberWithAdjacentSymbol(inputTest), Matchers.equalTo(4361L));
    }

    @Test
    public void testSumOfAdjacentGearRatio() {
        MatcherAssert.assertThat("Sum of adjacent gear ratio is 467835", Day3.getSumOfGearRatio(inputTest), Matchers.equalTo(467835L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 3).ifPresent(lines -> log.info("The answer is {}", Day3.getSumOfNumberWithAdjacentSymbol(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 3).ifPresent(lines -> log.info("The answer is {}", Day3.getSumOfGearRatio(lines)));
    }

    
}
