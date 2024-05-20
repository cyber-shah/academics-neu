package model.image;

import java.awt.image.BufferedImage;

/**
 * This class represents a Image.
 * Built up by a 2D array of Pixels.
 * The value of each channel should be greater than 0.
 * // TODO : manage PPM class, can you convert all PPM images into buffered image?
 */
public class PPMImage implements CustomImageMutable {
  private final int width;
  private final int height;
  private final int maxValue;
  private final PixelMutable[][] pixelsList;

  /**
   * Constructor for Image.
   *
   * @param width  int value of width.
   * @param height int value of height.
   * @param maxValue int value of maxValue.
   */
  public PPMImage(int width, int height, int maxValue) {
    if (width <= 0 || height <= 0 || maxValue <= 0) {
      throw new IllegalArgumentException("Invalid image parameters");
    }
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.pixelsList = new PixelMutable[width][height];
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
  public Pixel getPixel(int x, int y) throws IllegalArgumentException {
    if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
      String message = "From Image: Coordinates " + x + ", " + y + " are out of bounds"
              + " for an image of size " + this.width + " x " + this.height;
      throw new IllegalArgumentException(message);
    }
    else if (this.pixelsList[x][y] == null) {
      throw new IllegalArgumentException("Pixel at " + x + ", " + y + " is null");
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
  public PixelState[][] getPixelsList() {
    return this.pixelsList;
  }

  /**
   * Getter for bufferedImage.
   * Converts PPM Image to BufferedImage.
   *
   * @return BufferedImage value of bufferedImage.
   */
  @Override
  public BufferedImage getBufferedImage() {
    BufferedImage bufferedImage = new BufferedImage(this.width, this.height,
            BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j ++) {
        bufferedImage.setRGB(i, j, this.pixelsList[i][j].getRGB());
      }
    }
    return bufferedImage;
  }

  /**
   * Getter for histogram values.
   *
   * @return a 2D array of the histogram values.
   */
  @Override
  public int[][] getHistogramValues() {
    int[][] histogramValues = new int[4][256];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 256; j++) {
        histogramValues[i][j] = 0;
      }
    }

    int width = this.getWidth();
    int height = this.getHeight();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int red = this.getPixel(i, j).getRed();
        int green = this.getPixel(i, j).getGreen();
        int blue = this.getPixel(i, j).getBlue();

        int average = (red + green + blue) / 3;

        histogramValues[0][red]++;
        histogramValues[1][green]++;
        histogramValues[2][blue]++;
        histogramValues[3][average]++;
      }
    }
    return histogramValues;
  }

  /**
   * Setter for pixel.
   *
   * @param x     int value of x.
   * @param y     int value of y.
   * @param pixel Pixel value of pixel.
   */
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
