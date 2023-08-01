package view.gui;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

/**
 * This class represents the Canvas object.
 * It is responsible for displaying the image on the screen.
 * It is also responsible for handling the zooming functionality.
 * It extends the JPanel class.
 */
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
   * Updates the image to be displayed by this canvas.
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
   * A part of the mouse wheel listener interface.
   * sets the zoom level of the canvas when the mouse wheel is moved.
   * This event gets triggered when the mouse wheel is moved.
   *
   * @param e the event to be processed.
   */
  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    // Get the number of notches
    int notches = e.getWheelRotation();
    // Zoom in or out depending on the number of notches
    if (notches < 0) {
      // Zoom in
      setZoomLevel(zoomLevel + 0.1);
    } else {
      // Zoom out
      setZoomLevel(Math.max(0.1, zoomLevel - 0.1));
    }
  }

  /**
   * Sets the zoom level of this canvas.
   *
   * @param zoomLevel the zoom level of this canvas.
   */
  private void setZoomLevel(double zoomLevel) {
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


  // The following methods are part of the Scrollable interface.
  // They are used to control the scrolling behavior of the canvas.


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
   * @param orientation Either SwingConstants. VERTICAL or SwingConstants.HORIZONTAL.
   * @param direction Less than zero to scroll up/left, greater than zero for down/right.
   * @return the scrollable tracks viewport width.
   */
  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 16;
  }

  /**
   * Getter method for the scrollable block increment.
   *
   * @param visibleRect The view area visible within the viewport.
   * @param orientation Either SwingConstants. VERTICAL or SwingConstants. HORIZONTAL.
   * @param direction Less than zero to scroll up/left, greater than zero for down/right.
   * @return the scrollable block increment.
   */
  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    return 64;
  }

  /**
   * Always allow horizontal scrolling.
   *
   * @return false.
   */
  @Override
  public boolean getScrollableTracksViewportWidth() {
    return false;
  }

  /**
   * Always allow vertical scrolling.
   *
   * @return false.
   */
  @Override
  public boolean getScrollableTracksViewportHeight() {
    return false;
  }

}

