import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 *  Student Name : Pranchal Shah.
 *  Semester : Summer 2023.
 *  Assignment : Lab 2.
 *
 * <p>
 * Test class for Employee Class.
 * This class tests all the public methods of the Employee class.
 * </p>
 */

public class TestEmployee {
  /**
   * Test class for Employee Class.
   */

  private Employee e1;
  private Employee e2;
  private Employee e3;

  @Before
  public void setUp() {
    e1 = new Employee("John Snow", 10.0);
    e2 = new Employee("Jane King", 15.0);
    e3 = new Employee("Jack Rand", 20.0);
  }

  @Test
  public void testGetHoursWorked() {
    assertEquals(0.0, e1.getHoursWorked(), 0.0);
    assertEquals(0.0, e2.getHoursWorked(), 0.0);
    assertEquals(0.0, e3.getHoursWorked(), 0.0);
  }

  @Test
  public void testGetPayRate() {
    assertEquals(10.0, e1.getPayRate(), 0.0);
    assertEquals(15.0, e2.getPayRate(), 0.0);
    assertEquals(20.0, e3.getPayRate(), 0.0);
  }

  @Test
  public void testGetName() {
    assertEquals("John Snow", e1.getName());
    assertEquals("Jane King", e2.getName());
    assertEquals("Jack Rand", e3.getName());
  }

  @Test
  public void testAddHoursWorked() {
    e1.addHoursWorked(10.0);
    e2.addHoursWorked(20.0);
    e3.addHoursWorked(30.0);
    assertEquals(10.0, e1.getHoursWorked(), 0.0);
    assertEquals(20.0, e2.getHoursWorked(), 0.0);
    assertEquals(30.0, e3.getHoursWorked(), 0.0);
  }

  @Test
  public void testResetHoursWorked() {
    e1.addHoursWorked(10.0);
    e2.addHoursWorked(20.0);
    e3.addHoursWorked(30.0);
    e1.resetHoursWorked();
    e2.resetHoursWorked();
    e3.resetHoursWorked();
    assertEquals(0.0, e1.getHoursWorked(), 0.0);
    assertEquals(0.0, e2.getHoursWorked(), 0.0);
    assertEquals(0.0, e3.getHoursWorked(), 0.0);
  }

  @Test
  public void testToString() {
    assertEquals("John Snow, 10.0, 0.0", e1.toString());
    assertEquals("Jane King, 15.0, 0.0", e2.toString());
    assertEquals("Jack Rand, 20.0, 0.0", e3.toString());
  }


  @Test
  public void testGetWeeklyCheck() {
    e1.addHoursWorked(10.0);
    e2.addHoursWorked(20.0);
    e3.addHoursWorked(30.0);
    PayCheck pc1 = e1.getWeeklyCheck();
    PayCheck pc2 = e2.getWeeklyCheck();
    PayCheck pc3 = e3.getWeeklyCheck();
    assertEquals(100.0, pc1.getTotalPay(), 0.0);
    assertEquals(300.0, pc2.getTotalPay(), 0.0);
    assertEquals(600.0, pc3.getTotalPay(), 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPayRate() {
    Employee e4 = new Employee("Jack Rand", 0);
    Employee e5 = new Employee("Jack Rand", -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeHoursWorked() {
    e1.addHoursWorked(-10.0);
    e2.addHoursWorked(-20.0);
    e3.addHoursWorked(-30.0);
  }

}
