package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
@Slf4j
public class Day9Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45"
    ));

    @Test
    public void testSumOfExtrapolatedValue() {
        MatcherAssert.assertThat("Extrapolated right sum is 114", Day9.getSumOfRightExtrapolatedValues(inputTest), Matchers.equalTo(114L));
        MatcherAssert.assertThat("Extrapolated left sum is 2", Day9.getSumOfLeftExtrapolatedValue(inputTest), Matchers.equalTo(2L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 9).ifPresent(lines -> log.info("The answer is {}", Day9.getSumOfRightExtrapolatedValues(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 9).ifPresent(lines -> log.info("The answer is {}", Day9.getSumOfLeftExtrapolatedValue(lines)));
    }

}
