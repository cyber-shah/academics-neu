package model;

import model.image.CustomImageState;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a ImageDatabase.
 * It represents a database of images.
 */
public class ImageDatabase implements ImageDatabaseInterface {
  private Map<String, CustomImageState> imagesMap;


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
  public void addImage(String name, CustomImageState image) {
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
  public CustomImageState getImage(String name) {
    if (!this.imagesMap.containsKey(name) || name == null) {
      throw new IllegalArgumentException("getImage " + name + " not found.");
    }
    return this.imagesMap.get(name);
  }

  /**
   * Removes image from the database.
   *
   * @param name String value of name.
   */
  @Override
  public void removeImage(String name) {
    if (!this.imagesMap.containsKey(name) || name == null) {
      throw new IllegalArgumentException("removeImage " + name + " not found.");
    }
    this.imagesMap.remove(name);
  }

  /**
   * Returns the number of images in the database.
   *
   * @return int value of number of images.
   */
  @Override
  public int getNumImages() {
    return this.imagesMap.size();
  }

  /**
   * Returns the names of all images in the database.
   *
   * @return String value of names of all images.
   */
  @Override
  public String getAllImageNames() {
    StringBuilder sb = new StringBuilder();
    for (String name : this.imagesMap.keySet()) {
      sb.append(name).append("\n");
    }
    return sb.toString();
  }

  /**
   * Returns true if the database contains the image.
   *
   * @param imageID String value of imageID.
   * @return boolean value of whether the database contains the image.
   */
  @Override
  public boolean containsImage(String imageID) {
    return this.imagesMap.containsKey(imageID);
  }
}
