package controller.io;

import model.image.ImageState;
import java.io.IOException;

/**
 * This interface represents the image saver for the program.
 * It is responsible for saving an image to a file path.
 */
public interface ImageSaverInterface {
  void save(ImageState image, String path) throws IOException;
}
