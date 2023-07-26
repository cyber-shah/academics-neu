package model.operations;

import model.image.*;

import java.awt.image.BufferedImage;

public class BlurOperation implements OperationInterface {
  private final CustomImageState sourceImage;

  /**
   * Constructor for BlurOperation.
   *
   * @param image CustomImageState to be modified.
   */
  public BlurOperation(CustomImageState image) {
    this.sourceImage = image;
  }

  /**
   * This method applies the operation to the image.
   *
   * @return CustomImageState the new image with the operation applied.
   */
  @Override
  public CustomImageState applyOperation() {
    // 0. Initialize the kernel.
    float[][] kernel = new float[3][3];
    kernel[0][0] = (float) 1 / 16;
    kernel[0][1] = (float) 2 / 16;
    kernel[0][2] = (float) 1 / 16;
    kernel[1][0] = (float) 2 / 16;
    kernel[1][1] = (float) 4 / 16;
    kernel[1][2] = (float) 2 / 16;
    kernel[2][0] = (float) 1 / 16;
    kernel[2][1] = (float) 2 / 16;
    kernel[2][2] = (float) 1 / 16;

    return convolution(kernel, sourceImage);
  }


  /**
   * This method applies a convolution to a CustomImageState.
   *
   * @param kernel float[][] kernel to be applied.
   * @param sourceImage CustomImageState to be modified.
   * @return ExtendedCustomImage a new Image with the convolution applied.
   */
  public static ExtendedCustomImage convolution(float[][] kernel,
                                                CustomImageState sourceImage) {
    // 1. Get the width, height and maxValue of the newImage.
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();

    ExtendedCustomImage newImage = new BufferedImageWrapper(width, height);

    // 2. Loop over each pixel in the sourceImage
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j ++) {

        // 3. Get the pixel at (i, j) to be modified.
        Pixel pixel = sourceImage.getPixel(i, j);
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();

        // 4. Loop over the kernel.
        for (int k = 0; k < kernel.length; k++) {
          for (int l = 0; l < kernel[0].length; l++) {

            // check if pixel out of bounds of image
            if (i + k < 0 || i + k >= width || j + l < 0 || j + l >= height) {
              continue;
            }

            // 5. Get the pixel at (i + k, j + l).
            Pixel neighborPixel = sourceImage.getPixel(i + k, j + l);
            int neighborRed = neighborPixel.getRed();
            int neighborGreen = neighborPixel.getGreen();
            int neighborBlue = neighborPixel.getBlue();

            // 6. Calculate the new rgb values.
            red += kernel[k][l] * neighborRed;
            green += kernel[k][l] * neighborGreen;
            blue += kernel[k][l] * neighborBlue;
          }
        }
        // 7. Clamp all the values to 0 - 255.
        red = Math.min(255, Math.max(0, red));
        green = Math.min(255, Math.max(0, green));
        blue = Math.min(255, Math.max(0, blue));

        // 8. Set the new rgb values to the pixel.
        Pixel newPixel = new Pixel(red, green, blue);
        newImage.setPixel(i, j, newPixel);
      }
    }
    return newImage;
  }
}