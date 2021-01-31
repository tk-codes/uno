package ui.card;

import domain.card.Card;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class CardView extends JPanel {
    private final Card card;
    private final String value = "1";

    private final int width = 50;
    private final int height = 75;
    private final Dimension cardSize = new Dimension(width * 2, height * 2);

    private Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
    private Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);

    public CardView(Card card) {
        this.card = card;

        initView();
    }

    private void initView() {
        setPreferredSize(cardSize);
        setBorder(defaultBorder);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);

        int margin = 5;
        g2.setColor(Color.RED); // TODO
        g2.fillRect(margin, margin, width - 2 * margin, height - 2 * margin);

        g2.setColor(Color.white);
        AffineTransform org = g2.getTransform();
        g2.rotate(45, (double) width * 3 / 4, (double) height * 3 / 4);

        g2.fillOval(0, height / 4, width * 3 / 5, height);
        g2.setTransform(org);

        //Value in the center		
        Font defaultFont = new Font("Helvetica", Font.BOLD, width / 2 + 5);
        FontMetrics fm = this.getFontMetrics(defaultFont);
        int StringWidth = fm.stringWidth(value) / 2;
        int FontHeight = defaultFont.getSize() / 3;

        g2.setColor(Color.RED); // TODO: Card Color
        g2.setFont(defaultFont);
        g2.drawString(value, width / 2 - StringWidth, height / 2 + FontHeight);

        //Value in the corner
        defaultFont = new Font("Helvetica", Font.ITALIC, width / 5);

        g2.setColor(Color.white);
        g2.setFont(defaultFont);
        g2.drawString(value, 2 * margin, 5 * margin);
    }
}
