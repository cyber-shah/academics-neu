package model;

import model.Image.Image;
import model.Image.ImageState;

import java.util.HashMap;
import java.util.Map;

public class ImageDatabase implements ImageDatabaseInterface {
  private Map<String, ImageState> imagesMap;


  /**
   * Constructor for ImageDatabase.
   */
  public ImageDatabase() {
    imagesMap = new HashMap<>();
  }

  /**
   * Adds image to the database.
   *
   * @param name  String value of name.
   * @param image Image value of image.
   */
  @Override
  public void addImage(String name, ImageState image) {
    if (this.imagesMap.containsKey(name)) {
      throw new IllegalArgumentException("addImage " + name + " already exists.");
    }
    else if (image == null || name == null) {
      throw new IllegalArgumentException("addImage " + name + " is null.");
    }
    this.imagesMap.put(name, image);
  }

  /**
   * Gets image from the database.
   *
   * @param name String value of name.
   * @return Image value of image.
   */
  @Override
  public ImageState getImage(String name) {
    if (!this.imagesMap.containsKey(name) || name == null) {
      throw new IllegalArgumentException("getImage " + name + " not found.");
    }
    return this.imagesMap.get(name);
  }
}
