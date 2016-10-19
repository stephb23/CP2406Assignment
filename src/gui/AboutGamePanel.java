package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 19/10/2016.
 */
public class AboutGamePanel extends JPanel {

    GridLayout layout = new GridLayout(0, 1);
    JLabel[] aboutGame = new JLabel[15];
    Font font = new Font("Arial", Font.PLAIN, 20);

    AboutGamePanel() {
        setBackground(new Color(28, 103, 116));
        setLayout(layout);

        aboutGame[0] = new JLabel("~ ABOUT THE GAME ~");
        aboutGame[1] = new JLabel("Mineral Supertrumps is designed to teach players about the properties and uses of common rocks.");
        aboutGame[2] = new JLabel("Each pack contains 54 mineral cards and 6 'supertrump' cards. Each mineral card contains");
        aboutGame[3] = new JLabel("information including chemical formula, classification, crystal system, and where the mineral commonly found.");
        aboutGame[4] = new JLabel("Additionally, information in the five playing categories - hardness, specific gravity, cleavage,");
        aboutGame[5] = new JLabel("crustal abundance and economic value, is included in each card.");
        aboutGame[6] = new JLabel("This game is designed for 3-5 players, and the objective is to be the first player to lose");
        aboutGame[7] = new JLabel("all of your cards!");
        aboutGame[8] = new JLabel("");
        aboutGame[9] = new JLabel("~ ABOUT THE TRUMP CATEGORIES ~");
        aboutGame[10] = new JLabel("Hardness: relates to Moh's hardness scale, from 1-10.");
        aboutGame[11] = new JLabel("Specific Gravity: the density of the mineral with respect to pure water");
        aboutGame[12] = new JLabel("Cleavage: the number of cleavage planes and how well the planes are expressed in the crystal");
        aboutGame[13] = new JLabel("Crustal abundance: how commonly the mineral is found in the Earth's crust");
        aboutGame[14] = new JLabel("Economic value: the monetary worth of the mineral");

        for (int i = 0 ; i < aboutGame.length; i++) {
            aboutGame[i].setFont(font);
            aboutGame[i].setForeground(Color.WHITE);
            aboutGame[i].setVerticalTextPosition(JLabel.CENTER);
            aboutGame[i].setHorizontalAlignment(JLabel.CENTER);
            add(aboutGame[i]);
        }
    }

}
