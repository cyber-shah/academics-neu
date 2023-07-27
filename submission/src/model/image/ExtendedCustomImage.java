package model.image;

/**
 * This interface represents an extended custom image.
 * Adds methods that can mutate the image.
 */
public interface ExtendedCustomImage extends CustomImageState {
  public void setPixel(int x, int y, Pixel pixel);
}
