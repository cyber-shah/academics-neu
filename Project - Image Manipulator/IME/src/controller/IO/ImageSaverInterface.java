package controller.IO;

import model.Image.Image;
import model.Image.ImageState;

import java.io.IOException;

public interface ImageSaverInterface {
  void save(ImageState image, Appendable path) throws IOException;
}
