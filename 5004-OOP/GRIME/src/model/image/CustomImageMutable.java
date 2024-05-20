package model.image;

/**
 * This interface represents an extended custom image.
 * Adds methods that can mutate the image.
 */
public interface CustomImageMutable extends CustomImageState {

  /**
   * Sets the pixel at the given coordinates to the given pixel.
   *
   * @param x the x coordinate of the pixel.
   * @param y the y coordinate of the pixel.
   * @param pixel the pixel to set.
   */
  void setPixel(int x, int y, Pixel pixel);
}
