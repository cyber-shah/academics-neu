package model;

public class Image {
  private final int width;
  private final int height;
  private Pixel[] pixelsList;


  /**
   * Constructor for Image.
   *
   * @param width  int value of width.
   * @param height int value of height.
   */
  public Image(int width, int height) {
    this.width = width;
    this.height = height;

    // Initialize the pixelsList to null values
    int i = width * height;
    this.pixelsList = new Pixel[i];
    for (int j = 0; j < i; j++) {
      this.pixelsList[j] = null;
    }
  }

  /**
   * Getter for width.
   *
   * @return int value of width.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Getter for height.
   *
   * @return int value of height.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Getter for pixelsList.
   *
   * @return Pixel[] value of pixelsList.
   */
  public Pixel[] getPixelsList() {
    return this.pixelsList;
  }

  /**
   * Setter for pixelsList.
   *
   * @param pixelsList Pixel[] value of pixelsList.
   */
  public void setPixelsList(Pixel[] pixelsList) {
    this.pixelsList = pixelsList;
  }

  /**
   * Setter for pixel.
   *
   * @param i     int value of index.
   * @param pixel Pixel value of pixel.
   */
  public void setPixel(int i, Pixel pixel) {
    this.pixelsList[i] = pixel;
  }

  /**
   * Getter for pixel.
   *
   * @param i int value of index.
   * @return Pixel value of pixel.
   */
  public Pixel getPixel(int i) {
    return this.pixelsList[i];
  }

  /**
   * toString method for Image.
   *
   * @return String value of Image.
   */
  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Width:").append(this.width).append("\n");
    stringBuilder.append("Height:").append(this.height).append("\n");

    for (Pixel pixel : this.pixelsList) {
      if (pixel == null) {
        continue;
      }
      else {
        stringBuilder.append(pixel.toString()).append("\n");
      }
    }
    return stringBuilder.toString();
  }
}
