package fr.rk.aoc.challenge;

import java.util.ArrayList;
import java.util.List;

public final class Day4 {

    public static long getSumOfAllCardsScore(List<String> input) {
        List<GameCard> gameCards = new ArrayList<>();
        long sum=0L;
        for(String in : input) {
            gameCards.add(new GameCard(in));
        }
        for(GameCard gc : gameCards) {
            sum += gc.calculateWinningScore();
        }
        return sum;
    }

    public static long getSumOfWinningsCards(List<String> input) {
        long sum = 0L;
        GameCardDeck[] gameCardDecks = new GameCardDeck[input.size()];
        int curIndex = 0;
        for(String in : input) {
            gameCardDecks[curIndex] = new GameCardDeck(new GameCard(in));
            curIndex++;
        }
        for(int i=0; i<gameCardDecks.length; i++) {
            int nbMatch = gameCardDecks[i].gc.getNbWinningNumber();
            for(int j=i+1; j<=i+nbMatch; j++) {
                gameCardDecks[j].nbCard += gameCardDecks[i].nbCard;
            }
        }
        for(GameCardDeck gcd : gameCardDecks) {
            sum+=gcd.nbCard;
        }
        return sum;
    }

    static class GameCard {

        int numCard;
        List<Integer> winningNumbers;
        List<Integer> cardNumbers;

        GameCard(String input) {
            this.winningNumbers = new ArrayList<>();
            this.cardNumbers = new ArrayList<>();
            String[] cardId = input.split(":");
            this.numCard = Integer.parseInt(cardId[0].split(" ")[cardId[0].split(" ").length - 1]);
            String allNumber = cardId[1].trim().replaceAll("  ", " ");
            String[] numbers = allNumber.split("\\|");
            for(String wNb : numbers[0].trim().split(" ")) {
                winningNumbers.add(Integer.parseInt(wNb));
            }
            for(String cNb : numbers[1].trim().split(" ")) {
                cardNumbers.add(Integer.parseInt(cNb));
            }
        }

        public int getNbWinningNumber() {
            int nbmatch = 0;
            for(int wNb : winningNumbers) {
                for(int cNb : cardNumbers) {
                    if (cNb == wNb) {
                        nbmatch++;
                    }
                }
            }
            return nbmatch;
        }

        public long calculateWinningScore() {
            int nbmatch = 0;
            for(int wNb : winningNumbers) {
                for(int cNb : cardNumbers) {
                    if (cNb == wNb) {
                        nbmatch++;
                    }
                }
            }
            if(nbmatch == 0) {
                return 0L;
            }
            return (long) Math.pow(2, nbmatch-1);
        }
    }

    static class GameCardDeck {
        GameCard gc;
        long nbCard;

        GameCardDeck(GameCard gc) {
            this.gc = gc;
            this.nbCard = 1;
        }
    }
}
