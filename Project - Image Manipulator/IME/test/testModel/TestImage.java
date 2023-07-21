package testModel;

import model.Image.Image;
import model.Image.Pixel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestImage {

  /**
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

  @Test
  public void testParams() {
    Image image = new Image(4, 4, 255);
    assertEquals(4, image.getWidth());
    assertEquals(4, image.getHeight());
    assertEquals(255, image.getMaxValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullParams() {
    Image image = new Image(null, null , null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidth() {
    Image image = new Image(-1, 4, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHeight() {
    Image image = new Image(4, -1, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMaxValue() {
    Image image = new Image(4, 4, -1);
  }

  @Test
  public void testGetPixel() {
    Image image = new Image(4, 4, 255);
    Pixel pixel = new Pixel(0, 0, 0);
    image.setPixel(0, 0, pixel);
    assertEquals(pixel, image.getPixel(0, 0));
  }

}
