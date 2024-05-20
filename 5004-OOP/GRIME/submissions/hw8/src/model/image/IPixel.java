package model.image;

/**
 * This interface represents a Pixel.
 * Extends IPixelState and makes it mutable.
 */
public interface IPixel extends IPixelState {
  void setRed(int red);

  void setGreen(int green);

  void setBlue(int blue);
}
