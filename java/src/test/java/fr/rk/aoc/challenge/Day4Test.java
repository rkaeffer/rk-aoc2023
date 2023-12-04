package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day4Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
    ));
    @Test
    public void testSumOfCards() {
        MatcherAssert.assertThat("Sum of points of cards is 13", Day4.getSumOfAllCardsScore(inputTest), Matchers.equalTo(13L));
    }

    @Test
    public void testSumOfWinningCards() {
        MatcherAssert.assertThat("Sum of winning cards is 30", Day4.getSumOfWinningsCards(inputTest), Matchers.equalTo(30L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 4).ifPresent(lines -> log.info("The answer is {}", Day4.getSumOfAllCardsScore(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 4).ifPresent(lines -> log.info("The answer is {}", Day4.getSumOfWinningsCards(lines)));
    }

}
