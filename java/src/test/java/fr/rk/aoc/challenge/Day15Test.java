package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day15Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"
    ));

    @Test
    public void testSumOfHash() {
        MatcherAssert.assertThat("Sum is 0", Day15.getSumOfHash("rn"), Matchers.equalTo(0L));
        MatcherAssert.assertThat("Sum is 3", Day15.getSumOfHash("pc"), Matchers.equalTo(3L));
        MatcherAssert.assertThat("Sum is 1", Day15.getSumOfHash("qp"), Matchers.equalTo(1L));
        MatcherAssert.assertThat("Sum is 52", Day15.getSumOfHash("HASH"), Matchers.equalTo(52L));
        MatcherAssert.assertThat("Sum is 1320", Day15.getSumOfHash(inputTest.get(0)), Matchers.equalTo(1320L));
    }

    @Test
    public void testSumOfFocusingPower() {
        MatcherAssert.assertThat("Sum is 145", Day15.getFocusingPower(inputTest.get(0)), Matchers.equalTo(145L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 15).ifPresent(lines -> log.info("The answer is {}", Day15.getSumOfHash(lines.get(0))));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 15).ifPresent(lines -> log.info("The answer is {}", Day15.getFocusingPower(lines.get(0))));
    }
}
