package gui;

import guiGameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 19/10/2016.
 *
 * This frame holds the instructions panel
 *
 */

public class InstructionsFrame extends JFrame {
    private JPanel titlePanel = new JPanel();
    private JPanel instructionsPanel = new InstructionsPanel();
    private JPanel backPanel = new JPanel();

    private BorderLayout layout = new BorderLayout();
    private Font titleFont = new Font("Monotype Corsiva", Font.PLAIN, 45);
    private Font font = new Font("Calibri", Font.PLAIN, 30);
    private JLabel title = new JLabel("Instructions");
    private JLabel instructions = new JLabel();
    private Button backButton = new Button("Back");

    private Color background = new Color(28, 103, 116);
    private Dimension buttonSize = new Dimension(300, 50);

    public InstructionsFrame() {
        setLayout(layout);
        setTitle("Supertrump Minerals - Instructions");
        setResizable(false);
        setSize(900, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        titlePanel.setBackground(background);
        instructionsPanel.setBackground(background);
        backPanel.setBackground(background);

        title.setFont(titleFont);
        title.setForeground(Color.WHITE);

        backButton.setFont(font);
        backButton.setPreferredSize(buttonSize);
        titlePanel.add(title);

        instructionsPanel.add(instructions);
        backPanel.add(backButton);

        add(titlePanel, BorderLayout.NORTH);
        add(instructionsPanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);

        backButton.addActionListener(GUIGameRunner.backToMenuListener);
    }
}
