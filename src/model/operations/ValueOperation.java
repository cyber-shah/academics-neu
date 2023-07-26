package model.operations;

import model.image.PPMImage;
import model.image.CustomImageState;
import model.image.Pixel;

/**
 * This class represents the value component operation.
 * It sets the value component to the max of the rgb values of the pixel.
 */
public class ValueOperation implements OperationInterface {

  private final CustomImageState sourceImage;

  /**
   * This is the constructor for the ValueComponentOperation class.
   *
   * @param sourceImage The source image.
   */
  public ValueOperation(CustomImageState sourceImage) {
    this.sourceImage = sourceImage;
  }

  /**
   * This method applies the Value component operation on the image.
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
          int value_component = Math.max(red, green);
          value_component = Math.max(value_component, blue);


          // set the new rgb values to the pixel
          newCustomImage.setPixel(i, j, new Pixel(value_component, value_component, value_component));
        }
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid pixel at (" + i + ", " + j + ").");
    }
    return newCustomImage;
  }

}
