package model.image;

/**
 * This interface represents an Image.
 * It represents an Image that cannot be mutated.
 */
public interface CImageState {

  int getWidth();

  int getHeight();

  CPixelInterface[][] getPixelsList();

  CPixel getPixel(int x, int y);

  int getMaxValue();
}
