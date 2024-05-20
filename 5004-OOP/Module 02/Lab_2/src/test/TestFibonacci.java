import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 *  Student Name : Pranchal Shah.
 *  Semester : Summer 2023.
 *  Assignment : Lab 2.
 *
 * <p>
 * Test class for FibonacciCounter Class.
 * This class tests all the public methods of the FibonacciCounter class.
 * </p>
 */
public class TestFibonacci {
  /**
   * Test class for FibonacciCounter Class.
   */

  private FibonacciCounter f1;
  private FibonacciCounter f2;
  private FibonacciCounter f3;

  @Before
  public void setUp() {
    f1 = new FibonacciCounter(0);
    f2 = new FibonacciCounter(1);
    f3 = new FibonacciCounter(2);
  }

  @Test
  public void testGetCount() {
    assertEquals(0, f1.getCount());
    assertEquals(1, f2.getCount());
    assertEquals(2, f3.getCount());
  }

  @Test
  public void testIncrement() {
    assertEquals(1, f1.increment().getCount());
    assertEquals(2, f2.increment().getCount());
    assertEquals(3, f3.increment().getCount());
  }

  @Test
  public void testGetFibonacci() {
    assertEquals(0, f1.getFibonacci());
    assertEquals(1, f2.getFibonacci());
    assertEquals(1, f3.getFibonacci());
  }

  @Test
  public void testDecrement() {
    assertEquals(0, f2.decrement().getCount());
    assertEquals(1, f3.decrement().getCount());
  }

  @Test(expected = IllegalStateException.class)
  public void testDecrementException() {
    f1.decrement();
  }
}