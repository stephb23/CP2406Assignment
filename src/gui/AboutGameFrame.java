package gui;

import guiGameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 19/10/2016.
 *
 * This is the frame that holds the About Game panel with its information
 *
 */

public class AboutGameFrame extends JFrame {
    private JPanel titlePanel = new JPanel();
    private JPanel aboutGamePanel = new AboutGamePanel();
    private JPanel backPanel = new JPanel();

    private BorderLayout layout = new BorderLayout();
    private Font titleFont = new Font("Monotype Corsiva", Font.PLAIN, 45);
    private Font font = new Font("Calibri", Font.PLAIN, 30);
    private JLabel title = new JLabel("About");
    private JLabel instructions = new JLabel();
    private Button backButton = new Button("Back");

    private Color background = new Color(28, 103, 116);
    private Dimension buttonSize = new Dimension(300, 50);

    public AboutGameFrame() {

        setLayout(layout);
        setTitle("About Supertrump Minerals");
        setSize(1050, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        titlePanel.setBackground(background);
        aboutGamePanel.setBackground(background);
        backPanel.setBackground(background);

        title.setFont(titleFont);
        title.setForeground(Color.WHITE);

        backButton.setFont(font);
        backButton.setPreferredSize(buttonSize);
        titlePanel.add(title);

        aboutGamePanel.add(instructions);
        backPanel.add(backButton);

        add(titlePanel, BorderLayout.NORTH);
        add(aboutGamePanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);

        backButton.addActionListener(GUIGameRunner.backToMenuListener);
    }
}
