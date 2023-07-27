package model.operations.filters;

import model.image.CustomImageState;
import model.operations.OperationInterface;

public class SharpenFilter extends AbstractFilter {

  /**
   * Constructor for SharpenOperation.
   *
   * @param image CustomImageState to be modified.
   */
  public SharpenFilter(CustomImageState image) {
    super(image);
    super.kernel = new float[][]{
            { -1/8f, -1/8f, -1/8f, -1/8f, -1/8f },
            { 1/4f, 1/4f, 1/4f, 1/4f, 1/4f },
            { 1/4f, 1/4f, 1/4f, 1/4f, 1/4f },
            { 1/4f, 1/4f, 1/4f, 1/4f, 1/4f },
            { -1/8f, -1/8f, -1/8f, -1/8f, -1/8f}
    };
  }

}
