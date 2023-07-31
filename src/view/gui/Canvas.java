package view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel {
  private BufferedImage image;

  public Canvas() {
    super();
  }

  public void setImage(BufferedImage image) {
    this.image = image;
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.cyan);
    g.fillRect(50,50, 200, 200);

//    if (image != null) {
//      g.drawImage(image, 0, 0, null);
//    }
  }
}
