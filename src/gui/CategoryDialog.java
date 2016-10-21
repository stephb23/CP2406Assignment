package gui;

import gameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 21/10/2016.
 */
public class CategoryDialog extends JDialog {
    private String[] categoryStrings = {"Hardness", "Specific Gravity", "Cleavage", "Crustal Abundance", "Economic Value"};
    private JComboBox categories = new JComboBox(categoryStrings);
    private JLabel title = new JLabel("Choose a category: ");
    private Button confirm = new Button("Confirm");
    private GridLayout gridLayout = new GridLayout(0, 1);

    public CategoryDialog() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(gridLayout);
        setSize(300, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        add(title);
        add(categories);
        add(confirm);
        confirm.addActionListener(GUIGameRunner.categoryListener);
    }

    public String getCategory(){
        System.out.println(categories.getSelectedItem().toString().toLowerCase());
        return categories.getSelectedItem().toString().toLowerCase();
    }
}
