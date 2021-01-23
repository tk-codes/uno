package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UNO Card Deck consists of 108 cards.
 * See https://en.wikipedia.org/wiki/Uno_(card_game)#/media/File:UNO_cards_deck.svg for list of card types.
 */
public class CardDeck {
    private final List<Card> cards = new ArrayList<>(108);

    public CardDeck() {
        createCards();
    }

    public List<Card> getImmutableCards() {
        return Collections.unmodifiableList(cards);
    }

    private void createCards() {
        createNumberCards();
        createActionCards();
        createWildCards();
    }

    private void createNumberCards() {
        for (var color : CardColor.values()) {
            cards.add(new NumberCard(0, color));

            for (var i = 1; i <= 9; i++) {
                cards.add(new NumberCard(i, color));
                cards.add(new NumberCard(i, color));
            }
        }
    }

    private void createActionCards() {
        for (var color : CardColor.values()) {
            for (var i = 0; i < 2; i++) {
                cards.add(new SkipCard(color));
                cards.add(new ReverseCard(color));
                cards.add(new DrawTwoCard(color));
            }
        }
    }

    private void createWildCards() {
        for (var i = 0; i < 4; i++) {
            cards.add(new WildColorCard());
            cards.add(new WildDrawFourCard());
        }
    }
}
