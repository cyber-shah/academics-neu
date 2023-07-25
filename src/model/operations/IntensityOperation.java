package model.operations;

import model.image.PPMImage;
import model.image.CustomImageState;
import model.image.Pixel;

/**
 * This class represents the IntensityOperation.
 * It extends the abstract class OperationAbstract.
 */
public class IntensityOperation implements OperationInterface {
  private final CustomImageState sourceImage;

  /**
   * This is the constructor for the IntensityOperation class.
   *
   * @param sourceImage The source image.
   */
  public IntensityOperation(CustomImageState sourceImage) {
    this.sourceImage = sourceImage;
  }

  /**
   * This method applies the intensity operation on the image.
   * It calculates the average of the rgb values of the pixel and sets the new rgb values to the
   * average.
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
          // for each image pixel, get the rgb values
          int red = sourceImage.getPixel(i, j).getRed();
          int green = sourceImage.getPixel(i, j).getGreen();
          int blue = sourceImage.getPixel(i, j).getBlue();

          // calculate the new rgb values
          int average = (red + green + blue) / 3;

          Pixel newPixel = new Pixel(average, average, average);
          newCustomImage.setPixel(i, j, newPixel);
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Pixel " + i + ", " + j + " is null");
    }
    return newCustomImage;
  }
}
