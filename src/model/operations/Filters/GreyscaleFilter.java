package model.operations.Filters;

import model.image.*;
import model.operations.OperationInterface;

public class GreyscaleFilter extends AbstractFilter {

  /**
   * Constructor for GreyscaleOperation.
   *
   * @param image CustomImageState to be modified.
   */
  public GreyscaleFilter(CustomImageState image) {
    super(image);
  }

  /**
   * This method applies the greyscale operation to the image.
   *
   * @return CustomImageState the new image with the operation applied.
   */
  @Override
  public CustomImageState applyOperation() {
    //0. get all the parameters
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();

    // 1. create a new image
    ExtendedCustomImage newImage = new BufferedImageWrapper(width, height);

    // 2. loop over each pixel in the sourceImage
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j ++) {
        float grey = (float) (0.2126 * sourceImage.getPixel(i, j).getRed()
                        + 0.7152 * sourceImage.getPixel(i, j).getGreen()
                        + 0.0722 * sourceImage.getPixel(i, j).getBlue());

        // 3. Get the grey value of the pixel at (i, j) to be modified.
        grey = Math.round(Math.min(255, Math.max(grey, 0)));

        // 4. Set the new pixel to the newImage.
        Pixel greyPixel = new Pixel((int) grey, (int) grey, (int) grey);
        newImage.setPixel(i, j, greyPixel);
      }
    }
    return newImage;
  }
}
