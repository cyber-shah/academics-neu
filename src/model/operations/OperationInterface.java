package model.operations;

import model.image.ImageState;

/**
 * This interface represents an Operation.
 * It represents an Operation that can be applied to an Image.
 */
public interface OperationInterface {

  ImageState applyOperation();
}
