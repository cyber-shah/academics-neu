package controller.io;

import model.image.CustomImageState;

import java.io.IOException;

/**
 * This interface represents the image saver for the program.
 * It is responsible for saving an image to a file path.
 */
public interface ImageSaverInterface {

  /**
   * This method saves an image to a file path.
   * It takes in a CustomImageState, which is a CustomImage and saves it to the given path.
   *
   * @param image the image to save.
   * @param path the path to save the image to.
   * @throws IOException if the path is invalid.
   */
  void save(CustomImageState image, String path) throws IOException;
}
