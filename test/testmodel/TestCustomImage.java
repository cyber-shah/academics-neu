package testmodel;

import model.image.CImage;
import model.image.CPixel;
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
public class TestCustomImage {

  @Test
  public void testParams() {
    CImage customImage = new CImage(4, 4, 255);
    assertEquals(4, customImage.getWidth());
    assertEquals(4, customImage.getHeight());
    assertEquals(255, customImage.getMaxValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidth() {
    CImage customImage = new CImage(-1, 4, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHeight() {
    CImage customImage = new CImage(4, -1, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMaxValue() {
    CImage customImage = new CImage(4, 4, -1);
  }

  @Test
  public void testGetPixel() {
    CImage customImage = new CImage(4, 4, 255);
    CPixel pixel = new CPixel(0, 0, 0);
    customImage.setPixel(0, 0, pixel);
    assertEquals(pixel, customImage.getPixel(0, 0));
  }

  @Test(expected = NullPointerException.class)
  public void testGetPixelInvalidCoordinates() {
    CImage customImage = new CImage(4, 4, 255);
    CPixel pixel = new CPixel(0, 0, 0);
    customImage.setPixel(0, 0, pixel);
    customImage.getPixel(4, 4);
  }

  @Test(expected = NullPointerException.class)
  public void testGetPixelNullPixel() {
    CImage customImage = new CImage(4, 4, 255);
    customImage.getPixel(0, 0);
  }

  @Test
  public void testGetPixelsList() {
    CImage customImage = new CImage(4, 4, 255);
    CPixel pixel = new CPixel(0, 0, 0);
    customImage.setPixel(0, 0, pixel);
    assertEquals(pixel, customImage.getPixelsList()[0][0]);
  }

  @Test
  public void testSetPixel() {
    CImage customImage = new CImage(4, 4, 255);
    CPixel pixel = new CPixel(0, 0, 0);
    customImage.setPixel(0, 0, pixel);
    assertEquals(pixel, customImage.getPixel(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidCoordinates() {
    CImage customImage = new CImage(4, 4, 255);
    CPixel pixel = new CPixel(0, 0, 0);
    customImage.setPixel(4, 4, pixel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelNullPixel() {
    CImage customImage = new CImage(4, 4, 255);
    customImage.setPixel(0, 0, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelInvalidMaxValue() {
    CImage customImage = new CImage(4, 4, 255);
    CPixel pixel = new CPixel(0, 0, 256);
    customImage.setPixel(0, 0, pixel);
  }


}
