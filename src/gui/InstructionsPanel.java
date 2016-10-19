package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 19/10/2016.
 */
public class InstructionsPanel extends JPanel {

    GridLayout layout = new GridLayout(0, 1);
    JLabel[] instructions = new JLabel[12];
    Font font = new Font("Arial", Font.PLAIN, 20);

    InstructionsPanel() {
        setBackground(new Color(28, 103, 116));
        setLayout(layout);

        instructions[0] = new JLabel("1. You will be asked to choose the number of players you want to verse");
        instructions[1] = new JLabel("2. A dealer will be chosen randomly, and each player given 8 cards");
        instructions[2] = new JLabel("3. The dealer will play first, choosing a card and a trump category");
        instructions[3] = new JLabel("4. Each player after the dealer must play a card with a higher value in that trump category,");
        instructions[4] = new JLabel(" play a trump card, or choose to pass");
        instructions[5] = new JLabel("5. If a player passes, they pick up a card and remain out of the game until the next round");
        instructions[6] = new JLabel(" starts, or until a trump card is played");
        instructions[7] = new JLabel("6. If a trump card is played, the category is changed to the category specified on the card");
        instructions[8] = new JLabel("7. The player who throws a trump card then immediately gets to play another card");
        instructions[9] = new JLabel("8. If a player plays The Geophysicist followed by Magnetite, they win the round immediately");
        instructions[10] = new JLabel("9. A round continues until all but one player has passed; they become the winning player");
        instructions[11] = new JLabel("10. A game continues until all but one player has lost all of their cards");

        for (int i = 0 ; i < instructions.length; i++) {
            instructions[i].setFont(font);
            instructions[i].setForeground(Color.WHITE);
            instructions[i].setVerticalTextPosition(JLabel.CENTER);
            instructions[i].setHorizontalAlignment(JLabel.CENTER);
            add(instructions[i]);
        }
    }

}
