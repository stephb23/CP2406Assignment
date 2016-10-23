package gui;

import javax.swing.*;

/**
 * Created by Stephanie on 23/10/2016.
 */
public class GameOverDialog extends JDialog {
    JLabel message = new JLabel("Game over!");
    JPanel panel = new JPanel();

    public GameOverDialog() {
        panel.add(message);
        add(panel);

        setSize(300, 300);
        setVisible(true);
    }
}
