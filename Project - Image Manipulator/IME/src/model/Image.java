package model;

public class Image implements IImageState {

  private final IPixel[][] pixels;

  public Image(int width, int height) {
    this.pixels = new IPixel[width][height];
  }

  // how can I add pixels to the image?

}
