package model.image;

import java.awt.image.BufferedImage;

/**
 * This interface represents an Image.
 * It represents an Image that cannot be mutated.
 */
public interface CustomImageState {

  /**
   * Getter for the width of the image.
   *
   * @return the width of the image.
   */
  int getWidth();

  /**
   * Getter for the height of the image.
   *
   * @return the height of the image.
   */
  int getHeight();

  /**
   * Getter for the pixels of the image.
   *
   * @return the pixels of the image.
   */
  PixelState[][] getPixelsList();

  /**
   * Getter for the pixel at the given coordinates.
   *
   * @param x the x coordinate of the pixel.
   * @param y the y coordinate of the pixel.
   * @return the pixel at the given coordinates.
   */
  Pixel getPixel(int x, int y);

  /**
   * Getter for the maximum value of the image.
   *
   * @return the maximum value of the image.
   */
  int getMaxValue();

  /**
   * Getter for the BufferedImage of the image.
   *
   * @return the BufferedImage of the image.
   */
  BufferedImage getBufferedImage();
}
