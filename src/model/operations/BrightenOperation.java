package model.operations;

import model.image.PPMImage;
import model.image.CustomImageState;
import model.image.Pixel;

/**
 * This class represents the BrightenOperation class.
 * It implements the OperationInterface.
 */
public class BrightenOperation implements OperationInterface {
  private final CustomImageState sourceImage;
  private final int value;

  /**
   * This is the constructor for the BrightenOperation class.
   *
   * @param sourceImage The source image.
   * @param value      The value to be added to the rgb values of the pixel.
   */
  public BrightenOperation(CustomImageState sourceImage, int value) {
    this.sourceImage = sourceImage;
    this.value = value;
  }

  /**
   * This method applies the brighten operation on the image.
   * It adds the value to each rgb value of the pixel.
   * If the value is greater than the maxValue, it sets the value to the maxValue.
   *
   * @return newImage ImageState object.
   */
  @Override
  public CustomImageState applyOperation() {
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxValue = sourceImage.getMaxValue();
    PPMImage newCustomImage = new PPMImage(width, height, maxValue);

    int i = 0;
    int j = 0;
    try {
      for (i = 0; i < width; i++) {
        for (j = 0; j < height; j++) {
          // get the rgb values of the pixel
          int red = sourceImage.getPixel(i, j).getRed();
          int green = sourceImage.getPixel(i, j).getGreen();
          int blue = sourceImage.getPixel(i, j).getBlue();

          // add the value to each rgb value
          int newRed = red + this.value;
          int newGreen = green + this.value;
          int newBlue = blue + this.value;

          // NOTE : reduced the lines of code here
          newRed = Math.min(maxValue, Math.max(newRed, 0));
          newGreen = Math.min(maxValue, Math.max(newGreen, 0));
          newBlue = Math.min(maxValue, Math.max(newBlue, 0));
          newCustomImage.setPixel(i, j, new Pixel(newRed, newGreen, newBlue));
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Pixel " + i + ", " + j + " is null");
    }
    return newCustomImage;
  }
}
