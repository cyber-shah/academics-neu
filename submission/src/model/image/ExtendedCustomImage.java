package model.image;

public interface ExtendedCustomImage extends CustomImageState {
  public void setPixel(int x, int y, Pixel pixel);
}
