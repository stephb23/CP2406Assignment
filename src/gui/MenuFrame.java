package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stephanie on 19/10/2016.
 */
public class MenuFrame extends JFrame implements ActionListener {
    JPanel titlePanel = new JPanel();
    JPanel newGamePanel = new JPanel();
    JPanel instructionsPanel = new JPanel();
    JPanel aboutGamePanel = new JPanel();

    GridLayout layout = new GridLayout(0, 1);
    Font titleFont = new Font("Monotype Corsiva", Font.PLAIN, 45);
    Font font = new Font("Calibri", Font.PLAIN, 30);
    JLabel title = new JLabel("Welcome to Supertrump Minerals!");
    Button newGameButton = new Button("Start a Game");
    Button instructionsButton = new Button("Instructions");
    Button aboutGameButton = new Button("About the Game");

    Color background = new Color(28, 103, 116);
    Dimension buttonSize = new Dimension(300, 50);

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

        instructionsButton.addActionListener(this);
        aboutGameButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == instructionsButton) {
            InstructionsFrame instructionsFrame = new InstructionsFrame(this);
            instructionsFrame.setVisible(true);
            this.setVisible(false);
        } else if (source == aboutGameButton) {
            AboutGameFrame aboutGameFrame = new AboutGameFrame(this);
            aboutGameFrame.setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        MenuFrame frame = new MenuFrame();
        frame.setVisible(true);
    }
}
