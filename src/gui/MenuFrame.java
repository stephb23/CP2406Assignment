package gui;

import guiGameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 19/10/2016.
 *
 * This is the main menu frame
 *
 */
public class MenuFrame extends JFrame {
    private JPanel titlePanel = new JPanel();
    private JPanel newGamePanel = new JPanel();
    private JPanel instructionsPanel = new JPanel();
    private JPanel aboutGamePanel = new JPanel();

    private GridLayout layout = new GridLayout(0, 1);
    private Font titleFont = new Font("Monotype Corsiva", Font.PLAIN, 45);
    private Font font = new Font("Calibri", Font.PLAIN, 30);
    private JLabel title = new JLabel("Welcome to Supertrump Minerals!");
    private Button newGameButton = new Button("Start a Game");
    private Button instructionsButton = new Button("Instructions");
    private Button aboutGameButton = new Button("About the Game");

    private Color background = new Color(28, 103, 116);
    private Dimension buttonSize = new Dimension(300, 50);

    public MenuFrame() {
        setLayout(layout);
        setTitle("Supertrump Minerals");
        setSize(600, 400);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        titlePanel.setBackground(background);
        newGamePanel.setBackground(background);
        instructionsPanel.setBackground(background);
        aboutGamePanel.setBackground(background);

        title.setFont(titleFont);
        title.setForeground(Color.WHITE);

        newGameButton.setFont(font);
        newGameButton.setPreferredSize(buttonSize);

        instructionsButton.setFont(font);
        instructionsButton.setPreferredSize(buttonSize);

        aboutGameButton.setFont(font);
        aboutGameButton.setPreferredSize(buttonSize);

        titlePanel.add(title);
        newGamePanel.add(newGameButton);
        instructionsPanel.add(instructionsButton);
        aboutGamePanel.add(aboutGameButton);

        add(titlePanel);
        add(newGamePanel);
        add(instructionsPanel);
        add(aboutGamePanel);

        newGameButton.addActionListener(GUIGameRunner.startGameListener);
        instructionsButton.addActionListener(GUIGameRunner.instructionsListener);
        aboutGameButton.addActionListener(GUIGameRunner.aboutGameListener);
    }



}
