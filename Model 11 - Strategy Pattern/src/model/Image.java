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
    int i = width * height;
    this.pixelsList = new Pixel[i];
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


  public void setPixelsList(Pixel[] pixelsList) {
    this.pixelsList = pixelsList;
  }

  public void setPixel(int i, Pixel pixel) {
    this.pixelsList[i] = pixel;
  }

  public Pixel getPixel(int i) {
    return this.pixelsList[i];
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Width:").append(this.width).append("\n");
    stringBuilder.append("Height:").append(this.height).append("\n");

    for (Pixel pixel : this.pixelsList) {
      stringBuilder.append(pixel.toString()).append("\n");
    }
    return stringBuilder.toString();
  }
}
