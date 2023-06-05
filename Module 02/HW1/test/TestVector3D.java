import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 *  Student Name : Pranchal Shah
 *  Semester : Summer 2023
 *
 * Test class for Vector3D.
 * This class tests all the public methods of the Vector3D class.
 */
public class TestVector3D {

  private Vector3D v1;
  private Vector3D v2;
  private Vector3D v3;

  @Before
  public void setUp() {
    v1 = new Vector3D(1, 2, 3);
    v2 = new Vector3D(4, 5, 6);
    v3 = new Vector3D(7, 8, 9);
  }

  @Test
  public void testGetX() {
    assertEquals(1, v1.getX(), 0.001);
    assertEquals(4, v2.getX(), 0.001);
    assertEquals(7, v3.getX(), 0.001);
  }

  @Test
  public void getY() {
    assertEquals(2, v1.getY(), 0.001);
    assertEquals(5, v2.getY(), 0.001);
    assertEquals(8, v3.getY(), 0.001);
  }

  @Test
  public void getZ() {
    assertEquals(3, v1.getZ(), 0.001);
    assertEquals(6, v2.getZ(), 0.001);
    assertEquals(9, v3.getZ(), 0.001);
  }

  @Test
  public void testToString() {
    assertEquals("(1.00,2.00,3.00)", v1.toString());
    assertEquals("(4.00,5.00,6.00)", v2.toString());
    assertEquals("(7.00,8.00,9.00)", v3.toString());
  }

  @Test
  public void testGetMagnitude() {
    assertEquals(3.7416573867739413, v1.getMagnitude(), 0.001);
    assertEquals(8.774964387392123, v2.getMagnitude(), 0.001);
    assertEquals(13.92838827718412, v3.getMagnitude(), 0.001);
  }

  @Test
  public void testNormalize() {
    assertEquals("(0.27,0.53,0.80)", v1.normalize().toString());
    assertEquals("(0.46,0.57,0.68)", v2.normalize().toString());
    assertEquals("(0.50,0.57,0.65)", v3.normalize().toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testNormalizeZero() {
    Vector3D zero = new Vector3D(0, 0, 0);
    zero.normalize();
  }

  @Test
  public void testAdd() {
    assertEquals("(5.00,7.00,9.00)", v1.add(v2).toString());
    assertEquals("(11.00,13.00,15.00)", v2.add(v3).toString());
    assertEquals("(8.00,10.00,12.00)", v1.add(v3).toString());
  }

  @Test
  public void testMultiply() {
    assertEquals("(4.00,8.00,12.00)", v1.multiply(4).toString());
    assertEquals("(12.00,15.00,18.00)", v2.multiply(3).toString());
    assertEquals("(7.00,8.00,9.00)", v3.multiply(1).toString());
  }

  @Test
  public void testDotProduct() {
    assertEquals(32.0, v1.dotProduct(v2), 0.001);
    assertEquals(122.0, v2.dotProduct(v3), 0.001);
    assertEquals(50.0, v1.dotProduct(v3), 0.001);
  }

  @Test
  public void angleBetween() {
    assertEquals(12.933154491899105, v1.angleBetween(v2), 0.001);
    assertEquals(12.933154491899105, v2.angleBetween(v1), 0.001);
    assertEquals(3.4469524345065143, v2.angleBetween(v3), 0.001);
    assertEquals(3.4469524345065143, v3.angleBetween(v2), 0.001);
    assertEquals(16.380106926405656, v1.angleBetween(v3), 0.001);
    assertEquals(16.380106926405656, v3.angleBetween(v1), 0.001);
  }

  @Test (expected = IllegalStateException.class)
  public void angleBetweenZero() {
    Vector3D v = new Vector3D(0, 0, 0);
    v.angleBetween(v1);
    v.angleBetween(v2);
    v1.angleBetween(v);
  }
}