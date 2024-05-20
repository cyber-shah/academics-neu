package model.operations.color;

import model.image.BufferedImageWrapper;
import model.image.CustomImageState;
import model.image.CustomImageMutable;
import model.image.Pixel;
import model.operations.OperationInterface;

/**
 * This class is an abstract class for color operations.
 * It implements the OperationInterface.
 * It has a sourceImage and a matrix.
 * NOTE: Matrix is always created by the subclasses.
 */
public class AbstractColor implements OperationInterface {
  protected CustomImageState sourceImage;
  protected float[][] matrix;

  /**
   * Constructor for AbstractColor.
   *
   * @param image CustomImageState to be modified.
   */
  public AbstractColor(CustomImageState image) {
    this.sourceImage = image;
  }

  /**
   * This method applies the operation to the image.
   * NOTE: It uses the matrix and then calls the applies matrix method.
   *
   * @return CustomImageState the new image with the operation applied.
   */
  @Override
  public CustomImageState applyOperation() {
    return applyMatrix(this.matrix, this.sourceImage);
  }

  /**
   * This method applies a matrix to a CustomImageState.
   *
   * @param matrix float[][] matrix to be applied.
   * @param sourceImage CustomImageState to be modified.
   * @return CustomImageState a new Image with the matrix applied.
   */
  public static CustomImageState applyMatrix(float[][] matrix,
                                             CustomImageState sourceImage) {
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    CustomImageMutable newImage = new BufferedImageWrapper(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Pixel pixel = sourceImage.getPixel(x, y);
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();

        // Apply the matrix transformation to each color component
        int newRed = (int) (matrix[0][0] * red + matrix[0][1] * green + matrix[0][2] * blue);
        int newGreen = (int) (matrix[1][0] * red + matrix[1][1] * green + matrix[1][2] * blue);
        int newBlue = (int) (matrix[2][0] * red + matrix[2][1] * green + matrix[2][2] * blue);

        // Clamp the values to the valid range (0-255)
        newRed = Math.min(255, Math.max(0, newRed));
        newGreen = Math.min(255, Math.max(0, newGreen));
        newBlue = Math.min(255, Math.max(0, newBlue));

        // Set the new pixel with the transformed color components
        Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
        newImage.setPixel(x, y, newPixel);
      }
    }

    return newImage;


  }


}
