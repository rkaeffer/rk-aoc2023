package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day13Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#.",
            "",
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#"
    ));

    @Test
    public void testSummarize() {
        MatcherAssert.assertThat("Summarize is 405", Day13.getMirrorSummarize(inputTest, false), Matchers.equalTo(405L));
        MatcherAssert.assertThat("Summarize is 400", Day13.getMirrorSummarize(inputTest, true), Matchers.equalTo(400L));

    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 13).ifPresent(lines -> log.info("The answer is {}", Day13.getMirrorSummarize(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 13).ifPresent(lines -> log.info("The answer is {}", Day13.getMirrorSummarize(lines, true)));
    }





}
