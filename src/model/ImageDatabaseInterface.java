package model;

import model.image.CImageState;

/**
 * This interface represents an ImageDatabase.
 * It represents a database of images.
 */
public interface ImageDatabaseInterface {

  void addImage(String name, CImageState image);

  CImageState getImage(String name);

  void removeImage(String name);

  int getNumImages();

  String getAllImageNames();
}
