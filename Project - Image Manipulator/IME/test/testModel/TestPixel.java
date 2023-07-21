package testModel;

import model.Image.Pixel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for Pixel.
 *    * 1. Test the constructor.
 *    * 2. Test the constructor with a null argument.
 *    * 3. Test with invalid red value.
 *    * 4. Test with invalid green value.
 *    * 5. Test with invalid blue value.
 *    * 6. Test getRed. 7. Test getGreen. 8. Test getBlue.
 *    * 9. Test setRed. 10. Test setGreen. 11. Test setBlue.
 *    * 12. Test setRed with an invalid value.
 *    * 13. Test setGreen with an invalid value.
 *    * 14. Test setBlue with an invalid value.
 */
public class TestPixel {


  @Test
  public void testParams() {
    Pixel pixel = new Pixel(1, 2, 3);
    assertEquals(1, pixel.getRed());
    assertEquals(2, pixel.getGreen());
    assertEquals(3, pixel.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRed() {
    Pixel pixel = new Pixel(-1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreen() {
    Pixel pixel = new Pixel(0, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBlue() {
    Pixel pixel = new Pixel(0, 0, -1);
  }

  @Test
  public void testGetRed() {
    Pixel pixel = new Pixel(0, 0, 0);
    assertEquals(0, pixel.getRed());
  }

  @Test
  public void testGetGreen() {
    Pixel pixel = new Pixel(0, 0, 0);
    assertEquals(0, pixel.getGreen());
  }

  @Test
  public void testGetBlue() {
    Pixel pixel = new Pixel(0, 0, 0);
    assertEquals(0, pixel.getBlue());
  }

  @Test
  public void testSetRed() {
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setRed(200);
    assertEquals(200, pixel.getRed());
  }

  @Test
  public void testSetGreen() {
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setGreen(255);
    assertEquals(255, pixel.getGreen());
  }

  @Test
  public void testSetBlue() {
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setBlue(255);
    assertEquals(255, pixel.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidRed() {
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setRed(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidGreen() {
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setGreen(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidBlue() {
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.setBlue(-1);
  }

  @Test
  public void testToString() {
    Pixel pixel = new Pixel(0, 0, 0);
    assertEquals("(0, 0, 0)", pixel.toString());
  }

}
