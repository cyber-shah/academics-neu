package testcontroller.mocks;

import model.image.ImageState;
import model.ImageDatabaseInterface;

/**
 * This class is a mock image database to be used for testing.
 */
public class MockImageDatabase implements ImageDatabaseInterface {
  private final StringBuilder log = new StringBuilder();

  @Override
  public void addImage(String name, ImageState image) {
  }

  @Override
  public ImageState getImage(String name) {
    return null;
  }

  @Override
  public void removeImage(String name) {
    log.append("removeImage called with name: ").append(name);
  }

  @Override
  public int getNumImages() {
    return 0;
  }

  @Override
  public String getAllImageNames() {
    return null;
  }

  /**
   * EXTRA method to get the log.
   *
   * @return the log.
   */
  public String getLog() {
    return log.toString();
  }

  /**
   * EXTRA method to add an image to the log.
   *
   * @param name  the name of the image.
   * @param image the image.
   */
  public void addImage(String[] commandsList) {
    for (int i = 0; i < commandsList.length; i++) {
      log.append("Args " + i + commandsList[i]);
    }
  }
}
