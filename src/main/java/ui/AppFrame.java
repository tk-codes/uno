package ui;

import domain.card.CardColor;
import domain.card.NumberCard;
import domain.player.ImmutablePlayer;
import ui.view.CardView;
import ui.view.PlayerView;
import ui.view.TableView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AppFrame extends JFrame {
    private final JPanel mainLayout;
    private final List<ImmutablePlayer> players;

    public AppFrame(List<ImmutablePlayer> players){
        mainLayout = new JPanel();
        this.players = players;
        setupLayout();

        showFrame();
    }

    private void showFrame() {
        setVisible(true);
        setResizable(false);
        setLocation(200, 100);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupLayout() {
        mainLayout.setPreferredSize(new Dimension(960,720));
        mainLayout.setBackground(new Color(30,36,40));
        mainLayout.setLayout(new BorderLayout());

        var playerView1 = new PlayerView(players.get(0));
        var playerView2 = new PlayerView(players.get(1));
        var tableView = new TableView(new NumberCard(5, CardColor.GREEN));

        mainLayout.add(playerView1, BorderLayout.NORTH);
        mainLayout.add(tableView, BorderLayout.CENTER);
        mainLayout.add(playerView2, BorderLayout.SOUTH);
        add(mainLayout);
    }
}
