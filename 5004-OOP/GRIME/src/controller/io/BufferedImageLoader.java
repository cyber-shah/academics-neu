package controller.io;

import model.image.BufferedImageWrapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class implements the ImageLoaderInterface and is responsible for loading an image.
 * @see ImageLoaderInterface
 */
public class BufferedImageLoader implements ImageLoaderInterface
        <BufferedImageWrapper> {

  /**
   * This method loads the image from the given file path.
   *
   * @param filePath the file path.
   * @return the image.
   * @throws FileNotFoundException if the file is not found.
   */
  @Override
  public BufferedImageWrapper load(String filePath) throws FileNotFoundException {
    if (filePath == null) {
      throw new FileNotFoundException("File cannot be null");
    }
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(new java.io.File(filePath));
      if (bufferedImage == null) {
        throw new IllegalArgumentException("File format not supported");
      }
    } catch (IOException e) {
      throw new FileNotFoundException("File not found");
    }
    return new BufferedImageWrapper(bufferedImage);
  }
}
