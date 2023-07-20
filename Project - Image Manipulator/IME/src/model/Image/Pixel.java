package model.Image;

public class Pixel implements IPixel {
  private int Red;
  private int Green;
  private int Blue;
  private int maxValue;

  /**
   * Constructor for Pixel.
   * if the value of Red, Green, Blue is greater than 255, it will be set to 255.
   *
   * @param Red int value of Red.
   * @param Green int value of Green.
   * @param Blue int value of Blue.
   */
  public Pixel(int Red, int Green, int Blue) {
    Pixel pixel = new Pixel(Red, Green, Blue, 255);
  }

  /**
   * Constructor for Pixel.
   * if the value of Red, Green, Blue is greater than 255, it will be set to 255.
   *
   * @param Red int value of Red.
   * @param Green int value of Green.
   * @param Blue int value of Blue.
   * @param maxValue int value of maxColor.
   */
  public Pixel(int Red, int Green, int Blue, int maxValue) {
    this.Red = Red;
    this.Green = Green;
    this.Blue = Blue;
    if (this.Red > maxValue || this.Green > maxValue || this.Blue > maxValue) {
      throw new IllegalArgumentException("The value of Red, Green, Blue should be less than maxValue.");
    }
  }

  /**
   * Getter for Red.
   *
   * @return int value of Red.
   */
  public int getRed() {
    return this.Red;
  }

  /**
   * Getter for Green.
   *
   * @return int value of Green.
   */
  public int getGreen() {
    return this.Green;
  }

  /**
   * Getter for Blue.
   *
   * @return int value of Blue.
   */
  public int getBlue() {
    return this.Blue;
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
   * Setter for Red.
   *
   * @param Red int value of Red.
   */
  public void setRed(int Red) {
    if (Red > 255) {
      Red = 255;
    }
    this.Red = Red;
  }

  /**
   * Setter for Green.
   *
   * @param Green int value of Green.
   */
  public void setGreen(int Green) {
    if (Green > 255) {
      Green = 255;
    }
    this.Green = Green;
  }

  /**
   * Setter for Blue.
   *
   * @param Blue int value of Blue.
   */
  public void setBlue(int Blue) {
    if (Blue > 255) {
      Blue = 255;
    }
    this.Blue = Blue;
  }

  /**
   * Setter for Red, Green, Blue.
   *
   * @param Red int value of Red.
   * @param Green int value of Green.
   * @param Blue int value of Blue.
   */
  public void setAll(int Red, int Green, int Blue) {
    if (Red > 255) {
      Red = 255;
    }
    if (Green > 255) {
      Green = 255;
    }
    if (Blue > 255) {
      Blue = 255;
    }
    this.Red = Red;
    this.Green = Green;
    this.Blue = Blue;
  }

  /**
   * Sets the value of Red, Green, Blue to the same value.
   *
   * @param channel int value of Red, Green, Blue.
   */
  public void setChannel(int channel) {
    this.Red = channel;
    this.Green = channel;
    this.Blue = channel;
  }

  /**
   * ToString method for Pixel.
   *
   * @return String representation of Pixel.
   */
  @Override
  public String toString() {
    return "(" + this.Red + ", " + this.Green + ", " + this.Blue + ")";
  }
}
