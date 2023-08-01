package view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel implements Scrollable, MouseWheelListener {

  private BufferedImage image;
  private double zoomLevel = 1.0;

  /**
   * Constructs a new Canvas object.
   */
  public Canvas() {
    super();
    // Add the mouse wheel listener
    addMouseWheelListener(this);
  }

  /**
   * Sets the image to be displayed by this canvas.
   *
   * @param image the image to be displayed by this canvas.
   */
  public void setImage(BufferedImage image) {
    this.image = image;
    // Update the layout and scroll bars
    revalidate();
    repaint();
  }

  /**
   * Sets the zoom level of this canvas.
   *
   * @param zoomLevel the zoom level of this canvas.
   */
  public void setZoomLevel(double zoomLevel) {
    this.zoomLevel = zoomLevel;
    // Update the layout and scroll bars
    revalidate();
    repaint();
  }

  /**
   * Paints the image on the canvas.
   *
   * @param g graphics object.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      int scaledWidth = (int) (image.getWidth() * zoomLevel);
      int scaledHeight = (int) (image.getHeight() * zoomLevel);
      g.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
    }
  }

  /**
   * Getter method for the preferred size of the canvas.
   *
   * @return the preferred size of the canvas.
   */
  @Override
  public Dimension getPreferredSize() {
    if (image != null) {
      int scaledWidth = (int) (image.getWidth() * zoomLevel);
      int scaledHeight = (int) (image.getHeight() * zoomLevel);
      return new Dimension(scaledWidth, scaledHeight);
    } else {
      return super.getPreferredSize();
    }
  }

  /**
   * Getter method for the preferred size of the viewport.
   *
   * @return the preferred size of the viewport.
   */
  @Override
  public Dimension getPreferredScrollableViewportSize() {
    return getPreferredSize();
  }

  /**
   * Getter method for the scrollable tracks viewport width.
   *
   * @param visibleRect The view area visible within the viewport
   * @param orientation Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL.
   * @param direction Less than zero to scroll up/left, greater than zero for down/right.
   * @return the scrollable tracks viewport width.
   */
  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 16;
  }

  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 64;
  }

  @Override
  public boolean getScrollableTracksViewportWidth() {
    // Allow horizontal scrolling when image width is larger than the viewport width
    return false;
  }

  @Override
  public boolean getScrollableTracksViewportHeight() {
    // Allow vertical scrolling when image height is larger than the viewport height
    return false;
  }

  /**
   * A part of the mouse wheel listener interface.
   * sets the zoom level of the canvas when the mouse wheel is moved.
   *
   * @param e the event to be processed.
   */
  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    int notches = e.getWheelRotation();
    if (notches < 0) {
      // Zoom in
      setZoomLevel(zoomLevel + 0.1);
    } else {
      // Zoom out
      setZoomLevel(Math.max(0.1, zoomLevel - 0.1));
    }
  }
  
}

