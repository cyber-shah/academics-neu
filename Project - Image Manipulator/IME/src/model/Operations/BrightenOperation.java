package model.Operations;

import model.Image.Image;
import model.Image.ImageState;
import model.Image.Pixel;

public class BrightenOperation implements Operation {

  private int value;

  @Override
  public Image applyOperation(ImageState image, int... args) {
    value = args[0];
    int width = image.getWidth();
    int height = image.getHeight();
    int maxValue = image.getMaxValue();
    Image newImage = new Image(width, height, maxValue);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j ++) {
        int red = image.getPixel(i, j).getRed();
        int green = image.getPixel(i, j).getGreen();
        int blue = image.getPixel(i, j).getBlue();

        int newRed = red + value;
        int newGreen = green + value;
        int newBlue = blue + value;

        if (newRed > maxValue) {
          newRed = maxValue;
        } if (newGreen > maxValue) {
          newGreen = maxValue;
        } if (newBlue > maxValue) {
          newBlue = maxValue;
        }

        Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
        newImage.setPixel(i, j, newPixel);
      }
    }
    return newImage;
  }
}
