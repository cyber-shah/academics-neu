package view.gui;

import model.image.CustomImageState;

import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

/**
 * This interface represents the GUI view.
 * It extends the ActionListener and ChangeListener interfaces.
 * It is used to display the image and messages to the user.
 */
public interface ViewGUIInterface extends ActionListener, ChangeListener {


  /**
   * Adds Subscribers to the listeners list.
   *
   * @param listener the subscriber to be added.
   */
  void addEventsListener(CustomEventsListener listener);

  /**
   * Updates the image canvas.
   *
   * @param image the image to be displayed.
   */
  void updateImageCanvas(CustomImageState image);

  /**
   * Shows a message to the user.
   *
   * @param message the message to be shown.
   */
  void showMessage(String message);

  /**
   * Redraws the histogram panel with given values.
   *
   * @param histogramValues the values to be updated.
   */
  void updateHistogram(int[][] histogramValues);


}
