package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Panels.MainPanel;


public class Menu extends JPanel {
    private JButton showButton;



    public Menu() {
        // Set the background color and preferred size of the panel
        setBackground(Color.darkGray);
        setPreferredSize(new Dimension(800, 600));

        // Create a grid bag layout with constraints for the button
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;

        // Create the "Show Grid" button and add it to the panel
        showButton = new JButton("Show Grid");
        add(showButton, gbc);

        // Add an ActionListener to the button to handle clicks
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Remove all components from the panel
                removeAll();

                // Add the "MainPanel" to the center of the panel
                MainPanel mainPanel = new MainPanel(40, 40);
                setLayout(new BorderLayout());
                add(mainPanel, BorderLayout.CENTER);


                // Repaint the panel to show the changes
                revalidate();
                repaint();
            }
        });

        // Add a KeyListener to the button to handle keyboard events
        showButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    showButton.doClick();
                }
            }
        });
    }
}
