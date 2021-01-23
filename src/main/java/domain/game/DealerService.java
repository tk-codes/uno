package domain.game;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DealerService {

    /**
     * Cards are shuffled using the modern version of Fisher-Yates shuffle.
     * Refer https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
     * @param cards list will not be modified
     * @return a new shuffled card list
     */
    public static List<Card> shuffle(List<Card> cards) {
        var shuffledCards = new ArrayList<>(cards);
        var rand = new Random();

        for (int i = 0; i < cards.size() - 2; i++) {
            // get a random index for remaining positions, i.e. [i, CARDS_SIZE - 1)
            var r = i + rand.nextInt(cards.size() - i);

            shuffledCards.set(i, cards.get(r));
            shuffledCards.set(r, cards.get(i));
        }

        return shuffledCards;
    }
}
