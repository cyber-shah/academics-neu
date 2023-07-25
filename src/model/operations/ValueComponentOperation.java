package model.operations;

import model.image.CImage;
import model.image.CImageState;
import model.image.CPixel;

/**
 * This class represents the value component operation.
 * It sets the value component to the max of the rgb values of the pixel.
 */
public class ValueComponentOperation implements OperationInterface {

  private final CImageState sourceImage;

  /**
   * This is the constructor for the ValueComponentOperation class.
   *
   * @param sourceImage The source image.
   */
  public ValueComponentOperation(CImageState sourceImage) {
    this.sourceImage = sourceImage;
  }

  /**
   * This method applies the Value component operation on the image.
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
          int value_component = Math.max(red, green);
          value_component = Math.max(value_component, blue);


          // set the new rgb values to the pixel
          newCustomImage.setPixel(i, j, new CPixel(value_component, value_component, value_component));
        }
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid pixel at (" + i + ", " + j + ").");
    }
    return newCustomImage;
  }

}
