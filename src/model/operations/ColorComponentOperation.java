package model.operations;

import model.image.CImage;
import model.image.CImageState;
import model.image.CPixel;

/**
 * This class represents the color component operation.
 */
public class ColorComponentOperation implements OperationInterface {

  private final CImageState sourceImage;
  private final String component;

  /**
   * This is the constructor for the ColorComponentOperation class.
   *
   * @param sourceImage The source image.
   * @param component      The component to be added to the rgb values of the pixel.
   */
  public ColorComponentOperation(CImageState sourceImage, String component) {
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
          int value = 0;

          if (component.equalsIgnoreCase("red")) {
            value = red;
          } else if (component.equalsIgnoreCase("green")) {
            value = green;
          } else if (component.equalsIgnoreCase("blue")) {
            value = blue;
          }
          newCustomImage.setPixel(i, j, new CPixel(value, value, value));
        }
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid component");
    }
    return newCustomImage;
  }
}
