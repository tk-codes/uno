package ui.view;

import domain.card.Card;
import ui.common.StyleUtil;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {
    private Card topCard;
    private final JPanel table;

    public TableView(Card firstDiscard){
        setOpaque(false);
        setLayout(new GridBagLayout());

        topCard = firstDiscard;
        table = new JPanel();
        table.setBackground(new Color(64,64,64));

        initTable();
        initInfoView();
    }

    private void initTable(){
        table.setPreferredSize(new Dimension(500,200));
        table.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        table.add(new CardView(topCard), c);
    }

    private void initInfoView() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 130, 0, 45);
        add(table,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 1, 0, 1);
        // add(infoPanel, c);
    }

    public void discard(Card playedCard){
        table.removeAll();
        topCard = playedCard;
        initTable();

        setBackgroundColor();
    }

    private void setBackgroundColor(){
        Color background = StyleUtil.convertCardColor(topCard.getColor());
        table.setBackground(background);
    }
}
