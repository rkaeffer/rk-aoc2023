package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
@Slf4j
public class Day11Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "...#......",
            ".......#..",
            "#.........",
            "..........",
            "......#...",
            ".#........",
            ".........#",
            "..........",
            ".......#..",
            "#...#....."
    ));
    @Test
    public void testSumShortestPath() {
        MatcherAssert.assertThat("Total sum is 374", Day11.getSumOfShortestPath(inputTest,2), Matchers.equalTo(374L));
        MatcherAssert.assertThat("Total sum is 374", Day11.getSumOfShortestPath(inputTest,10), Matchers.equalTo(1030L));
        MatcherAssert.assertThat("Total sum is 374", Day11.getSumOfShortestPath(inputTest,100), Matchers.equalTo(8410L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 11).ifPresent(lines -> log.info("The answer is {}", Day11.getSumOfShortestPath(lines, 2)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 11).ifPresent(lines -> log.info("The answer is {}", Day11.getSumOfShortestPath(lines, 1000000)));
    }
}
