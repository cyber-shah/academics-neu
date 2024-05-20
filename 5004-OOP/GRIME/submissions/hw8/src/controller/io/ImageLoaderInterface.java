package controller.io;

import model.image.Image;

import java.io.FileNotFoundException;

/**
 * This interface represents the image loader for the program.
 * It is responsible for loading an image from a file path.
 */
public interface ImageLoaderInterface {
  Image load(String filePath) throws FileNotFoundException;
}
