package model.Operations;

import model.Image.Image;
import model.Image.ImageState;
import model.Image.Pixel;

public class BrightenOperation implements OperationInterface {
  private ImageState image;
  private int value;

  public BrightenOperation(ImageState image, int value) {
    this.image = image;
    this.value = value;
  }

  @Override
  public ImageState applyOperation() {
    int width = image.getWidth();
    int height = image.getHeight();
    int maxValue = image.getMaxValue();
    Image newImage = new Image(width, height, maxValue);

    int i = 0;
    int j = 0;
    try {
      for (i = 0; i < width; i++) {
        for (j = 0; j < height; j++) {
          // get the rgb values of the pixel
          int red = image.getPixel(i, j).getRed();
          int green = image.getPixel(i, j).getGreen();
          int blue = image.getPixel(i, j).getBlue();

          // add the value to each rgb value
          int newRed = red + this.value;
          int newGreen = green + this.value;
          int newBlue = blue + this.value;

          // if greater than maxValue, set to maxValue
          if (newRed > maxValue) {
            newRed = maxValue;
          } if (newGreen > maxValue) {
            newGreen = maxValue;
          } if (newBlue > maxValue) {
            newBlue = maxValue;
          }

          // if less than 0, set to 0
          if (newRed < 0) {
            newRed = 0;
          } if (newGreen < 0) {
            newGreen = 0;
          } if (newBlue < 0) {
            newBlue = 0;
          }

          Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
          newImage.setPixel(i, j, newPixel);
        }
      }
    } catch (NullPointerException e) {
      throw new NullPointerException("Pixel " + i + ", " + j + " is null");
    }
    return newImage;
  }
}
