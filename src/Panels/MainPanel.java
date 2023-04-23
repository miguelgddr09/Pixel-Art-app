package Panels;

import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Menu;

public class MainPanel extends JPanel{
  private JButton backToMenuBtn;
  public JPanel colorSquare;
  public JPanel sidebar, gridPanel;
  private final int rows;
  private final int cols;
  private final JPanel[][] squares;
  public Color pencil;

  public JSlider redSlider = new JSlider(0, 255),
   greenSlider = new JSlider(0, 255),
   blueSlider = new JSlider(0, 255);


  public MainPanel(int rows, int cols) {
    pencil = getCurrColor(0, 0, 0);
    this.rows = rows;
    this.cols = cols;
    this.squares = new JPanel[rows][cols];
    sidebar = new JPanel();
    gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(rows, cols));
    gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    sidebar.setPreferredSize(new Dimension(300, 600));
    sidebar.setBackground(Color.darkGray);

    backToMenuBtn = new JButton("Back to Menu");
    backToMenuBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Remove the sidebar and grid and add the menu
            Container parent = sidebar.getParent();
            parent.removeAll();
            parent.add(new Menu(), BorderLayout.CENTER);
            parent.revalidate();
            parent.repaint();
        }
    });



    // Create the red range input
        JPanel redPanel = new JPanel();
        redPanel.setLayout(new BoxLayout(redPanel, BoxLayout.Y_AXIS));
        JLabel redLabel = new JLabel("Red");
        redSlider = new JSlider(0, 255, 0);
        redSlider.setMajorTickSpacing(50);
        redSlider.setMinorTickSpacing(10);
        redSlider.setPaintTicks(true);
        redSlider.setPaintLabels(true);
        redSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        redPanel.add(redLabel);
        redPanel.add(redSlider);
        redSlider.setValue(0);
        redSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int red = redSlider.getValue();
                int green = greenSlider.getValue();
                int blue = blueSlider.getValue();
                updateColorSquare(red, green, blue);
                getCurrColor(red, green, blue);
            }
        });

    // Create the green range input
        JPanel greenPanel = new JPanel();
        greenPanel.setLayout(new BoxLayout(greenPanel, BoxLayout.Y_AXIS));
        JLabel greenLabel = new JLabel("Green");
        greenSlider = new JSlider(0, 255, 0);
        greenSlider.setMajorTickSpacing(50);
        greenSlider.setMinorTickSpacing(10);
        greenSlider.setPaintTicks(true);
        greenSlider.setPaintLabels(true);
        greenSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        greenPanel.add(greenLabel);
        greenPanel.add(greenSlider);
        greenSlider.setValue(0);
        greenSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int red = redSlider.getValue();
                int green = greenSlider.getValue();
                int blue = blueSlider.getValue();
                updateColorSquare(red, green, blue);
                getCurrColor(red, green, blue);
            }
        });

    // Create the blue range input
        JPanel bluePanel = new JPanel();
        bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.Y_AXIS));
        JLabel blueLabel = new JLabel("Blue");
        blueSlider = new JSlider(0, 255, 0);
        blueSlider.setMajorTickSpacing(50);
        blueSlider.setMinorTickSpacing(10);
        blueSlider.setPaintTicks(true);
        blueSlider.setPaintLabels(true);
        blueSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        bluePanel.add(blueLabel);
        bluePanel.add(blueSlider);
        blueSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int red = redSlider.getValue();
                int green = greenSlider.getValue();
                int blue = blueSlider.getValue();
                updateColorSquare(red, green, blue);
                getCurrColor(red, green, blue);
            }
        });


        // Add the back button to the panel with some margin
     JPanel topPanel = new JPanel();
     topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
     topPanel.setBackground(Color.darkGray);
     topPanel.add(backToMenuBtn);
     sidebar.add(topPanel, BorderLayout.NORTH);

     //adding range panels
      JPanel inputPanel = new JPanel();
      inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
      inputPanel.setBackground(Color.darkGray);
      inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      inputPanel.add(redPanel);
      inputPanel.add(greenPanel);
      inputPanel.add(bluePanel);
      sidebar.add(inputPanel, BorderLayout.CENTER);

      // Create the color square panel
      colorSquare = new JPanel();
      colorSquare.setPreferredSize(new Dimension(200, 200));
      colorSquare.setBackground(Color.black);
      sidebar.add(colorSquare, BorderLayout.SOUTH);


    for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JPanel square = new JPanel();
                square.setPreferredSize(new Dimension(50, 50)); // Set the size of each square
                square.setBackground(Color.white);
                square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                gridPanel.add(square);
                squares[row][col] = square;
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JPanel square = squares[row][col];
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (e.isShiftDown()) {
                          System.out.println(pencil);
                          square.setBackground(pencil);
                          //
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                      square.setBackground(pencil);
                    }
                    
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                });
            }
        }
    
    setLayout(new BorderLayout());
    add(sidebar, BorderLayout.WEST);
    add(gridPanel, BorderLayout.CENTER);
  }

  public JPanel updateColorSquare(int red, int green, int blue) {
    colorSquare.setBackground(new Color(red, green, blue));
    return colorSquare;
  }

  public Color getCurrColor(int red, int green, int blue) {
    Color drawingColor = new Color(red, green, blue);
    pencil = drawingColor;
    return drawingColor;
  }


}
