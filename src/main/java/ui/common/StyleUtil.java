package ui.common;

import domain.card.Card;
import domain.card.CardColor;
import domain.card.NumberCard;

import java.awt.*;

public class StyleUtil {
    private StyleUtil() {
    }

    public static Color COLOR_RED = new Color(192, 80, 77);
    public static Color COLOR_BLUE = new Color(31, 73, 125);
    public static Color COLOR_GREEN = new Color(0, 153, 0);
    public static Color COLOR_YELLOW = new Color(255, 204, 0);
    public static Color COLOR_BLACK = new Color(0, 0, 0);

    public static String DEFAULT_FONT = "Helvetica";

    private static final Character CHAR_REVERSE = (char) 8634;
    private static final Character CHAR_SKIP = (char) Integer.parseInt("2718", 16);

    public static Color convertCardColor(CardColor color) {
        if (color == null) {
            return COLOR_BLACK;
        }

        switch (color) {
            case RED -> {
                return COLOR_RED;
            }
            case GREEN -> {
                return COLOR_GREEN;
            }
            case BLUE -> {
                return COLOR_BLUE;
            }
            case YELLOW -> {
                return COLOR_YELLOW;
            }
            default -> throw new IllegalArgumentException("Unsupported card color " + color);
        }
    }

    public static String getValueToDisplay(Card card) {
        switch (card.getType()) {
            case NUMBER -> {
                return Integer.toString(((NumberCard) card).getValue());
            }
            case SKIP -> {
                return CHAR_SKIP.toString();
            }
            case REVERSE -> {
                return CHAR_REVERSE.toString();
            }
            case DRAW_TWO -> {
                return "2+";
            }
            case WILD_COLOR -> {
                return "W";
            }
            case WILD_DRAW_FOUR -> {
                return "4+";
            }
            default -> throw new IllegalArgumentException("Unsupported card type " + card.getType());
        }
    }
}
