package controller.io;

import model.image.CImageState;
import java.io.IOException;

/**
 * This interface represents the image saver for the program.
 * It is responsible for saving an image to a file path.
 */
public interface ImageSaverInterface {
  void save(CImageState image, String path) throws IOException;
}
