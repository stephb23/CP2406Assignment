package gui;

import gameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 21/10/2016.
 */
public class PassQuitPanel extends JPanel {
    GridLayout layout = new GridLayout(0, 1);
    JPanel passPanel = new JPanel();
    Button passButton = new Button("Pass");
    JPanel quitPanel = new JPanel();
    Button quitButton = new Button("Quit");

    public PassQuitPanel() {
        setLayout(layout);
        passPanel.add(passButton);
        quitPanel.add(quitButton);
        add(passPanel);
        add(quitPanel);

        passButton.addActionListener(GUIGameRunner.passListener);
        quitButton.addActionListener(GUIGameRunner.quitListener);
    }
}
