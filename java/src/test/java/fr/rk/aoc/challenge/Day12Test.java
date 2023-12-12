package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day12Test {
    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            "???.### 1,1,3", 
            ".??..??...?##. 1,1,3",
            "?#?#?#?#?#?#?#? 1,3,1,6",
            "????.#...#... 4,1,1",
            "????.######..#####. 1,6,5",
            "?###???????? 3,2,1"
    ));


    @Test
    public void testSumOfArrangement()  {
        MatcherAssert.assertThat("Sum is 21", Day12.getSumOfPossibleArrangments(inputTest, false), Matchers.equalTo(21L));
        MatcherAssert.assertThat("Sum is 525152", Day12.getSumOfPossibleArrangments(inputTest, true), Matchers.equalTo(525152L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 12).ifPresent(lines -> log.info("The answer is {}", Day12.getSumOfPossibleArrangments(lines, false)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 12).ifPresent(lines -> log.info("The answer is {}", Day12.getSumOfPossibleArrangments(lines, true)));
    }
}
