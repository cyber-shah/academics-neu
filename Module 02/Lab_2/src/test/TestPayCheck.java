import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 *  Student Name : Pranchal Shah.
 *  Semester : Summer 2023.
 *  Assignment : Lab 2.
 *
 * <p>
 * Test class for Paycheck Class.
 * This class tests all the public methods of the Paycheck class.
 * </p>
 */

public class TestPayCheck {
  /**
   * Test class for Paycheck Class.
   */

  private PayCheck p1;
  private PayCheck p2;
  private PayCheck p3;

  @Before
  public void setUp() {
    p1 = new PayCheck("John Snow", 10.0, 10.0);
    p2 = new PayCheck("Jane King", 15.0, 20.0);
    p3 = new PayCheck("Jack Rand", 20.0, 30.0);
  }

  @Test
  public void testGetTotalPay() {
    assertEquals(100.0, p1.getTotalPay(), 0.0);
    assertEquals(300.0, p2.getTotalPay(), 0.0);
    assertEquals(600.0, p3.getTotalPay(), 0.0);
  }

  @Test
  public void testToString() {
    assertEquals("$100.00", p1.toString());
    assertEquals("$300.00", p2.toString());
    assertEquals("$600.00", p3.toString());
  }
}