package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day8Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "RL",
            "",
            "AAA = (BBB, CCC)",
            "BBB = (DDD, EEE)",
            "CCC = (ZZZ, GGG)",
            "DDD = (DDD, DDD)",
            "EEE = (EEE, EEE)",
            "GGG = (GGG, GGG)",
            "ZZZ = (ZZZ, ZZZ)"
    ));

    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "LLR",
            "",
            "AAA = (BBB, BBB)",
            "BBB = (AAA, ZZZ)",
            "ZZZ = (ZZZ, ZZZ)"
            ));

    ArrayList<String> inputTest3 = new ArrayList<>(Arrays.asList(
            "LR",
            "",
            "11A = (11B, XXX)",
            "11B = (XXX, 11Z)",
            "11Z = (11B, XXX)",
            "22A = (22B, XXX)",
            "22B = (22C, 22C)",
            "22C = (22Z, 22Z)",
            "22Z = (22B, 22B)",
            "XXX = (XXX, XXX)"
    ));

    @Test
    public void testNbStepToReachZZZ() {
        MatcherAssert.assertThat("Nb step to reach ZZZ is 2", Day8.getNbStepToReachZZZ(inputTest), Matchers.equalTo(2L));
        MatcherAssert.assertThat("Nb step to reach ZZZ is 6", Day8.getNbStepToReachZZZ(inputTest2), Matchers.equalTo(6L));
    }

    @Test
    public void testNbStepToReachAllZ() {
        MatcherAssert.assertThat("Nb step to reach ZZZ is 2", Day8.getNbStepToReachAllZ(inputTest3), Matchers.equalTo(6L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 8).ifPresent(lines -> log.info("The answer is {}", Day8.getNbStepToReachZZZ(lines)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 8).ifPresent(lines -> log.info("The answer is {}", Day8.getNbStepToReachAllZ(lines)));
    }




}
