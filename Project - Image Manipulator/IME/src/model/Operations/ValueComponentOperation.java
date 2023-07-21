package model.Operations;

import model.Image.Image;
import model.Image.ImageState;
import model.Image.Pixel;

public class ValueComponentOperation implements OperationInterface {

  private final ImageState sourceImage;

  public ValueComponentOperation(ImageState sourceImage) {
    this.sourceImage = sourceImage;
  }

  /**
   * This method applies the Value component operation on the image.
   *
   * @return newImage ImageState object.
   */
  @Override
  public ImageState applyOperation() {
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxValue = sourceImage.getMaxValue();
    Image newImage = new Image(width, height, maxValue);

    int i = 0;
    int j = 0;
    try {
      for (i = 0; i < width; i++) {
        for (j = 0; j < height; j++) {
          // get the rgb values of the pixel
          int red = sourceImage.getPixel(i, j).getRed();
          int green = sourceImage.getPixel(i, j).getGreen();
          int blue = sourceImage.getPixel(i, j).getBlue();

          if (red > green && red > blue) {
            red = maxValue;
          } else if (green > red && green > blue) {
            green = maxValue;
          } else if (blue > red && blue > green) {
            blue = maxValue;
          }

          // set the new rgb values to the pixel
          newImage.setPixel(i, j, new Pixel(red, green, blue));
        }
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid pixel at (" + i + ", " + j + ").");
    }
    return newImage;
  }
}
