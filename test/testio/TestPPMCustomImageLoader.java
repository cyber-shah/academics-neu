package testio;

import model.image.CImage;
import controller.io.PPMImageLoader;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

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
 */
public class TestPPMCustomImageLoader {

  private CImage customImage;
  private String path;

  @Test
  public void testSetParams() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.customImage = new PPMImageLoader().load(path);
    assertEquals(4, customImage.getWidth());
    assertEquals(4, customImage.getHeight());
    assertEquals(255, customImage.getMaxValue());
  }

  @Test (expected = NoSuchElementException.class)
  public void testSetParamsIncompelete() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testIO\\testIncompleteFile.ppm";
    this.customImage = new PPMImageLoader().load(path);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNoIntegers() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testImageLoader\\testNoIntegers.ppm";
    this.customImage = new PPMImageLoader().load(path);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNotP3() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testImageLoader\\testNotP3.ppm";
    this.customImage = new PPMImageLoader().load(path);
  }

  @Test
  public void testPixels() throws FileNotFoundException {
    this.path = "C:\\Users\\shahp\\OneDrive - Northeastern University\\NEU\\CS-5004\\"
            + "Project - Image Manipulator\\IME\\test\\testIO\\test4x4.ppm";
    this.customImage = new PPMImageLoader().load(path);

    assertEquals(11, customImage.getPixel(0,0).getRed());

    assertEquals(12, customImage.getPixel(1,0).getRed());

    assertEquals(13, customImage.getPixel(3,0).getRed());
  }
}