package controller.io;

import model.image.CustomImageState;

import java.io.FileNotFoundException;

/**
 * This interface represents the image loader for the program.
 * It is responsible for loading an image from a file path.
 */
public interface ImageLoaderInterface<T extends CustomImageState> {

  /**
   * This method loads an image from a file path.
   * Of type T, which is a CustomImageState.
   *
   * @param filePath the file path to load the image from.
   * @return the image loaded from the file path.
   * @throws FileNotFoundException if the file path is invalid.
   */
  T load(String filePath) throws FileNotFoundException;
}
