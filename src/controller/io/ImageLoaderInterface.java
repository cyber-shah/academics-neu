package controller.io;

import model.image.CImage;

import java.io.FileNotFoundException;

/**
 * This interface represents the image loader for the program.
 * It is responsible for loading an image from a file path.
 */
public interface ImageLoaderInterface {
  CImage load(String filePath) throws FileNotFoundException;
}
