package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day7Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483"
            ));
    @Test
    public void testHandsTotalScore() {
        MatcherAssert.assertThat("Total score is 6440", Day7.getHandsTotalScore(inputTest, false), Matchers.equalTo(6440L));
    }

    @Test
    public void testHandsTotalScoreWithJoker() {
        MatcherAssert.assertThat("Total score is 5905", Day7.getHandsTotalScore(inputTest, true), Matchers.equalTo(5905L));
    }


    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 7).ifPresent(lines -> log.info("The answer is {}", Day7.getHandsTotalScore(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 7).ifPresent(lines -> log.info("The answer is {}", Day7.getHandsTotalScore(lines, true)));
    }
}
