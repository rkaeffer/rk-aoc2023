package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day16Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            ".|...\\....",
            "|.-.\\.....",
            ".....|-...",
            "........|.",
            "..........",
            ".........\\",
            "..../.\\\\..",
            ".-.-/..|..",
            ".|....-|.\\",
            "..//.|...."
    ));

    @Test
    public void testNbTileEnergized() {
        MatcherAssert.assertThat("Nb tile energized is 46", Day16.getNbTileEnergized(inputTest, 0, 0, Day16.Direction.RIGHT), Matchers.equalTo(46L));
    }

    @Test
    public void testNbMaxTileEnergized() {
        MatcherAssert.assertThat("Nb max tile energized is 51", Day16.getMaxNbTileEnergized(inputTest), Matchers.equalTo(51L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 16).ifPresent(lines -> log.info("The answer is {}", Day16.getNbTileEnergized(lines, 0, 0, Day16.Direction.RIGHT)));
    }

    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 16).ifPresent(lines -> log.info("The answer is {}", Day16.getMaxNbTileEnergized(lines)));
    }
}
