package domain.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCard {
    @Test
    public void GivenSameNumberCardValues_ShouldBeEqual(){
        var numberCard = new NumberCard(1, CardColor.RED);
        var anotherNumberCard = new NumberCard(1, CardColor.RED);

        assertNotSame(numberCard, anotherNumberCard);
        assertEquals(numberCard, anotherNumberCard);
    }

    @Test
    public void GivenDifferentNumberCardValues_ShouldNotBeEqual(){
        var numberCard = new NumberCard(1, CardColor.GREEN);
        var anotherNumberCard = new NumberCard(1, CardColor.RED);

        assertNotEquals(numberCard, anotherNumberCard);
    }

    @Test
    public void GivenSameSkipCard_ShouldBeEqual(){
        var skipCard = new SkipCard(CardColor.RED);
        var anotherSkipCard = new SkipCard(CardColor.RED);

        assertEquals(skipCard, anotherSkipCard);
    }

    @Test
    public void GivenDifferentSkipCard_ShouldNotBeEqual(){
        var skipCard = new SkipCard(CardColor.RED);
        var anotherSkipCard = new SkipCard(CardColor.GREEN);

        assertNotEquals(skipCard, anotherSkipCard);
    }

    @Test
    public void GivenDifferentActionCard_ShouldNotBeEqual(){
        var skipCard = new SkipCard(CardColor.RED);
        var reverseCard = new ReverseCard(CardColor.RED);

        assertNotEquals(skipCard, reverseCard);
    }

    @Test
    public void GivenSameWildColorCard_ShouldBeEqual(){
        var card = new WildColorCard();
        var anotherCard = new WildColorCard();

        assertEquals(card, anotherCard);
    }

    @Test
    public void GivenSameWildDrawFourCard_ShouldBeEqual(){
        var card = new WildDrawFourCard();
        var anotherCard = new WildDrawFourCard();

        assertEquals(card, anotherCard);
    }
}
