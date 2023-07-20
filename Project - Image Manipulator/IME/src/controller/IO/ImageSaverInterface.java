package controller.IO;

import model.Image.Image;
import model.Image.ImageState;

public interface ImageSaverInterface {
  void save(ImageState image, Appendable path);
}
