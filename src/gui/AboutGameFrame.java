package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stephanie on 19/10/2016.
 */

public class AboutGameFrame extends JFrame implements ActionListener {
    JPanel titlePanel = new JPanel();
    JPanel aboutGamePanel = new AboutGamePanel();
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

    public AboutGameFrame(MenuFrame parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(layout);
        setTitle("About Supertrump Minerals");
        setSize(1050, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

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

        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        parentFrame.setVisible(true);
        this.dispose();
    }
}
