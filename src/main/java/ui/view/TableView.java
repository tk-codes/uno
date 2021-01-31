package ui.view;

import application.IGameAppService;
import ui.common.StyleUtil;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {
    private final JPanel table;
    private final IGameAppService appService;

    public TableView(IGameAppService appService){
        this.appService = appService;

        setOpaque(false);
        setLayout(new GridBagLayout());
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
        table.add(new CardView(appService.peekTopCard()), c);
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

        add(new GameStatusView(), c);
    }

    public void discard(){
        table.removeAll();
        initTable();

        setBackgroundColor();
    }

    private void setBackgroundColor(){
        Color background = StyleUtil.convertCardColor(appService.peekTopCard().getColor());
        table.setBackground(background);
    }
}
