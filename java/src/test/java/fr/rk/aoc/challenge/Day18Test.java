package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day18Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "R 6 (#70c710)",
            "D 5 (#0dc571)",
            "L 2 (#5713f0)",
            "D 2 (#d2c081)",
            "R 2 (#59c680)",
            "D 2 (#411b91)",
            "L 5 (#8ceee2)",
            "U 2 (#caa173)",
            "L 1 (#1b58a2)",
            "U 2 (#caa171)",
            "R 2 (#7807d2)",
            "U 3 (#a77fa3)",
            "L 2 (#015232)",
            "U 2 (#7a21e3)"
    ));

    @Test
    public void testLavaVolume() {
      //  MatcherAssert.assertThat("Volume of lava is 62", Day18.getLavaVolume(inputTest, false), Matchers.equalTo(62L));
        MatcherAssert.assertThat("Volume of lava is fat", Day18.getLavaVolume(inputTest, true), Matchers.equalTo(952408144115L));

    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 18).ifPresent(lines -> log.info("The answer is {}", Day18.getLavaVolume(lines, false)));
    }
    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 18).ifPresent(lines -> log.info("The answer is {}", Day18.getLavaVolume(lines, true)));
    }

}
