package model.image;

/**
 * This interface represents a pixel in an image.
 * It represents a pixel that cannot be mutated.
 */
public interface PixelState {

  int getRed();

  int getGreen();

  int getBlue();

  int getMaxValue();

  int getRGB();
}
