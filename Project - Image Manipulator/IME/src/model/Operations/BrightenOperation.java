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

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j ++) {
        int red = image.getPixel(i, j).getRed();
        int green = image.getPixel(i, j).getGreen();
        int blue = image.getPixel(i, j).getBlue();

        int newRed = red + this.value;
        int newGreen = green + this.value;
        int newBlue = blue + this.value;

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
