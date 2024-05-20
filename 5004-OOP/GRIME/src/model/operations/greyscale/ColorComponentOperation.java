package model.operations.greyscale;

import model.image.BufferedImageWrapper;
import model.image.CustomImageState;
import model.image.Pixel;
import model.operations.OperationInterface;

/**
 * This class represents the color component operation.
 */
public class ColorComponentOperation implements OperationInterface {

  private final CustomImageState sourceImage;
  private final String component;

  /**
   * This is the constructor for the ColorComponentOperation class.
   *
   * @param sourceImage The source image.
   * @param component      The component to be added to the rgb values of the pixel.
   */
  public ColorComponentOperation(CustomImageState sourceImage, String component) {
    this.sourceImage = sourceImage;
    this.component = component;
  }

  /**
   * This method applies the color component operation on the image.
   * It sets the value of the component to each rgb value of the pixel.
   *
   * @return newImage ImageState object.
   */
  @Override
  public CustomImageState applyOperation() {
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxValue = sourceImage.getMaxValue();
    BufferedImageWrapper newCustomImage = new BufferedImageWrapper(width, height);

    int i = 0;
    int j = 0;
    try {
      for (i = 0; i < width; i++) {
        for (j = 0; j < height; j++) {
          // get the rgb values of the pixel
          int red = sourceImage.getPixel(i, j).getRed();
          int green = sourceImage.getPixel(i, j).getGreen();
          int blue = sourceImage.getPixel(i, j).getBlue();
          int value = 0;

          if (component.equalsIgnoreCase("red")) {
            value = red;
          } else if (component.equalsIgnoreCase("green")) {
            value = green;
          } else if (component.equalsIgnoreCase("blue")) {
            value = blue;
          }
          newCustomImage.setPixel(i, j, new Pixel(value, value, value));
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid component");
    }
    return newCustomImage;
  }
}
