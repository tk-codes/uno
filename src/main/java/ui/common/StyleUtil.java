package ui.common;

import domain.card.CardColor;

import java.awt.*;

public class StyleUtil {
    private StyleUtil(){}

    public static Color COLOR_RED = new Color(192,80,77);
    public static Color COLOR_BLUE = new Color(31,73,125);
    public static Color COLOR_GREEN = new Color(0,153,0);
    public static Color COLOR_YELLOW = new Color(255,204,0);
    public static Color COLOR_BLACK = new Color(0,0,0);

    public static String DEFAULT_FONT = "Helvetica";

    public static Color convertCardColor(CardColor color){
        switch(color){
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
            default -> {
                return COLOR_BLACK;
            }
        }
    }
}
