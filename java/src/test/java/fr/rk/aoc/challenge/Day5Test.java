package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day5Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "seeds: 79 14 55 13",
                    "",
                    "seed-to-soil map:",
                    "50 98 2",
                    "52 50 48",
                    "",
                    "soil-to-fertilizer map:",
                    "0 15 37",
                    "37 52 2",
                    "39 0 15",
                    "",
                    "fertilizer-to-water map:",
                    "49 53 8",
                    "0 11 42",
                    "42 0 7",
                    "57 7 4",
                    "",
                    "water-to-light map:",
                    "88 18 7",
                    "18 25 70",
                    "",
                    "light-to-temperature map:",
                    "45 77 23",
                    "81 45 19",
                    "68 64 13",
                    "",
                    "temperature-to-humidity map:",
                    "0 69 1",
                    "1 0 69",
                    "",
                    "humidity-to-location map:",
                    "60 56 37",
                    "56 93 4"
    ));

    @Test
    public void testGetLowestSeedLocation() {
        MatcherAssert.assertThat("Lowest location is 35", Day5.getLowestSeedLocation(inputTest, false), Matchers.equalTo(35L));
    }

    @Test
    public void testGetLowestSeedLocationWithRange() {
        MatcherAssert.assertThat("Lowest location is 46", Day5.getLowestSeedLocation(inputTest, true), Matchers.equalTo(46L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 5).ifPresent(lines -> log.info("The answer is {}", Day5.getLowestSeedLocation(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 5).ifPresent(lines -> log.info("The answer is {}", Day5.getLowestSeedLocation(lines, true)));
    }
}
