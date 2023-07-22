package model.Image;

/**
 * This class represents a Pixel.
 * A Pixel has three channels: Red, Green, Blue.
 * The value of each channel should be greater than 0.
 * The value of each channel should be less than maxValue.
 */
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
    this(Red, Green, Blue, 255);
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
    if (Red < 0 || Green < 0 || Blue < 0) {
      throw new IllegalArgumentException("The value of Red, Green, Blue should be greater than 0.");
    }
    this.Red = Red;
    this.Green = Green;
    this.Blue = Blue;
    this.maxValue = maxValue;
    if (this.Red > maxValue || this.Green > maxValue || this.Blue > maxValue) {
      throw new IllegalArgumentException("The value of Red, Green, Blue should be less than maxValue.");
    }
  }

  /**
   * Getter for Red.
   *
   * @return int value of Red.
   */
  @Override
  public int getRed() {
    return this.Red;
  }

  /**
   * Getter for Green.
   *
   * @return int value of Green.
   */
  @Override
  public int getGreen() {
    return this.Green;
  }

  /**
   * Getter for Blue.
   *
   * @return int value of Blue.
   */
  @Override
  public int getBlue() {
    return this.Blue;
  }

  /**
   * Getter for maxValue.
   *
   * @return int value of maxValue.
   */
  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * Setter for Red.
   *
   * @param Red int value of Red.
   */
  @Override
  public void setRed(int Red) throws IllegalArgumentException {
    if (Red > maxValue) {
      Red = maxValue;
    }
    else if (Red < 0) {
      throw new IllegalArgumentException("The value of Red should be greater than 0.");
    }
    this.Red = Red;
  }

  /**
   * Setter for Green.
   *
   * @param Green int value of Green.
   */
  @Override
  public void setGreen(int Green) throws IllegalArgumentException {
    if (Green > maxValue) {
      Green = maxValue;
    }
    else if (Green < 0) {
      throw new IllegalArgumentException("The value of Green should be greater than 0.");
    }
    this.Green = Green;
  }

  /**
   * Setter for Blue.
   *
   * @param Blue int value of Blue.
   */
  @Override
  public void setBlue(int Blue) throws IllegalArgumentException {
    if (Blue > maxValue) {
      Blue = maxValue;
    }
    else if (Blue < 0) {
      throw new IllegalArgumentException("The value of Blue should be greater than 0.");
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
