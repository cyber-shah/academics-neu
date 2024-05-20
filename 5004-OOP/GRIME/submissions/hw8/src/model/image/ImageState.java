package model.image;

/**
 * This interface represents an Image.
 * It represents an Image that cannot be mutated.
 */
public interface ImageState {

  int getWidth();

  int getHeight();

  IPixel[][] getPixelsList();

  Pixel getPixel(int x, int y);

  int getMaxValue();
}
