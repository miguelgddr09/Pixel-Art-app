package textUI;

import javax.swing.JLabel;
import java.awt.*;

public class TextUI {
  private JLabel label;
  public TextUI(int x, int y, int width, int height, Color foreground) {
    label = new JLabel();
    label.setBounds(x, y, width, height); 
    label.setForeground(foreground); 
  }

  public void setText(String text) {
    label.setText(text);
  }

  public void setColor(Color color) {
    label.setForeground(color); 
  }

  public JLabel getLabel() {
    return label;
  }
}