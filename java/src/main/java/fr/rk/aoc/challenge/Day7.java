package fr.rk.aoc.challenge;

import java.util.*;

public final class Day7 {

    public static long getHandsTotalScore(List<String> input, boolean withJoker) {
        initCardvalue(withJoker);
        List<Hand> hands = new ArrayList<>();
        for(String in : input) {
            hands.add(new Hand(in, withJoker));
        }
        Collections.sort(hands);
        long score = 0L;
        for(int i=1; i<=hands.size(); i++) {
            score += i*hands.get(i-1).bid;
        }
        return score;
    }

    static enum HandValue {

        HIGH_CARD,
        PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND;
    }

    static Map<Character, Integer> cardValue = new HashMap<>();

    private static void initCardvalue(boolean withJoker) {
        cardValue.put('2', withJoker ? 2 : 1);
        cardValue.put('3', withJoker ? 3 : 2);
        cardValue.put('4', withJoker ? 4 : 3);
        cardValue.put('5', withJoker ? 5 : 4);
        cardValue.put('6', withJoker ? 6 : 5);
        cardValue.put('7', withJoker ? 7 : 6);
        cardValue.put('8', withJoker ? 8 : 7);
        cardValue.put('9', withJoker ? 9 : 8);
        cardValue.put('T', withJoker ? 10 : 9);
        cardValue.put('J', withJoker ? 1 : 10);
        cardValue.put('Q', 11);
        cardValue.put('K', 12);
        cardValue.put('A', 13);
    }

    static class Hand implements Comparable {
        long bid;
        int[] cards;

        String cardsAsString;
        HandValue handValue;

        Hand(String input, boolean withJoker) {
            String[] hand = input.split(" ");
            this.cardsAsString = hand[0];
            this.bid = Long.parseLong(hand[1]);
            this.cards = hand[0].chars().map(c -> cardValue.get((char) c)).toArray();
            this.calculateHandValue(withJoker);
        }

        @Override
        public int compareTo(Object o) {
            Hand hand = (Hand) o;
            if(handValue.compareTo(hand.handValue) == 0) {
                int index = 0;
                while(cards[index] == hand.cards[index]) {
                    index++;
                }
                return Integer.compare(cards[index], hand.cards[index]);
            } else {
                return handValue.compareTo(hand.handValue);
            }
        }

        private void calculateHandValue(boolean withJoker) {
            Map<Integer, Integer> cardNb = new HashMap<>();
            for(int i=1; i<=13; i++) {
                cardNb.put(i, 0);
            }
            for(int i : this.cards) {
                cardNb.put(i, cardNb.get(i) + 1);
            }
            int nbJ =0;
            if(withJoker) {
                nbJ = cardNb.get(1);
                cardNb.put(1, 0);
            }
            int nb5 = 0;
            int nb4 = 0;
            int nb3 = 0;
            int nb2 = 0;
            for(Map.Entry<Integer, Integer> nbCard : cardNb.entrySet()) {
                if (withJoker ? nbCard.getValue() + nbJ == 5 : nbCard.getValue() == 5) {
                    nb5++;
                }
                if (withJoker ? nbCard.getValue() + nbJ == 4 : nbCard.getValue() == 4) {
                    nb4++;
                }
                if (withJoker ? nbCard.getValue() + nbJ == 3 : nbCard.getValue() == 3) {
                    nb3++;
                }
                if (withJoker ? nbCard.getValue() + nbJ == 2 : nbCard.getValue() == 2) {
                    nb2++;
                }
            }
            if(nb5 >= 1) {
                this.handValue = HandValue.FIVE_OF_A_KIND;
            } else if(nb4 >= 1) {
                this.handValue = HandValue.FOUR_OF_A_KIND;
            } else if(nb3 >= 1 && nb2 >= 1) {
                this.handValue = HandValue.FULL_HOUSE;
            } else if(nb3 >= 1) {
                this.handValue = HandValue.THREE_OF_A_KIND;
            } else if(nb2 >= 2) {
                this.handValue = HandValue.TWO_PAIR;
            } else if(nb2 == 1) {
                this.handValue = HandValue.PAIR;
            } else {
                this.handValue = HandValue.HIGH_CARD;
            }
        }
    }
}
