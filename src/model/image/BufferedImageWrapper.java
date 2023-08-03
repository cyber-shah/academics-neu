package model.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * This class is an adapter for BufferedImage.
 * NOTE: Basically it adapts BufferedImage to the CustomImageState interface.
 * This is done to continue using the CustomImageState interface and
 * allow the use of BufferedImage. Following the Open-Closed Principle.
 */
public class BufferedImageWrapper implements CustomImageMutable {
  private final BufferedImage bufferedImage;

  // ---------------------- CONSTRUCTORS -------------------------------------

  /**
   * Constructor to convert a BufferedImage to a CustomImageState.
   *
   * @param bufferedImage BufferedImage to be converted.
   */
  public BufferedImageWrapper(BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
  }

  /**
   * Constructor to create a new BufferedImage.
   *
   * @param width of the new BufferedImage.
   * @param height of the new BufferedImage.
   */
  public BufferedImageWrapper(int width, int height) {
    this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  // ---------------------- GETTERS ------------------------------------------

  /**
   * Getter for BufferedImage.
   *
   * @return BufferedImage.
   */
  public BufferedImage getBufferedImage() {
    return bufferedImage;
  }

  /**
   * Getter for histogram values.
   *
   * @return the histogram values.
   */
  @Override
  public int[][] getHistogramValues() {
    // initialize the array
    int[][] histogramValues = new int[4][256];

    // set all values to 0
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 256; j++) {
        histogramValues[i][j] = 0;
      }
    }

    // get the width and height of the image
    int width = this.getWidth();
    int height = this.getHeight();

    // iterate over all the pixels and increment the histogram values
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
   * Getter for width.
   *
   * @return int value of width.
   */
  @Override
  public int getWidth() {
    return bufferedImage.getWidth();
  }

  /**
   * Getter for height.
   *
   * @return int value of height.
   */
  @Override
  public int getHeight() {
    return bufferedImage.getHeight();
  }

  /**
   * Getter for pixelsList.
   *
   * @return PixelState[][] pixelsList.
   */
  @Override
  public PixelState[][] getPixelsList() {
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();

    PixelState[][] pixelsList = new PixelState[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Color color = new Color(bufferedImage.getRGB(i, j));
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        pixelsList[i][j] = new Pixel(red, green, blue);
      }
    }
    return pixelsList;
  }

  /**
   * Getter for pixel.
   * This method converts the RGB value of the pixel to a Pixel object.
   *
   * @param x int value of x.
   * @param y int value of y.
   * @return Pixel object.
   */
  @Override
  public Pixel getPixel(int x, int y) {
    Color color = new Color(bufferedImage.getRGB(x, y));
    int red = color.getRed();
    int green = color.getGreen();
    int blue = color.getBlue();
    return new Pixel(red, green, blue);
  }

  /**
   * Getter for maxValue. Always set to 255.
   *
   * @return 255.
   */
  @Override
  public int getMaxValue() {
    return 255;
  }


  // ---------------------- SETTERS -----------------------------------

  /**
   * Setter for pixel.
   *
   * @param x int value of x.
   * @param y int value of y.
   * @param pixel Pixel object.
   */
  @Override
  public void setPixel(int x, int y, Pixel pixel) {
    bufferedImage.setRGB(x, y, pixel.getRGB());
  }


}
