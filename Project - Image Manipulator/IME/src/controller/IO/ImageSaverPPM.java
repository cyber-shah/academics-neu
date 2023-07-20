package controller.IO;

import model.Image.Image;
import model.Image.ImageState;

import java.io.File;

public class ImageSaverPPM implements ImageSaverInterface{

  public void save(ImageState image, Appendable path) {
    StringBuilder sb = new StringBuilder();

    sb.append("P3\n");
    sb.append(image.getWidth() + " " + image.getHeight() + "\n");
    sb.append(image.getMaxValue());

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        sb.append(image.getPixel(i, j).getRed() + " ");
        sb.append(image.getPixel(i, j).getGreen() + " ");
        sb.append(image.getPixel(i, j).getBlue() + " ");
      }
      sb.append("\n");
    }
    File file = new File(path.toString());
    file.setWritable(true);
    file.write(sb.toString());
  }
}
