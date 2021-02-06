package ui.view;

import application.IGameAppService;
import domain.common.DomainEvent;
import domain.common.DomainEventPublisher;
import domain.common.DomainEventSubscriber;
import domain.game.events.CardDrawn;
import domain.game.events.CardPlayed;
import domain.game.events.GameOver;

import javax.swing.*;
import java.awt.*;

public class GameStatusView extends JPanel implements DomainEventSubscriber {
    private String error;
    private String text;
    private int panelCenter;

    private final IGameAppService appService;

    public GameStatusView(IGameAppService appService) {
        this.appService = appService;

        setPreferredSize(new Dimension(275, 200));
        setOpaque(false);
        error = "";

        updateStatus();

        DomainEventPublisher.subscribe(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelCenter = getWidth() / 2;

        printMessage(g);
        printError(g);
    }

    private void printError(Graphics g) {
        if (!error.isEmpty()) {
            Font adjustedFont = new Font("Calibri", Font.PLAIN, 25);

            //Determine the width of the word to position
            FontMetrics fm = this.getFontMetrics(adjustedFont);
            int xPos = panelCenter - fm.stringWidth(error) / 2;

            g.setFont(adjustedFont);
            g.setColor(Color.red);
            g.drawString(error, xPos, 35);

            error = "";
        }
    }

    private void printMessage(Graphics g) {
        Font adjustedFont = new Font("Calibri", Font.BOLD, 25);

        //Determine the width of the word to position
        FontMetrics fm = this.getFontMetrics(adjustedFont);
        int xPos = panelCenter - fm.stringWidth(text) / 2;

        g.setFont(adjustedFont);
        g.setColor(new Color(228, 108, 10));
        g.drawString(text, xPos, 75);
    }

    private void updateStatus() {
        if (appService.isGameOver()) {
            text = String.format("%s won", appService.getWinner().getName());
        } else {
            text = String.format("%s's turn", appService.getCurrentPlayer().getName());
        }

        repaint();
    }

    public void setError(String errorMgs) {
        error = errorMgs;
    }

    @Override
    public void handleEvent(DomainEvent event) {
        if (event instanceof CardPlayed
            || event instanceof CardDrawn
            || event instanceof GameOver) {
            updateStatus();
        }
    }
}
