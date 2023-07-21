package testIO;

import model.Image.Image;
import controller.IO.PPMImageLoader;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Test class for PPMImageLoader.
 * It tests the following:
 * 1. SetParams
 * 2. SetParamsIncomplete
 * 3. NoIntegers
 * 4. NotP3
 * 5. Pixels
 * 6. PixelsIncomplete
 * 7. PixelsNoIntegers
 *
 */
public class testPPMImageLoader {

  private Image image;
  private String path;

  @Test
  public void testSetParams() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.image = new PPMImageLoader().load(path);
    assertEquals(4, image.getWidth());
    assertEquals(4, image.getHeight());
    assertEquals(255, image.getMaxValue());
  }

  @Test (expected = NoSuchElementException.class)
  public void testSetParamsIncompelete() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testIO\\testIncompleteFile.ppm";
    this.image = new PPMImageLoader().load(path);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNoIntegers() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testImageLoader\\testNoIntegers.ppm";
    this.image = new PPMImageLoader().load(path);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNotP3() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testImageLoader\\testNotP3.ppm";
    this.image = new PPMImageLoader().load(path);
  }

  @Test
  public void testPixels() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.image = new PPMImageLoader().load(path);

    assertEquals(11, image.getPixel(0,0).getRed());

    assertEquals(12, image.getPixel(1,0).getRed());

    assertEquals(13, image.getPixel(3,0).getRed());
  }
}