package ui.view;

import application.IGameAppService;
import domain.game.DealerService;
import domain.player.ImmutablePlayer;
import ui.common.StyleUtil;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class PlayerView extends JPanel {
    private JLayeredPane handCardsView;
    private Box controlPanel;

    private JButton draw;
    private JButton sayUNO;
    private JLabel nameLabel;

    private final ImmutablePlayer player;

    private final IGameAppService appService;

    public PlayerView(ImmutablePlayer player, IGameAppService appService) {
        this.player = player;
        this.appService = appService;

        initView();
    }

    private void initView() {
        Box layout = Box.createHorizontalBox();

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

        Point originPoint = getPoint(handCardsView.getWidth(), player.getTotalCards());
        int offset = calculateOffset(handCardsView.getWidth(), player.getTotalCards());

        int i = 0;
        for (var card : player.getHandCards().collect(Collectors.toList())) {
            var cardView = new CardView(card,
                (playedCard) -> appService.playCard(player.getId(), playedCard));

            cardView.setBounds(originPoint.x, originPoint.y,
                cardView.getDimension().width, cardView.getDimension().height);
            handCardsView.add(cardView, i++);
            handCardsView.moveToFront(cardView);

            originPoint.x += offset;
        }

        repaint();
    }

    private Point getPoint(int width, int totalCards) {
        Point p = new Point(0, 20);
        if (totalCards < DealerService.TOTAL_INITIAL_HAND_CARDS) {
            var offset = calculateOffset(width, totalCards);
            p.x = (width - offset * totalCards) / 2;
        }
        return p;
    }

    private int calculateOffset(int width, int totalCards) {
        if (totalCards <= DealerService.TOTAL_INITIAL_HAND_CARDS) {
            return 71;
        } else {
            return (width - 100) / (totalCards - 1);
        }
    }

    private void initControlPanel() {
        initDrawButton();
        initSayNoButton();
        initNameLabel();

        controlPanel = Box.createVerticalBox();
        controlPanel.add(nameLabel);
        controlPanel.add(draw);
        controlPanel.add(Box.createVerticalStrut(15));
        controlPanel.add(sayUNO);
    }

    private void initNameLabel() {
        nameLabel = new JLabel(player.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font(StyleUtil.defaultFont, Font.BOLD, 15));
    }

    private void initSayNoButton() {
        sayUNO = new JButton("Say UNO");
        sayUNO.setBackground(new Color(149, 55, 53));
        sayUNO.setFont(new Font(StyleUtil.defaultFont, Font.BOLD, 20));
        sayUNO.setFocusable(false);
    }

    private void initDrawButton() {
        draw = new JButton("Draw");

        draw.setBackground(new Color(79, 129, 189));
        draw.setFont(new Font(StyleUtil.defaultFont, Font.BOLD, 20));
        draw.setFocusable(false);
    }
}
