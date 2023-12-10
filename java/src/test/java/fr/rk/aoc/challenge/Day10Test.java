package fr.rk.aoc.challenge;

import fr.rk.aoc.challenge.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class Day10Test {

    ArrayList<String> inputTest = new ArrayList<>(Arrays.asList(
            ".....",
            ".S-7.",
            ".|.|.",
            ".L-J.",
            "....."
    ));
    ArrayList<String> inputTest2 = new ArrayList<>(Arrays.asList(
            "..F7.",
            ".FJ|.",
            "SJ.L7",
            "|F--J",
            "LJ..."
    ));

    ArrayList<String> inputTest3 = new ArrayList<>(Arrays.asList(
            "...........",
            ".S-------7.",
            ".|F-----7|.",
            ".||.....||.",
            ".||.....||.",
            ".|L-7.F-J|.",
            ".|..|.|..|.",
            ".L--J.L--J.",
            "..........."
    ));

    ArrayList<String> inputTest4 = new ArrayList<>(Arrays.asList(
            "..........",
            ".S------7.",
            ".|F----7|.",
            ".||....||.",
            ".||....||.",
            ".|L-7F-J|.",
            ".|..||..|.",
            ".L--JL--J.",
            ".........."
    ));

    ArrayList<String> inputTest5 = new ArrayList<>(Arrays.asList(
            ".F----7F7F7F7F-7....",
            ".|F--7||||||||FJ....",
            ".||.FJ||||||||L7....",
            "FJL7L7LJLJ||LJ.L-7..",
            "L--J.L7...LJS7F-7L7.",
            "....F-J..F7FJ|L7L7L7",
            "....L7.F7||L7|.L7L7|",
            ".....|FJLJ|FJ|F7|.LJ",
            "....FJL-7.||.||||...",
            "....L---J.LJ.LJLJ..."
    ));

    ArrayList<String> inputTest6 = new ArrayList<>(Arrays.asList(
            "FF7FSF7F7F7F7F7F---7",
            "L|LJ||||||||||||F--J",
            "FL-7LJLJ||||||LJL-77",
            "F--JF--7||LJLJ7F7FJ-",
            "L---JF-JLJ.||-FJLJJ7",
            "|F|F-JF---7F7-L7L|7|",
            "|FFJF7L7F-JF7|JL---7",
            "7-L-JL7||F7|L7F-7F7|",
            "L.L7LFJ|||||FJL7||LJ",
            "L7JLJL-JLJLJL--JLJ.L"
    ));

    @Test
    public void testMaxStep() {
        MatcherAssert.assertThat("Max Step is 4", Day10.getMaxDistanceFromStart(inputTest, 'F'), Matchers.equalTo(4L));
        MatcherAssert.assertThat("Max Step is 8", Day10.getMaxDistanceFromStart(inputTest2, 'F'), Matchers.equalTo(8L));
    }

    @Test
    public void testCountTiles() {
        MatcherAssert.assertThat("Nb tiles is 4", Day10.getNbTiles(inputTest3, 'F'), Matchers.equalTo(4L));
        MatcherAssert.assertThat("Nb tiles is 4", Day10.getNbTiles(inputTest4, 'F'), Matchers.equalTo(4L));
        MatcherAssert.assertThat("Nb tiles is 8", Day10.getNbTiles(inputTest5, 'F'), Matchers.equalTo(8L));
        MatcherAssert.assertThat("Nb tiles is 10", Day10.getNbTiles(inputTest6, '7'), Matchers.equalTo(10L));
    }

    @Test
    public void getFirstChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 10).ifPresent(lines -> log.info("The answer is {}", Day10.getMaxDistanceFromStart(lines, '|')));
    }
    @Test
    public void getSecondChallengeResult() {
        FileUtils.readInputFileAsList("input.txt", 10).ifPresent(lines -> log.info("The answer is {}", Day10.getNbTiles(lines, '|')));
    }
}
