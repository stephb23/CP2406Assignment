package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 23/10/2016.
 */
public class AIPlayerPanel extends JPanel {

    Color color = Color.LIGHT_GRAY;
    String string = "";
    int rightOrLeft;
    Dimension dim;

    Color backgroundColour = new Color(28, 103, 116);

    public AIPlayerPanel(int rightOrLeft) {
        this.rightOrLeft = rightOrLeft;
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
        } else {
            g.setColor(color);
            g.drawRect(dim.width/4 - 115, 0, 50, 100);
            g.fillRect(dim.width/4 - 115, 0, 50, 100);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString(string, 180, 150);
        }
    }

    public void setPlaying() {
        color = Color.YELLOW;
        string = "";
        repaint();
    }

    public void setInactive() {
        color = Color.DARK_GRAY;
        string = "F";
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

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 500));
        AIPlayerPanel panel = new AIPlayerPanel(0);
        frame.add(panel);
        frame.setVisible(true);
        for (int i = 0; i < 1000000000; i++);
        panel.setPlaying();
        for (int i = 0; i < 1000000000; i++);
        panel.setInactive();
    }


}
