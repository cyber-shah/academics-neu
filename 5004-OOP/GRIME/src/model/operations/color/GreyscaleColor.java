package model.operations.color;

import model.image.BufferedImageWrapper;
import model.image.CustomImageState;
import model.image.CustomImageMutable;
import model.image.Pixel;

/**
 * This class is a subclass for absractcolor.
 * It creates a matrix and then uses the applyMatrix method from the superclass.
 */
public class GreyscaleColor extends AbstractColor {

  /**
   * Constructor for GreyscaleOperation.
   *
   * @param image CustomImageState to be modified.
   */
  public GreyscaleColor(CustomImageState image) {
    super(image);
    super.matrix = new float[][]{
            {0.2126f, 0.7152f, 0.0722f},
            {0.2126f, 0.7152f, 0.0722f},
            {0.2126f, 0.7152f, 0.0722f}
    };
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
    CustomImageMutable newImage = new BufferedImageWrapper(width, height);

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
