package testmodel;

import model.image.CPixel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


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
    CPixel pixel = new CPixel(1, 2, 3);
    assertEquals(1, pixel.getRed());
    assertEquals(2, pixel.getGreen());
    assertEquals(3, pixel.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRed() {
    CPixel pixel = new CPixel(-1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreen() {
    CPixel pixel = new CPixel(0, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBlue() {
    CPixel pixel = new CPixel(0, 0, -1);
  }

  @Test
  public void testGetRed() {
    CPixel pixel = new CPixel(0, 0, 0);
    assertEquals(0, pixel.getRed());
  }

  @Test
  public void testGetGreen() {
    CPixel pixel = new CPixel(0, 0, 0);
    assertEquals(0, pixel.getGreen());
  }

  @Test
  public void testGetBlue() {
    CPixel pixel = new CPixel(0, 0, 0);
    assertEquals(0, pixel.getBlue());
  }

  @Test
  public void testSetRed() {
    CPixel pixel = new CPixel(0, 0, 0);
    pixel.setRed(200);
    assertEquals(200, pixel.getRed());
  }

  @Test
  public void testSetGreen() {
    CPixel pixel = new CPixel(0, 0, 0);
    pixel.setGreen(255);
    assertEquals(255, pixel.getGreen());
  }

  @Test
  public void testSetBlue() {
    CPixel pixel = new CPixel(0, 0, 0);
    pixel.setBlue(255);
    assertEquals(255, pixel.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidRed() {
    CPixel pixel = new CPixel(0, 0, 0);
    pixel.setRed(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidGreen() {
    CPixel pixel = new CPixel(0, 0, 0);
    pixel.setGreen(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidBlue() {
    CPixel pixel = new CPixel(0, 0, 0);
    pixel.setBlue(-1);
  }

  @Test
  public void testToString() {
    CPixel pixel = new CPixel(0, 0, 0);
    assertEquals("(0, 0, 0)", pixel.toString());
  }

}
