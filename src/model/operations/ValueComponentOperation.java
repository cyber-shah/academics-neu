package model.operations;

import model.image.Image;
import model.image.ImageState;
import model.image.Pixel;

/**
 * This class represents the value component operation.
 * It sets the value component to the max of the rgb values of the pixel.
 */
public class ValueComponentOperation implements OperationInterface {

  private final ImageState sourceImage;

  /**
   * This is the constructor for the ValueComponentOperation class.
   *
   * @param sourceImage The source image.
   */
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
          int value_component = Math.max(red, green);
          value_component = Math.max(value_component, blue);


          // set the new rgb values to the pixel
          newImage.setPixel(i, j, new Pixel(value_component, value_component, value_component));
        }
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid pixel at (" + i + ", " + j + ").");
    }
    return newImage;
  }

}
