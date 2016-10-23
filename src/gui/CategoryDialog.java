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

    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JPanel categoriesPanel = new JPanel();

    Color background = new Color(28, 103, 116);

    public CategoryDialog() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(gridLayout);
        setSize(300, 200);
        setAlwaysOnTop(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        titlePanel.setBackground(background);
        title.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
        title.setForeground(Color.WHITE);
        titlePanel.add(title);
        add(titlePanel);

        categoriesPanel.setBackground(background);
        categoriesPanel.add(categories);
        add(categoriesPanel);

        buttonPanel.setBackground(background);
        buttonPanel.add(confirm);
        add(buttonPanel);

        confirm.addActionListener(GUIGameRunner.categoryListener);
    }

    public String getCategory(){
        System.out.println(categories.getSelectedItem().toString().toLowerCase());
        return categories.getSelectedItem().toString().toLowerCase();
    }
}
