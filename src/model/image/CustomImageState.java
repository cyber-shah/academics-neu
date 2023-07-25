package model.image;

/**
 * This interface represents an Image.
 * It represents an Image that cannot be mutated.
 */
public interface CustomImageState {

  int getWidth();

  int getHeight();

  PixelState[][] getPixelsList();

  Pixel getPixel(int x, int y);

  int getMaxValue();
}
