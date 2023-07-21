package controller.IO;

import model.Image.ImageState;

import java.io.IOException;

public interface ImageSaverInterface {
  void save(ImageState image, String path) throws IOException;
}
