package model;

import model.Image.Image;

import java.util.HashMap;
import java.util.Map;

public class ImageDatabase implements ImageDatabaseInterface {
  private Map<String, Image> imagesMap;


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
  public void addImage(String name, Image image) {
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
  public Image getImage(String name) {
    if (!this.imagesMap.containsKey(name)) {
      throw new IllegalArgumentException("getImage " + name + " not found.");
    }
    return this.imagesMap.get(name);
  }
}
