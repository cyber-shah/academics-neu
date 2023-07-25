package model.operations;

import model.image.CImage;
import model.image.CImageState;
import model.image.CPixel;

/**
 * This class represents a LumaOperation.
 * It extends the abstract class AbstractOperation.
 */
public class LumaOperation implements OperationInterface {
  private final CImageState sourceImage;

  /**
   * This is the constructor for the LumaOperation class.
   *
   * @param sourceImage The source image.
   */
  public LumaOperation(CImageState sourceImage) {
    this.sourceImage = sourceImage;
  }

  /**
   * This method applies the luma operation on the image.
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

          // calculate the new rgb values
          int luma = (int) ((0.2126 * red)
                  + (0.7152 * green)
                  + (0.0722 * blue));

          // set the new rgb values
          newCustomImage.setPixel(i, j, new CPixel(luma, luma, luma));
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Pixel " + i + ", " + j + " is null");
    }
    return newCustomImage;
  }
}
