package testmodel;

import model.image.PPMImage;
import model.image.Pixel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Image.
   * 1. Test the constructor.
   * 2. Test the constructor with a null argument.
   * 3. Test with invalid width.
   * 4. Test with invalid height.
   * 5. Test with invalid max value.
   * 6. Test getPixel with valid coordinates.
   * 7. Test getPixel with invalid coordinates.
   * 8. Test getPixel with a null pixel.
   * 9. Test getPixelsList.
   * 10. Test setPixel with valid coordinates.
   * 11. Test setPixel with invalid coordinates.
   * 12. Test setPixel with a null pixel.
   * 13. Test setPixel with a pixel with invalid maxValue.
 */
public class TestExtendedCustomImage {

  @Test
  public void testParams() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    assertEquals(4, customImage.getWidth());
    assertEquals(4, customImage.getHeight());
    assertEquals(255, customImage.getMaxValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidth() {
    PPMImage customImage = new PPMImage(-1, 4, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHeight() {
    PPMImage customImage = new PPMImage(4, -1, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMaxValue() {
    PPMImage customImage = new PPMImage(4, 4, -1);
  }

  @Test
  public void testGetPixel() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    Pixel pixel = new Pixel(0, 0, 0);
    customImage.setPixel(0, 0, pixel);
    assertEquals(pixel, customImage.getPixel(0, 0));
  }

  @Test(expected = NullPointerException.class)
  public void testGetPixelInvalidCoordinates() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    Pixel pixel = new Pixel(0, 0, 0);
    customImage.setPixel(0, 0, pixel);
    customImage.getPixel(4, 4);
  }

  @Test(expected = NullPointerException.class)
  public void testGetPixelNullPixel() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    customImage.getPixel(0, 0);
  }

  @Test
  public void testGetPixelsList() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    Pixel pixel = new Pixel(0, 0, 0);
    customImage.setPixel(0, 0, pixel);
    assertEquals(pixel, customImage.getPixelsList()[0][0]);
  }

  @Test
  public void testSetPixel() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    Pixel pixel = new Pixel(0, 0, 0);
    customImage.setPixel(0, 0, pixel);
    assertEquals(pixel, customImage.getPixel(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidCoordinates() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    Pixel pixel = new Pixel(0, 0, 0);
    customImage.setPixel(4, 4, pixel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelNullPixel() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    customImage.setPixel(0, 0, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidMaxValue() {
    PPMImage customImage = new PPMImage(4, 4, 255);
    Pixel pixel = new Pixel(0, 0, 256);
    customImage.setPixel(0, 0, pixel);
  }


}
