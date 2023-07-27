package testio;

import controller.io.BufferedImageLoader;
import controller.io.PPMImageLoader;
import model.image.CustomImageState;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * test file to test image loader with other formats.
 * 1. test load() with a JPG
 * 2. test load() with a PNG
 * 3. test load() with a BMP
 * 4. test load() with invalid file
 * 5. test load() with null file
 * 6. test load() with a file with unsupported extension
 * 7. test load() with a file with no extension
 */
public class TestImageLoader {

  private CustomImageState customImage;
  private String path;

  @Test
  public void testSetParamsJPG() throws FileNotFoundException {
    this.path = "resources/testimage.jpg";
    this.customImage = new BufferedImageLoader().load(path);
    assertEquals(200, customImage.getWidth());
    assertEquals(160, customImage.getHeight());
    assertEquals(255, customImage.getMaxValue());
  }

  @Test
  public void testSetParamsPNG() throws FileNotFoundException {
    this.path = "resources/testimage.png";
    this.customImage = new BufferedImageLoader().load(path);
    assertEquals(200, customImage.getWidth());
    assertEquals(160, customImage.getHeight());
    assertEquals(255, customImage.getMaxValue());
  }

  @Test
  public void testSetParamsBMP() throws FileNotFoundException {
    this.path = "resources/testimage.bmp";
    this.customImage = new BufferedImageLoader().load(path);
    assertEquals(200, customImage.getWidth());
    assertEquals(160, customImage.getHeight());
    assertEquals(255, customImage.getMaxValue());
  }

  @Test (expected = FileNotFoundException.class)
  public void testInvalidFile() throws FileNotFoundException {
    this.path = "resources/testimage1.jpg";
    this.customImage = new BufferedImageLoader().load(path);
  }

  @Test (expected = FileNotFoundException.class)
  public void testNullFile() throws FileNotFoundException {
    this.path = null;
    this.customImage = new BufferedImageLoader().load(path);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testUnsupportedExtension() throws FileNotFoundException {
    this.path = "resources/testimage.ppm";
    this.customImage = new BufferedImageLoader().load(path);
  }
}
