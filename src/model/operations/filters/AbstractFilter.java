package model.operations.filters;

import model.image.BufferedImageWrapper;
import model.image.CustomImageState;
import model.image.CustomImageMutable;
import model.image.Pixel;
import model.operations.OperationInterface;

/**
 * This class represents an abstract filter.
 * It contains the common methods and fields for all filters.
 * -----------------------------------------------------------------------
 * NOTE: the matrix operation is a general term that includes any pixel-wise operation
 *       without considering the values of neighboring pixels.
 * ------------------------------------------------------------------------
 * NOTE: The convolution approach is a specific type of matrix operation that involves
 *       applying a kernel to compute new pixel values based on the neighboring pixels' values.
 * -------------------------------------------------------------------------
 */
public abstract class AbstractFilter implements OperationInterface {

  protected CustomImageState sourceImage;
  protected float[][] kernel;

  /**
   * Constructor for AbstractFilter.
   *
   * @param image CustomImageState to be modified.
   */
  public AbstractFilter(CustomImageState image) {
    this.sourceImage = image;
  }

  /**
   * This method applies the operation to the image.
   * NOTE: It creates the kernel and then calls the convolution method.
   *
   * @return CustomImageState the new image with the operation applied.
   */
  @Override
  public CustomImageState applyOperation() {
    return convolution(this.kernel, this.sourceImage);
  }

  /**
   * This method applies a convolution to a CustomImageState.
   *
   * @param kernel float[][] kernel to be applied.
   * @param sourceImage CustomImageState to be modified.
   * @return ExtendedCustomImage a new Image with the convolution applied.
   */
  public static CustomImageMutable convolution(float[][] kernel,
                                               CustomImageState sourceImage) {
    // 1. Get the width, height and maxValue of the newImage.
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();

    CustomImageMutable newImage = new BufferedImageWrapper(width, height);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int k = -1; k <= 1; k++) {
          for (int l = -1; l <= 1; l++) {
            int x = i + k;
            int y = j + l;

            if (x >= 0 && x < width && y >= 0 && y < height) {
              Pixel neighborPixel = sourceImage.getPixel(x, y);
              int neighborRed = neighborPixel.getRed();
              int neighborGreen = neighborPixel.getGreen();
              int neighborBlue = neighborPixel.getBlue();

              red += kernel[k + 1][l + 1] * neighborRed;
              green += kernel[k + 1][l + 1] * neighborGreen;
              blue += kernel[k + 1][l + 1] * neighborBlue;
            }
          }
        }

        red = Math.min(255, Math.max(0, red));
        green = Math.min(255, Math.max(0, green));
        blue = Math.min(255, Math.max(0, blue));

        Pixel newPixel = new Pixel(red, green, blue);
        newImage.setPixel(i, j, newPixel);
      }
    }
    return newImage;
  }
}
