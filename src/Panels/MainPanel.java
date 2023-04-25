package Panels;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.App;
import main.Menu;
import textUI.TextUI;

public class MainPanel extends JPanel {
  private JButton backToMenuBtn, savingOpt;
  protected JButton pngButton, jpgButton, gifButton;
  protected JButton penButton, eraserButton, eraseAll;
  protected JDialog dialog;
  public JPanel colorSquare;
  public JPanel sidebar, gridPanel, textPanel;
  private final int rows;
  private final int cols;
  private final JPanel[][] squares;
  public Color pencil;
  protected String format = "";
  protected boolean isPen = true, isEraser = false, isEraseAll = false;
  public JPanel square;
  public int redVal = 0, greenVal = 0, blueVal = 0;
  protected boolean smallLoop = false;

  protected Color[] colors = { Color.BLACK, Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.PINK };
  public Color selectedColor;

  protected String mFilename = "";

  JFrame frame = App.frame;
  TextUI textmanager = new TextUI(5, 500, 300, 300, Color.white); 


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
    sidebar.setPreferredSize(new Dimension(220, 0));
    sidebar.setBackground(Color.darkGray);

    textPanel = new JPanel();
    textPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0 , 0));
    //textPanel.setPreferredSize(new Dimension(1, 25));
    textPanel.setBackground(Color.darkGray);
    
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
        redSlider = new JSlider(0, 255, redVal);
        redSlider.setMajorTickSpacing(50);
        redSlider.setMinorTickSpacing(10);
        redSlider.setPaintTicks(true);
        redSlider.setPaintLabels(true);
        redSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        redSlider.setBackground(Color.gray);
        redPanel.add(redLabel);
        redPanel.add(redSlider);
        redSlider.setValue(0);
        redSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int red = redSlider.getValue();
                redVal = redSlider.getValue();
                int green = greenSlider.getValue();
                greenVal = greenSlider.getValue();
                int blue = blueSlider.getValue();
                blueVal = blueSlider.getValue();
                getCurrColor(red, green, blue);
                updateColorSquare(red, green, blue);
            }
        });

    // Create the green range input
        JPanel greenPanel = new JPanel();
        greenPanel.setLayout(new BoxLayout(greenPanel, BoxLayout.Y_AXIS));
        JLabel greenLabel = new JLabel("Green");
        greenSlider = new JSlider(0, 255, greenVal);
        greenSlider.setMajorTickSpacing(50);
        greenSlider.setMinorTickSpacing(10);
        greenSlider.setPaintTicks(true);
        greenSlider.setPaintLabels(true);
        greenSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        greenSlider.setBackground(Color.gray);
        greenPanel.add(greenLabel);
        greenPanel.add(greenSlider);
        greenSlider.setValue(0);
        greenSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int red = redSlider.getValue();
                redVal = redSlider.getValue();
                int green = greenSlider.getValue();
                greenVal = greenSlider.getValue();
                int blue = blueSlider.getValue();
                blueVal = blueSlider.getValue();
                getCurrColor(red, green, blue);
                updateColorSquare(red, green, blue);
            }
        });

    // Create the blue range input
        JPanel bluePanel = new JPanel();
        bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.Y_AXIS));
        JLabel blueLabel = new JLabel("Blue");
        blueSlider = new JSlider(0, 255, blueVal);
        blueSlider.setMajorTickSpacing(50);
        blueSlider.setMinorTickSpacing(10);
        blueSlider.setPaintTicks(true);
        blueSlider.setPaintLabels(true);
        blueSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        blueSlider.setBackground(Color.gray);
        bluePanel.add(blueLabel);
        bluePanel.add(blueSlider);
        blueSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int red = redSlider.getValue();
                redVal = redSlider.getValue();
                int green = greenSlider.getValue();
                greenVal = greenSlider.getValue();
                int blue = blueSlider.getValue();
                blueVal = blueSlider.getValue();
                getCurrColor(red, green, blue);
                updateColorSquare(red, green, blue);
            }
        });


        // Add the back button to the panel with some margin
     JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5 , 0));
     topPanel.setBackground(Color.darkGray);
     topPanel.add(backToMenuBtn);
     //sidebar.add(topPanel, BorderLayout.NORTH);

     //adding range panels
      JPanel inputPanel = new JPanel();
      inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
      inputPanel.setBackground(Color.darkGray);
      inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      inputPanel.add(redPanel);
      inputPanel.add(greenPanel);
      inputPanel.add(bluePanel);
      sidebar.add(inputPanel, BorderLayout.CENTER);

      JPanel defaultColorsPanel = new JPanel();
      defaultColorsPanel.setLayout(new GridLayout(2, 0));
      defaultColorsPanel.setBackground(Color.darkGray);
      // loop through the colors array and create a button for each color
      for (Color c : colors) {
      JButton colorButton = new JButton();
      colorButton.setPreferredSize(new Dimension(20, 20));
      colorButton.setBackground(c);
      colorButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // set the selected color to the button's background color
            selectedColor = c;
            smallLoop = true;
            if (smallLoop) {
              updateBlueSilder(selectedColor);
              updateRedSlider(selectedColor);
              updateGreenSlider(selectedColor);
              smallLoop = false;
            }
        }
    });
    defaultColorsPanel.add(colorButton);
    }

    sidebar.add(defaultColorsPanel, BorderLayout.CENTER);

      // Create pen & eraser buttons
      JPanel middPanel = new JPanel();
      middPanel.setLayout(new BoxLayout(middPanel, BoxLayout.Y_AXIS));
      middPanel.setBackground(Color.gray);
      penButton =  new JButton("pen");
      eraserButton = new JButton("eraser");
      eraseAll = new JButton("erase all");

      penButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          isEraser = false;
          isPen = true;
          textmanager.setText("press \"shift\" to draw continuously \t pen on");
          System.out.println("pen " +  isPen);
          System.out.println("eraser " +  isEraser);
        }
      });

      eraserButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          isEraser = true;
          isPen = false;
          textmanager.setText("press \"shift\" to erase continously \t eraser on");
          System.out.println("pen " +  isPen);
          System.out.println("eraser " +  isEraser);
        }
      });

      eraseAll.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          isEraseAll = true;
          System.out.println("erasing all");

          if (isEraseAll) {
            // erase the grid
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    squares[row][col].setBackground(Color.white);
                }
            }

            isEraseAll = false;
          }
        }
      });

      middPanel.add(penButton);
      middPanel.add(eraserButton);
      middPanel.add(eraseAll);

      sidebar.add(middPanel, BorderLayout.CENTER);

      // Create the color square panel
      colorSquare = new JPanel();
      colorSquare.setPreferredSize(new Dimension(200, 200));
      colorSquare.setBackground(Color.black);
      sidebar.add(colorSquare, BorderLayout.SOUTH);


    for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                //JPanel square = new JPanel();
                square = new JPanel();
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
                          if(isPen) {
                            square.setBackground(pencil);
                          }

                          if(isEraser) {
                            square.setBackground(Color.white);
                          }
                          //
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                    
                    @Override
                    public void mousePressed(MouseEvent e) {
                      if(isPen) {
                        square.setBackground(pencil);
                      }

                      if(isEraser) {
                        square.setBackground(Color.white);
                      }
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
    add(textPanel, BorderLayout.PAGE_START);


    //default mess
    textmanager.setText("press \"shift\" to draw continuously \t pen on");
    textPanel.add(textmanager.getLabel());

    savingOpt = new JButton("Save");
    savingOpt.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Crear el diálogo modal
        dialog = new JDialog(frame, "Save As", true);
        
        // Crear el panel con los botones
        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel fieldInstructions = new JLabel("Enter name of the file:");
        JTextField mTextInputField = new JTextField(20);
        pngButton = new JButton("PNG");
        jpgButton = new JButton("JPG");
        gifButton = new JButton("GIF");
        panel.add(fieldInstructions);
        panel.add(mTextInputField);
        panel.add(pngButton);
        panel.add(jpgButton);
        panel.add(gifButton);

        
        
        // Agregar el panel al diálogo
        dialog.add(panel);
        
        pngButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            mFilename = mTextInputField.getText();
            System.out.println(mFilename); 
            saveAsPng();
            dialog.dispose();
          }
        });

        jpgButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e ) {
            mFilename = mTextInputField.getText();
            saveAsJpg();
            dialog.dispose();
          }
        });

        gifButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            mFilename = mTextInputField.getText();
            saveAsGif();
            dialog.dispose();
          }
        });
        
        // Configurar el diálogo
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
        

      }
    });
       
    savingOpt.setLayout(new BoxLayout( savingOpt, BoxLayout.Y_AXIS));
    savingOpt.setAlignmentX(LEFT_ALIGNMENT);
    topPanel.add(savingOpt);

     sidebar.add(topPanel, BorderLayout.NORTH);

  


  }

  public JPanel updateColorSquare(int red, int green, int blue) {
    //colorSquare.setBackground(new Color(red, green, blue));
    colorSquare.setBackground(pencil);
    return colorSquare;
  }

  public Color getCurrColor(int red, int green, int blue) {
    Color drawingColor = new Color(red, green, blue);
    pencil = drawingColor;
    return drawingColor;
  }

  public void updateRedSlider (Color theColor) {
    redVal = theColor.getRed();
    redSlider.setValue(redVal);
  }

  public void updateGreenSlider (Color theColor) {
    greenVal = theColor.getGreen();
    greenSlider.setValue(greenVal);
  }

  public void updateBlueSilder (Color theColor) {
    blueVal = theColor.getBlue();
    blueSlider.setValue(blueVal);
  }

  public void saveAsPng() {
    format = "png";
    System.out.println(format);
    saveAs(mFilename, format);
  }

  public void saveAsJpg() {
    format = "jpg";
    System.out.println(format);
    saveAs(mFilename, format);
  }

  public void saveAsGif() {
    format = "gif";
    System.out.println(format);
    saveAs(mFilename, format);
  }

  private void saveAs(String theFileName, String format) {
    try {
      // Create an image in the required format
      ImageIO.write(getPanelImage(), format, new File(theFileName + "." + format));
    } catch (IOException ex) {
    System.err.println("Problem occurred creating image.");
    }
  }

  public BufferedImage getPanelImage() {
    BufferedImage image = new BufferedImage(gridPanel.getSize().width, gridPanel.getSize().height, BufferedImage.TYPE_3BYTE_BGR);
    Graphics2D g = image.createGraphics();
    gridPanel.paint(g);
    g.dispose();
    return image;
  }

  
  
  

}
