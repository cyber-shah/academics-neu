package view;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

  public Canvas() {
    super();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.RED);
    g.fillRect(0, 0, 100, 100);
  }
}
