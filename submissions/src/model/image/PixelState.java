package model.image;

/**
 * This interface represents a pixel in an image.
 * It represents a pixel that cannot be mutated.
 */
public interface PixelState {

  /**
   * Getter for the red value of the pixel.
   *
   * @return the red value of the pixel.
   */
  int getRed();

  /**
   * Getter for the green value of the pixel.
   *
   * @return the green value of the pixel.
   */
  int getGreen();

  /**
   * Getter for the blue value of the pixel.
   *
   * @return the blue value of the pixel.
   */
  int getBlue();

  /**
   * Getter for the maximum value of the pixel.
   *
   * @return the maximum value of the pixel.
   */
  int getMaxValue();

  /**
   * Getter for the RGB value of the pixel.
   *
   * @return the RGB value of the pixel.
   */
  int getRGB();
}
