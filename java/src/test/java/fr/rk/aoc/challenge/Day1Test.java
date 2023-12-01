package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
@Slf4j
public class Day1Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
    ));

    ArrayList<String> secondInputTest = new ArrayList<>(Arrays.asList(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
    ));

    @Test
    public void testSumCalibrationValues() {
        MatcherAssert.assertThat("Sum of calibration is 142", Day1.getCalibrationsSum(inputTest, false), Matchers.equalTo(142L));
    }

    @Test
    public void testSumCalibrationValuesWithLetters() {
        MatcherAssert.assertThat("Sum of calibration is 281", Day1.getCalibrationsSum(secondInputTest, true), Matchers.equalTo(281L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 1).ifPresent(lines -> log.info("The answer is {}", Day1.getCalibrationsSum(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 1).ifPresent(lines -> log.info("The answer is {}", Day1.getCalibrationsSum(lines, true)));
    }

}
