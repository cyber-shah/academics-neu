package testImageLoader;

import model.Image.Image;
import model.Image.ImageLoader;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class testImageLoader {

  private Image image;
  private String path;

  @Test
  public void testSetParams() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testImageLoader\\test4x4.ppm";
    this.image = new ImageLoader().loadPPM(path);
    assertEquals(4, image.getWidth());
    assertEquals(4, image.getHeight());
    assertEquals(255, image.getMaxValue());
  }

  @Test (expected = NoSuchElementException.class)
  public void testSetParamsIncompelete() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testImageLoader\\testIncompleteFile.ppm";
    this.image = new ImageLoader().loadPPM(path);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNoIntegers() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testImageLoader\\testNoIntegers.ppm";
    this.image = new ImageLoader().loadPPM(path);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNotP3() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testImageLoader\\testNotP3.ppm";
    this.image = new ImageLoader().loadPPM(path);
  }

  @Test
  public void testPixels() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testImageLoader\\test4x4.ppm";
    this.image = new ImageLoader().loadPPM(path);

    assertEquals(11, image.getPixel(0,0).getRed());

    assertEquals(12, image.getPixel(1,0).getRed());

    assertEquals(13, image.getPixel(3,0).getRed());
  }
}