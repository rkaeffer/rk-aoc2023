package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day6Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "7,15,30",
            "9,40,200"
    ));

    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "71530",
            "940200"
    ));

    ArrayList<String> chall2 = new ArrayList<>(Arrays.asList(
            "42899189",
            "308117012911467"
    ));

    @Test
    public void testRecordPossibilityMultiplication() {
        MatcherAssert.assertThat("La multiplication des possibilités est 288", Day6.getMultiplicationRecordsPossibility(inputTest), Matchers.equalTo(288L));
    }

    @Test
    public void testRecordPossibility() {
        MatcherAssert.assertThat("La multiplication des possibilités est 71503", Day6.getMultiplicationRecordsPossibility(inputTest2), Matchers.equalTo(71503L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 6).ifPresent(lines -> log.info("The answer is {}", Day6.getMultiplicationRecordsPossibility(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        log.info("The answer is {}", Day6.getMultiplicationRecordsPossibility(chall2));
    }
}
