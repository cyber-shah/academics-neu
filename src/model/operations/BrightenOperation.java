package model.operations;

import model.image.CImage;
import model.image.CImageState;
import model.image.CPixel;

/**
 * This class represents the BrightenOperation class.
 * It implements the OperationInterface.
 */
public class BrightenOperation implements OperationInterface {
  private final CImageState sourceImage;
  private final int value;

  /**
   * This is the constructor for the BrightenOperation class.
   *
   * @param sourceImage The source image.
   * @param value      The value to be added to the rgb values of the pixel.
   */
  public BrightenOperation(CImageState sourceImage, int value) {
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
  public CImageState applyOperation() {
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxValue = sourceImage.getMaxValue();
    CImage newCustomImage = new CImage(width, height, maxValue);

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

          newCustomImage.setPixel(i, j, new CPixel(newRed, newGreen, newBlue));
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Pixel " + i + ", " + j + " is null");
    }
    return newCustomImage;
  }
}
