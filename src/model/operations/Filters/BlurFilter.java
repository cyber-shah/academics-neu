package model.operations.Filters;

import model.image.*;

public class BlurFilter extends AbstractFilter {

  /**
   * Constructor for BlurOperation.
   *
   * @param image CustomImageState to be modified.
   */
  public BlurFilter(CustomImageState image) {
    super(image);
  }

  /**
   * This method applies the operation to the image.
   * NOTE: It creates the kernel and then calls the convolution method.
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
    return convolution(kernel, this.sourceImage);
  }

}