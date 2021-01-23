package domain.game;

import domain.card.Card;
import domain.card.CardDeck;

import java.util.List;

public class GameBuilder {

    public Game build() {
        var cards = new CardDeck().getImmutableCards();

        var drawPile = buildDrawPile(cards);
        var handCardLists = DealerService.dealInitialHandCards(drawPile, 3);

        return new Game(drawPile);
    }

    private DrawPile buildDrawPile(List<Card> cards) {
        var shuffledCards = DealerService.shuffle(cards);

        return new DrawPile(shuffledCards);
    }


}
