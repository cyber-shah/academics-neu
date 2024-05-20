package model;

import model.image.ImageState;

/**
 * This interface represents an ImageDatabase.
 * It represents a database of images.
 */
public interface ImageDatabaseInterface {

  void addImage(String name, ImageState image);

  ImageState getImage(String name);

  void removeImage(String name);

  int getNumImages();

  String getAllImageNames();
}
