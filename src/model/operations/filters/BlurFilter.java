package model.operations.filters;

import model.image.*;

public class BlurFilter extends AbstractFilter {

  /**
   * Constructor for BlurOperation.
   *
   * @param image CustomImageState to be modified.
   */
  public BlurFilter(CustomImageState image) {
    super(image);
    super.kernel = new float[][]{
            {1 / 16f, 2 / 16f, 1 / 16f},
            {2 / 16f, 4 / 16f, 2 / 16f},
            {1 / 16f, 2 / 16f, 1 / 16f}
    };
  }
}