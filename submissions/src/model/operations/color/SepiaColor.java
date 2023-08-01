package model.operations.color;

import model.image.CustomImageState;

/**
 * This class represents the Sepia operation.
 * It creates a matrix and then uses the applyMatrix method from the superclass.
 */
public class SepiaColor extends AbstractColor {

  /**
   * Constructor for SepiaOperation.
   */
  public SepiaColor(CustomImageState image) {
    super(image);
    super.matrix = new float[][]{
            {0.393f, 0.769f, 0.189f},
            {0.349f, 0.686f, 0.168f},
            {0.272f, 0.534f, 0.131f}
    };
  }
}
