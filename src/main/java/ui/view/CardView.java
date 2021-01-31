package ui.view;

import domain.card.Card;
import ui.common.StyleUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardView extends JPanel {
    private final Card card;
    private final String value;

    private final int width = 100;
    private final int height = 150;
    private final int margin = 5;

    private final Dimension dimension = new Dimension(width, height);

    private final Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
    private final Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);

    public CardView(Card card) {
        this.card = card;
        this.value = StyleUtil.getValueToDisplay(card);

        initView();
    }

    public Dimension getDimension() {
        return dimension;
    }

    private void initView() {
        setPreferredSize(dimension);
        setBorder(defaultBorder);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                showHoverEffect();
            }

            @Override
            public void mouseExited(MouseEvent e) {
               super.mouseExited(e);
               removeHoverEffect();
            }
        });
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
        g2.setColor(Color.WHITE);
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
        int stringWidth = fontMetrics.stringWidth(value) / 2;
        int fontHeight = defaultFont.getSize() / 3;

        g2.setColor(cardColor);
        g2.setFont(defaultFont);
        g2.drawString(value, width / 2 - stringWidth, height / 2 + fontHeight);
    }

    private void drawValueInCorner(Graphics2D g2) {
        var defaultFont = new Font(StyleUtil.DEFAULT_FONT, Font.ITALIC, width / 5);

        g2.setColor(Color.white);
        g2.setFont(defaultFont);
        g2.drawString(value, margin, 5 * margin);
    }

    private void showHoverEffect(){
        setBorder(focusedBorder);

        Point p = getLocation();
        p.y -= 20;
        setLocation(p);
    }

    private void removeHoverEffect() {
        setBorder(defaultBorder);

        Point p = getLocation();
        p.y += 20;
        setLocation(p);
    }
}
