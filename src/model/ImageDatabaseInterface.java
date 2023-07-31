package model;

import model.image.CustomImageState;

import java.util.Collection;

/**
 * This interface represents an ImageDatabase.
 * It represents a database of images.
 */
public interface ImageDatabaseInterface {

  /**
   * This method adds an image to the database.
   *
   * @param name the name of the image.
   * @param image the image to add.
   */
  void addImage(String name, CustomImageState image);

  /**
   * Getter for the image with the given name.
   *
   * @param name the name of the image to get.
   * @return the image with the given name.
   */
  CustomImageState getImage(String name);

  /**
   * This method removes an image from the database.
   *
   * @param name the name of the image to remove.
   */
  void removeImage(String name);

  /**
   * Calculates the number of images in the database.
   *
   * @return the number of images in the database.
   */
  int getNumImages();

  /**
   * Getter for the names of all the images in the database.
   *
   * @return Collection type names of all the image.
   */
  Collection<? extends CustomImageState> getAllImageNames();

  /**
   * Getter for the names of all the images in the database as a string.
   *
   * @return String names of all the image.
   */
  String getAllImageNamesString();

  /**
   * This method checks if the database contains an image with the given name.
   *
   * @param imageID the name of the image to check.
   * @return true if the database contains an image with the given name, false otherwise.
   */
  boolean containsImage(String imageID);

}
