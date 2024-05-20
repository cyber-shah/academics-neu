package testview;

import model.image.CustomImageState;
import view.gui.CustomEventsListener;
import view.gui.ViewGUIInterface;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class MockView implements ViewGUIInterface {

  public CustomImageState image;
  public StringBuilder log;

  public MockView() {
    this.image = null;
    this.log = new StringBuilder();
  }

  /**
   * Adds Subscribers to the listeners list.
   *
   * @param listener the subscriber to be added.
   */
  @Override
  public void addEventsListener(CustomEventsListener listener) {
    log.append("added an event listener\n");
  }

  /**
   * Updates the image canvas.
   *
   * @param image the image to be displayed.
   */
  @Override
  public void updateImageCanvas(CustomImageState image) {
    this.image = image;
  }

  /**
   * Shows a message to the user.
   *
   * @param message the message to be shown.
   */
  @Override
  public void showMessage(String message) {
    log.append(message);
  }

  /**
   * Redraws the histogram panel with given values.
   *
   * @param histogramValues the values to be updated.
   */
  @Override
  public void updateHistogram(int[][] histogramValues) {
    log.append(Arrays.deepToString(histogramValues));
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

  }

  /**
   * Invoked when the target of the listener has changed its state.
   *
   * @param e a ChangeEvent object
   */
  @Override
  public void stateChanged(ChangeEvent e) {

  }


  /**
   * NOTE: this is the key method for testing.
   * It returns the image that was last updated.
   *
   * @return the image.
   */
  public CustomImageState getImage() {
    return this.image;
  }
}
