package model;

import model.image.CustomImageState;

/**
 * This interface represents an ImageDatabase.
 * It represents a database of images.
 */
public interface ImageDatabaseInterface {

  void addImage(String name, CustomImageState image);

  CustomImageState getImage(String name);

  void removeImage(String name);

  int getNumImages();

  String getAllImageNames();

  boolean containsImage(String imageID);
}
