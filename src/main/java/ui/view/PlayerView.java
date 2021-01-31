package ui.view;

import domain.player.ImmutablePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class PlayerView extends JPanel {
    private Box layout;
    private JLayeredPane handCardsView;
    private Box controlPanel;

    private JButton draw;
    private JButton sayUNO;
    private JLabel nameLabel;

    private final ImmutablePlayer player;

    public PlayerView(ImmutablePlayer player) {
        this.player = player;

        initView();
    }

    private void initView() {
        layout = Box.createHorizontalBox();

        initHandCardsView();
        initControlPanel();

        layout.add(handCardsView);
        layout.add(Box.createHorizontalStrut(40));
        layout.add(controlPanel);
        add(layout);
        setOpaque(false);
    }

    private void initHandCardsView() {
        handCardsView = new JLayeredPane();
        handCardsView.setPreferredSize(new Dimension(600, 175));
        handCardsView.setOpaque(false);

        renderHandCardsView();
    }

    private void renderHandCardsView() {
        handCardsView.removeAll();

        int offset = calculateOffset(handCardsView.getWidth(), player.getTotalCards());
        Point point = getPoint(handCardsView.getWidth(), player.getTotalCards());

        int i = 0;
        for (var card : player.getHandCards().collect(Collectors.toList())) {
            var cardView = new CardView(card);

            cardView.setBounds(point.x, point.y, cardView.getDimension().width, cardView.getDimension().height);
            handCardsView.add(cardView, i++);
            handCardsView.moveToFront(cardView);
            point.x += offset;
        }

        repaint();
    }

    private Point getPoint(int width, int totalCards) {
        Point p = new Point(0, 20);
        if (totalCards < 8) {
            var offset = calculateOffset(width, totalCards);
            p.x = (width - offset * totalCards) / 2;
        }
        return p;
    }

    private int calculateOffset(int width, int totalCards) {
        int offset = 71;

        if (totalCards <= 8) {
            return offset;
        } else {
            return (width - 100) / (totalCards - 1);
        }
    }

    private void initControlPanel() {
        draw = new JButton("Draw");
        sayUNO = new JButton("Say UNO");
        nameLabel = new JLabel(player.getName());

        // style
        draw.setBackground(new Color(79, 129, 189));
        draw.setFont(new Font("Arial", Font.BOLD, 20));
        draw.setFocusable(false);

        sayUNO.setBackground(new Color(149, 55, 53));
        sayUNO.setFont(new Font("Arial", Font.BOLD, 20));
        sayUNO.setFocusable(false);

        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));

        controlPanel = Box.createVerticalBox();
        controlPanel.add(nameLabel);
        controlPanel.add(draw);
        controlPanel.add(Box.createVerticalStrut(15));
        controlPanel.add(sayUNO);
    }
}
