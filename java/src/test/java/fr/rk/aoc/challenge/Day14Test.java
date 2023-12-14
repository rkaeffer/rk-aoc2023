package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day14Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "O....#....",
            "O.OO#....#",
            ".....##...",
            "OO.#O....O",
            ".O.....O#.",
            "O.#..O.#.#",
            "..O..#O..O",
            ".......O..",
            "#....###..",
            "#OO..#...."
    ));

    @Test
    public void testSumOfWeight() {
        MatcherAssert.assertThat("Sum is 136", Day14.getSumOfWeight(inputTest, false, -1), Matchers.equalTo(136L));
        MatcherAssert.assertThat("Sum is 87", Day14.getSumOfWeight(inputTest, true, 1), Matchers.equalTo(87L));
        MatcherAssert.assertThat("Sum is 69", Day14.getSumOfWeight(inputTest, true, 2), Matchers.equalTo(69L));
        MatcherAssert.assertThat("Sum is 69", Day14.getSumOfWeight(inputTest, true, 3), Matchers.equalTo(69L));


    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 14).ifPresent(lines -> log.info("The answer is {}", Day14.getSumOfWeight(lines, false, -1)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 14).ifPresent(lines -> log.info("The answer is {}", Day14.getSumOfWeight(lines, true, 1000000000L)));
    }
}
