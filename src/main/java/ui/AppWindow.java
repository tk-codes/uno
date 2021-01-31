package ui;

import domain.card.CardColor;
import domain.card.NumberCard;
import ui.card.CardView;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {
    private final JPanel mainLayout;

    public AppWindow(){
        mainLayout = new JPanel();
        setupLayout();

        showFrame();
    }

    private void showFrame() {
        setVisible(true);
        setResizable(true);
        setLocation(200, 100);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupLayout() {
        mainLayout.setPreferredSize(new Dimension(960,720));
        mainLayout.setBackground(new Color(30,36,40));
        mainLayout.setLayout(new BorderLayout());

        mainLayout.add(new CardView(new NumberCard(1, CardColor.RED)), BorderLayout.NORTH);

        add(mainLayout);
    }
}
