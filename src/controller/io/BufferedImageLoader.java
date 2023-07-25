package controller.io;

import model.image.BufferedImageAdapter;
import model.image.CustomImageState;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedImageLoader implements ImageLoaderInterface
        <BufferedImageAdapter> {

  @Override
  public BufferedImageAdapter load(String filePath) throws FileNotFoundException {
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
    return new BufferedImageAdapter(bufferedImage);
  }
}
