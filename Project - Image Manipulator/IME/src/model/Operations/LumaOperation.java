package model.Operations;

import model.Image.Image;
import model.Image.ImageState;
import model.Image.Pixel;

public class LumaOperation implements OperationInterface {
  private final ImageState sourceImage;

  /**
   * This is the constructor for the LumaOperation class.
   *
   * @param sourceImage The source image.
   */
  public LumaOperation(ImageState sourceImage) {
    this.sourceImage = sourceImage;
  }

  /**
   * This method applies the luma operation on the image.
   *
   * @return newImage ImageState object.
   */
  @Override
  public ImageState applyOperation() {
    int width = sourceImage.getWidth();
    int height = sourceImage.getHeight();
    int maxValue = sourceImage.getMaxValue();
    Image newImage = new Image(width, height, maxValue);

    int i = 0;
    int j = 0;
    try {
      for (i = 0; i < width; i++) {
        for (j = 0; j < height; j++) {
          // get the rgb values of the pixel
          int red = sourceImage.getPixel(i, j).getRed();
          int green = sourceImage.getPixel(i, j).getGreen();
          int blue = sourceImage.getPixel(i, j).getBlue();

          // calculate the new rgb values
          int luma = (int) ((0.2126 * red)
                  + (0.7152 * green)
                  + (0.0722 * blue));

          // set the new rgb values
          newImage.setPixel(i, j, new Pixel(luma, luma, luma));
        }
      }
    } catch (NullPointerException e) {
      throw new NullPointerException("Pixel " + i + ", " + j + " is null");
    }
    return newImage;
  }
}
