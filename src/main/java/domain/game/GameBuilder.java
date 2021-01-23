package domain.game;

import domain.card.CardDeck;

public class GameBuilder {

    public Game build() {
        var cards = new CardDeck().getImmutableCards();

        var shuffledCards = DealerService.shuffle(cards);

        var drawPile = new DrawPile(shuffledCards);
        return new Game(drawPile);
    }


}
