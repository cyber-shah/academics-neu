package model.operations;

import model.image.CustomImageState;

/**
 * This interface represents an Operation.
 * It represents an Operation that can be applied to an Image.
 */
public interface OperationInterface {

  /**
   * This method applies the operation to the image.
   *
   * @return the image after the operation has been applied.
   */
  CustomImageState applyOperation();
}
