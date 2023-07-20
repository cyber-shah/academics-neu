package model;

public class Image implements IImageState {
  private final int width;
  private final int height;
  private final IPixel[][] pixelsList;

  /**
   * Constructor for Image.
   *
   * @param width  int value of width.
   * @param height int value of height.
   */
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.pixelsList = new IPixel[width][height];
  }

  /**
   * Getter for width.
   *
   * @return int value of width.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Getter for height.
   *
   * @return int value of height.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Getter for pixel.
   *
   * @param x int value of x.
   * @param y int value of y.
   * @return Pixel value of pixel.
   */
  @Override
  public Pixel getPixel(int x, int y) {
    return (Pixel) this.pixelsList[x][y];
  }

  /**
   * Getter for pixelsList.
   *
   * @return Pixel[] value of pixelsList.
   */
  @Override
  public IPixel[][] getPixelsList() {
    return this.pixelsList;
  }

  // how can I add pixels to the image?
}
