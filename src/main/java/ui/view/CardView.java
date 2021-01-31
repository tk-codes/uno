package ui.view;

import domain.card.Card;
import ui.common.StyleUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CardView extends JPanel {
    private final Card card;
    private final String value = "1"; // TODO

    private final int width = 50;
    private final int height = 75;
    private final int margin = 5;

    private final Dimension dimension = new Dimension(width * 2, height * 2);

    private Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);
    private Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);

    public CardView(Card card) {
        this.card = card;

        initView();
    }

    public Dimension getDimension() {
        return dimension;
    }

    private void initView() {
        setPreferredSize(dimension);
        setBorder(defaultBorder);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        var cardColor = StyleUtil.convertCardColor(card.getColor());

        fillBackground(g2, cardColor);
        drawWhiteOvalInCenter(g2);
        drawValueInCenter(g2, cardColor);
        drawValueInCorner(g2);
    }

    private void fillBackground(Graphics2D g2, Color cardColor) {
        g2.setColor(Color.ORANGE);
        g2.fillRect(0, 0, width, height);

        g2.setColor(cardColor);
        g2.fillRect(margin, margin, width - 2 * margin, height - 2 * margin);
    }

    private void drawWhiteOvalInCenter(Graphics2D g2) {
        var transformer = g2.getTransform();
        g2.setColor(Color.white);
        g2.rotate(45, (double) width * 3 / 4, (double) height * 3 / 4);
        g2.fillOval(0, height / 4, width * 3 / 5, height);

        g2.setTransform(transformer);
    }

    private void drawValueInCenter(Graphics2D g2, Color cardColor) {
        var defaultFont = new Font(StyleUtil.DEFAULT_FONT, Font.BOLD, width / 2 + 5);
        var fontMetrics = this.getFontMetrics(defaultFont);
        int StringWidth = fontMetrics.stringWidth(value) / 2;
        int FontHeight = defaultFont.getSize() / 3;

        g2.setColor(cardColor);
        g2.setFont(defaultFont);
        g2.drawString(value, width / 2 - StringWidth, height / 2 + FontHeight);
    }

    private void drawValueInCorner(Graphics2D g2) {
        var defaultFont = new Font(StyleUtil.DEFAULT_FONT, Font.ITALIC, width / 5);

        g2.setColor(Color.white);
        g2.setFont(defaultFont);
        g2.drawString(value, margin, 5 * margin);
    }
}
