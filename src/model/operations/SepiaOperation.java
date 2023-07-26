package model.operations;

import model.image.BufferedImageWrapper;
import model.image.CustomImageState;
import model.image.ExtendedCustomImage;
import model.image.Pixel;

public class SepiaOperation implements OperationInterface {

  private final CustomImageState sourceImage;

  /**
   * Constructor for SepiaOperation.
   */
  public SepiaOperation(CustomImageState image) {
    this.sourceImage = image;
  }

  /**
   * This method applies the operation to the image.
   *
   * @return CustomImageState the new image with the operation applied.
   */
  @Override
  public CustomImageState applyOperation() {
     // 0. get all the parameters
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxValue = sourceImage.getMaxValue();

    // 1. create a new image
    ExtendedCustomImage newImage = new BufferedImageWrapper(width, height);

    // 2. loop over each pixel in the sourceImage
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j ++) {
        // 3. Get the rgb values of the pixel at (i, j) to be modified.
        int red = sourceImage.getPixel(i, j).getRed();
        int green = sourceImage.getPixel(i, j).getGreen();
        int blue = sourceImage.getPixel(i, j).getBlue();

        // 4. Apply the sepia formula to the rgb values.
        int newRed = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
        int newGreen = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
        int newBlue = (int) (0.272 * red + 0.534 * green + 0.131 * blue);

        // 5. Set the new pixel to the newImage.
        newRed = Math.min(maxValue, Math.max(newRed, 0));
        newGreen = Math.min(maxValue, Math.max(newGreen, 0));
        newBlue = Math.min(maxValue, Math.max(newBlue, 0));

        newImage.setPixel(i, j, new Pixel(newRed, newGreen, newBlue));
      }
    }
    return newImage;
  }
}
