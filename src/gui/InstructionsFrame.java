package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stephanie on 19/10/2016.
 */

public class InstructionsFrame extends JFrame implements ActionListener {
    JPanel titlePanel = new JPanel();
    JPanel instructionsPanel = new InstructionsPanel();
    JPanel backPanel = new JPanel();

    BorderLayout layout = new BorderLayout();
    Font titleFont = new Font("Monotype Corsiva", Font.PLAIN, 45);
    Font font = new Font("Calibri", Font.PLAIN, 30);
    JLabel title = new JLabel("Instructions");
    JLabel instructions = new JLabel();
    Button backButton = new Button("Back");

    Color background = new Color(28, 103, 116);
    Dimension buttonSize = new Dimension(300, 50);

    MenuFrame parentFrame = null;

    public InstructionsFrame(MenuFrame parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(layout);
        setTitle("Supertrump Minerals");
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

        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        parentFrame.setVisible(true);
        this.dispose();
    }
}
