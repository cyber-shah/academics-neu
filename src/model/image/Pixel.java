package model.image;

import java.awt.*;

/**
 * This class represents a Pixel.
 * A Pixel has three channels: Red, Green, Blue.
 * The value of each channel should be greater than 0.
 * The value of each channel should be less than maxValue.
 */
public class Pixel implements PixelExtended {
  private int red;
  private int green;
  private int blue;
  private int maxValue;

  /**
   * Constructor for Pixel.
   * if the value of red, green, blue is greater than 255, it will be set to 255.
   *
   * @param red int value of red.
   * @param green int value of green.
   * @param blue int value of blue.
   */
  public Pixel(int red, int green, int blue) {
    this(red, green, blue, 255);
  }

  /**
   * Constructor for Pixel.
   * if the value of red, green, blue is greater than 255, it will be set to 255.
   *
   * @param red int value of red.
   * @param green int value of green.
   * @param blue int value of blue.
   * @param maxValue int value of maxColor.
   */
  public Pixel(int red, int green, int blue, int maxValue) {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("The value of red, green, blue should be greater than 0.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.maxValue = maxValue;
    if (this.red > maxValue || this.green > maxValue || this.blue > maxValue) {
      throw new IllegalArgumentException("The value of red, green, "
              + "blue should be less than maxValue.");
    }
  }

  /**
   * Getter for Red.
   *
   * @return int value of Red.
   */
  @Override
  public int getRed() {
    return this.red;
  }

  /**
   * Getter for Green.
   *
   * @return int value of Green.
   */
  @Override
  public int getGreen() {
    return this.green;
  }

  /**
   * Getter for Blue.
   *
   * @return int value of Blue.
   */
  @Override
  public int getBlue() {
    return this.blue;
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
   * Setter for red.
   *
   * @param red int value of red.
   */
  @Override
  public void setRed(int red) throws IllegalArgumentException {
    if (red > maxValue) {
      red = maxValue;
    }
    else if (red < 0) {
      throw new IllegalArgumentException("The value of red should be greater than 0.");
    }
    this.red = red;
  }

  /**
   * Setter for green.
   *
   * @param green int value of green.
   */
  @Override
  public void setGreen(int green) throws IllegalArgumentException {
    if (green > maxValue) {
      green = maxValue;
    }
    else if (green < 0) {
      throw new IllegalArgumentException("The value of green should be greater than 0.");
    }
    this.green = green;
  }

  /**
   * Setter for blue.
   *
   * @param blue int value of blue.
   */
  @Override
  public void setBlue(int blue) throws IllegalArgumentException {
    if (blue > maxValue) {
      blue = maxValue;
    }
    else if (blue < 0) {
      throw new IllegalArgumentException("The value of blue should be greater than 0.");
    }
    this.blue = blue;
  }

  /**
   * Setter for red, green, blue.
   *
   * @param red int value of red.
   * @param green int value of green.
   * @param blue int value of blue.
   */
  public void setAll(int red, int green, int blue) {
    if (red > 255) {
      red = 255;
    }
    if (green > 255) {
      green = 255;
    }
    if (blue > 255) {
      blue = 255;
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Sets the value of Red, Green, Blue to the same value.
   *
   * @param channel int value of Red, Green, Blue.
   */
  public void setChannel(int channel) {
    this.red = channel;
    this.green = channel;
    this.blue = channel;
  }

  /**
   * ToString method for Pixel.
   *
   * @return String representation of Pixel.
   */
  @Override
  public String toString() {
    return "(" + this.red + ", " + this.green + ", " + this.blue + ")";
  }

  /**
   * Getter for RGB. Added in the extension.
   * This is to allow the usage of java.awt.Color.
   *
   * @return int value of RGB.
   */
  @Override
  public int getRGB() {
    int red = this.getRed();
    int green = this.getGreen();
    int blue = this.getBlue();
    Color color = new Color(red, green, blue);
    return color.getRGB();
  }

}
