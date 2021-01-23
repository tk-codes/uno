package domain.card;

public class CardUtil {
    public static void validateColor(CardColor color) {
        if (color == null) {
            throw new IllegalArgumentException("Card color should be defined");
        }
    }

    public static void validateNumber(int number) {
        if (number < 0 || number > 9) {
            throw new IllegalArgumentException("Card number should between 0 and 9");
        }
    }
}
