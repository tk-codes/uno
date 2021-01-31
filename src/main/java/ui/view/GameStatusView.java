package ui.view;

import javax.swing.*;
import java.awt.*;

public class GameStatusView extends JPanel {
    private String error;
    private String text;
    private int panelCenter;

    public GameStatusView(){
        setPreferredSize(new Dimension(275,200));
        setOpaque(false);
        error = "";
        text = "Game Started";

        updateText(text);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelCenter = getWidth()/2;

        printMessage(g);
        printError(g);
    }

    private void printError(Graphics g) {
        if(!error.isEmpty()){
            Font adjustedFont = new Font("Calibri", Font.PLAIN,	25);

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
        Font adjustedFont = new Font("Calibri", Font.BOLD,	25);

        //Determine the width of the word to position
        FontMetrics fm = this.getFontMetrics(adjustedFont);
        int xPos = panelCenter - fm.stringWidth(text) / 2;

        g.setFont(adjustedFont);
        g.setColor(new Color(228,108,10));
        g.drawString(text, xPos, 75);
    }

    public void updateText(String newText) {
        text = newText;
    }

    public void setError(String errorMgs){
        error = errorMgs;
    }
}
