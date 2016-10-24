package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 23/10/2016.
 *
 * This panel draws AI icons
 *
 */
public class AIPlayerPanel extends JPanel {

    private Color color = Color.LIGHT_GRAY;
    String string = "";
    private int rightOrLeft;
    private Dimension dim;
    private String playerName;

    Color backgroundColour = new Color(28, 103, 116);

    public AIPlayerPanel(int rightOrLeft, String playerName) {
        this.rightOrLeft = rightOrLeft;
        this.playerName = playerName;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(dim.width/4, dim.height/4));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (rightOrLeft == 0) {
            g.setColor(color);
            g.drawRect(0, 0, 50, 100);
            g.fillRect(0, 0, 50, 100);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString(string, 20, 50);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString(playerName, 55, 50);
        } else {
            g.setColor(color);
            g.drawRect(dim.width/4 - 115, 0, 50, 100);
            g.fillRect(dim.width/4 - 115, 0, 50, 100);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString(string, dim.width/4 - 100, 50);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString(playerName, dim.width/4 - 180, 50);
        }
    }

    public void setPlaying() {
        color = Color.YELLOW;
        string = "";
        repaint();
    }

    public void setInactive(String string) {
        color = Color.DARK_GRAY;
        this.string = string;
        repaint();
    }

    public void setNotPlaying() {
        color = Color.LIGHT_GRAY;
        string = "";
        repaint();
    }

    public void setNotInGame() {
        color = backgroundColour;
        string = "";
        repaint();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        repaint();
    }

}
