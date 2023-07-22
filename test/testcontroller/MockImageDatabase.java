package testcontroller;

import model.image.ImageState;
import model.ImageDatabaseInterface;

/**
 * This class is a mock image database to be used for testing.
 */
public class MockImageDatabase implements ImageDatabaseInterface {
  private StringBuilder log;

  MockImageDatabase(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void addImage(String name, ImageState image) {
    log.append("addImage called with name: ").append(name).append(" and image: ").append(image);
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
}
