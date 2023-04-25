package main;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import Panels.MainPanel;


public class Menu extends JPanel {
    private JButton showButton, showCurrCanvas;
    protected JCheckBox checkBox1, checkBox2, checkBox3, checkBox4;
    public int gridSize = 40;

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
        showButton = new JButton("start new canvas");
        add(showButton, gbc);

        showCurrCanvas = new JButton("load canvas");
        gbc.gridy++;
        add(showCurrCanvas, gbc);

        showCurrCanvas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                fileChooser.showSaveDialog(null);
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    String path = selectedFile.getAbsolutePath();
                    if (path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".gif")) {
                    //Save the image at the selected path
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid file format! Please select PNG, JPG or GIF.", 
                                           "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } 
 
            } 
        });
        

        ButtonGroup buttonGroup = new ButtonGroup();
        
        // Create the checkboxes and add them to the panel and the button group
        checkBox1 = new JCheckBox("40x40");
        checkBox1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        checkBox1.setBackground(Color.darkGray);
        checkBox1.setForeground(Color.white);
        checkBox1.setPreferredSize(new Dimension(60, 60));
        checkBox1.setSelected(true);
        checkBox1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                gridSize = 40;
                System.out.println(gridSize);
                checkBox2.setSelected(false);
                checkBox3.setSelected(false);
                checkBox4.setSelected(false);
            }
        });
        gbc.gridy++;
        add(checkBox1, gbc);
        buttonGroup.add(checkBox1);

        checkBox2 = new JCheckBox("45x45");
        checkBox2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        checkBox2.setBackground(Color.darkGray);
        checkBox2.setForeground(Color.white);
        checkBox2.setPreferredSize(new Dimension(60, 60));
        checkBox2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                gridSize = 45;
                System.out.println(gridSize);
                checkBox1.setSelected(false);
                checkBox3.setSelected(false);
                checkBox4.setSelected(false);
            }
        });
        gbc.gridy++;
        add(checkBox2, gbc);
        buttonGroup.add(checkBox2);

        checkBox3 = new JCheckBox("50x50");
        checkBox3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        checkBox3.setBackground(Color.darkGray);
        checkBox3.setForeground(Color.white);
        checkBox3.setPreferredSize(new Dimension(60, 60));
        checkBox3.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                gridSize = 50;
                System.out.println(gridSize);
                checkBox1.setSelected(false);
                checkBox2.setSelected(false);
                checkBox4.setSelected(false);
            }
        });
        gbc.gridy++;
        add(checkBox3, gbc);
        buttonGroup.add(checkBox3);

        checkBox4 = new JCheckBox("55x55");
        checkBox4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        checkBox4.setBackground(Color.darkGray);
        checkBox4.setForeground(Color.white);
        checkBox4.setPreferredSize(new Dimension(60, 60));
        checkBox4.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                gridSize = 55;
                System.out.println(gridSize);
                checkBox1.setSelected(false);
                checkBox2.setSelected(false);
                checkBox3.setSelected(false);
            }
        });
        gbc.gridy++;
        add(checkBox4, gbc);
        buttonGroup.add(checkBox4);

        


        // Add an ActionListener to the button to handle clicks
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Remove all components from the panel
                removeAll();

                // Add the "MainPanel" to the center of the panel
                MainPanel mainPanel = new MainPanel(gridSize, gridSize);
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
