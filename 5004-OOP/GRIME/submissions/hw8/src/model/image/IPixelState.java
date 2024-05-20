package model.image;

/**
 * This interface represents a pixel in an image.
 * It represents a pixel that cannot be mutated.
 */
public interface IPixelState {

  int getRed();

  int getGreen();

  int getBlue();

  int getMaxValue();
}
