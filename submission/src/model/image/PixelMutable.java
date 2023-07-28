package model.image;

/**
 * This interface represents a Pixel.
 * Extends IPixelState and makes it mutable.
 */
public interface PixelMutable extends PixelState {

  /**
   * Sets the red value of the pixel.
   *
   * @param red the red value to set.
   */
  void setRed(int red);

  /**
   * Sets the green value of the pixel.
   *
   * @param green the green value to set.
   */
  void setGreen(int green);

  /**
   * Sets the blue value of the pixel.
   *
   * @param blue the blue value to set.
   */
  void setBlue(int blue);

  /**
   * Sets the RGB value of the pixel.
   *
   * @param rgb the RGB value to set.
   */
  void setRGB(int rgb);
}
