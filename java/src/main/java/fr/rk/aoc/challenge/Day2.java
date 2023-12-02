package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Day2 {

    public static final String BLUE = "blue";
    public static final String RED = "red";
    public static final String GREEN = "green";

    public static long getSumOfGameIdWithLimit(List<String> input, int redLimit, int greenLimit, int blueLimit) {
        long sumOfGame = 0L;
        for(String in : input) {
            Game game = new Game(in);
            if(game.isGamePossible(redLimit, greenLimit, blueLimit)) {
                sumOfGame+= game.id;
            }
        }
        return sumOfGame;
    }

    public static long getSumOfGamePower(List<String> input) {
        long sumOfGame = 0L;
        for(String in : input) {
            Game game = new Game(in);
            GameSet maxSet = game.getMaxSet();
            sumOfGame += (maxSet.nbRed * maxSet.nbBlue* maxSet.nbGreen);
        }
        return sumOfGame;
    }

    static class GameSet {
        public long nbRed;
        public long nbBlue;
        public long nbGreen;
        GameSet(long nbRed, long nbGreen, long nbBlue) {
            this.nbBlue = nbBlue;
            this.nbGreen = nbGreen;
            this.nbRed = nbRed;
        }

        public boolean isSetPossible(int redLimit, int greenLimit, int blueLimit) {
            return this.nbRed <= redLimit && this.nbGreen <= greenLimit && this.nbBlue <= blueLimit;
        }
    }

    static class Game {
        public int id;
        List<GameSet> gameSets;

        Game(String input) {
            this.gameSets = new ArrayList<>();
            String[] gameDesc = input.split(":");
            this.id = Integer.parseInt(gameDesc[0].split(" ")[1]);
            String[] bagSet = gameDesc[1].split(";");
            Arrays.stream(bagSet).forEach(bag -> {
                int nbRed= 0, nbBlue = 0, nbGreen = 0;
                String[] nbColor = bag.split(",");
                for(String color : nbColor) {
                    String[] colorDetails = color.trim().split(" ");
                    switch (colorDetails[1].trim()) {
                        case RED:
                            nbRed = Integer.parseInt(colorDetails[0].trim());
                            break;
                        case BLUE:
                            nbBlue = Integer.parseInt(colorDetails[0].trim());
                            break;
                        case GREEN:
                            nbGreen = Integer.parseInt(colorDetails[0].trim());
                            break;
                    }
                }
                gameSets.add(new GameSet(nbRed, nbGreen, nbBlue));
            });
        }

        public boolean isGamePossible(int redLimit, int greenLimit, int blueLimit) {
            boolean gamePossible = true;
            for(GameSet gs : this.gameSets) {
                gamePossible &= gs.isSetPossible(redLimit, greenLimit, blueLimit);
            }
            return gamePossible;
        }

        public GameSet getMaxSet() {
            long maxRed = 0, maxGreen = 0, maxBlue = 0;
            for(GameSet gs : this.gameSets) {
                if(gs.nbBlue > maxBlue) {
                    maxBlue = gs.nbBlue;
                }
                if(gs.nbGreen > maxGreen) {
                    maxGreen = gs.nbGreen;
                }
                if(gs.nbRed > maxRed) {
                    maxRed = gs.nbRed;
                }
            }
            return new GameSet(maxRed, maxGreen, maxBlue);
        }
    }
}
