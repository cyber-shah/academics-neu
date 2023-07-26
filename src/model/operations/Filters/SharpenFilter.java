package model.operations.Filters;

import model.image.CustomImageState;
import model.operations.OperationInterface;

public class SharpenFilter implements OperationInterface {

  private final CustomImageState sourceImage;

  /**
   * Constructor for SharpenOperation.
   *
   * @param image CustomImageState to be modified.
   */
  public SharpenFilter(CustomImageState image) {
    this.sourceImage = image;
  }

  @Override
  public CustomImageState applyOperation() {
    // 0. Initialize the kernel.
    float[][] kernel = new float[5][5];

    // 1. set values for kernel
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        if (i == 1 || i == 2 || i == 3) {
          kernel[i][j] = (float) 1 / 4;
        } else {
          kernel[i][j] = (float) -1 / 8;
        }
      }
    }

    // 2. return convolution
    return BlurFilter.convolution(kernel, sourceImage);
  }
}
