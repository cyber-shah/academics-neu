package model.Image;

public class Image implements ImageState {
  private final int width;
  private final int height;
  private final int maxValue;
  private final IPixel[][] pixelsList;


  /**
   * Constructor for Image.
   *
   * @param width  int value of width.
   * @param height int value of height.
   * @param maxValue int value of maxValue.
   */
  public Image(int width, int height, int maxValue) {
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
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
   * Getter for maxValue.
   *
   * @return int value of maxValue.
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * Getter for pixel.
   *
   * @param x int value of x.
   * @param y int value of y.
   * @return Pixel value of pixel.
   */
  @Override
  public Pixel getPixel(int x, int y) throws NullPointerException {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      String message = "Coordinates " + x + ", " + y + " are out of bounds"
              + " for an image of size " + this.width + " x " + this.height;
      throw new NullPointerException(message);
    }
    else if (this.pixelsList[x][y] == null) {
      throw new NullPointerException("Pixel at " + x + ", " + y + " is null");
    }
    else {
      return (Pixel) this.pixelsList[x][y];
    }
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

  /**
   * Setter for pixel.
   *
   * @param x     int value of x.
   * @param y     int value of y.
   * @param pixel Pixel value of pixel.
   */
  @Override
  public void setPixel(int x, int y, Pixel pixel) {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      throw new IllegalArgumentException("Invalid pixel coordinates");
    }
    else if (pixel == null) {
      throw new IllegalArgumentException("Invalid pixel");
    }
    else if (pixel.getMaxValue() > this.maxValue) {
      throw new IllegalArgumentException("Invalid pixel maxValue");
    }
    this.pixelsList[x][y] = pixel;
  }

}
