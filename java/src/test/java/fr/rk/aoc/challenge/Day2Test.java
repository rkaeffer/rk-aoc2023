package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day2Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    ));

    @Test
    public void testSumeGameWithLimit() {
        MatcherAssert.assertThat("Sum of game id is 8", Day2.getSumOfGameIdWithLimit(inputTest, 12,13,14), Matchers.equalTo(8L));
    }

    @Test
    public void testSumCubePower() {
        MatcherAssert.assertThat("Sum of power is 2286", Day2.getSumOfGamePower(inputTest), Matchers.equalTo(2286L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 2).ifPresent(lines -> log.info("The answer is {}", Day2.getSumOfGameIdWithLimit(lines, 12,13,14)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 2).ifPresent(lines -> log.info("The answer is {}", Day2.getSumOfGamePower(lines)));
    }


}
