import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPolynomial {
  private Polynomial p1;
  private Polynomial p3;
  private Polynomial p2;

  @Before
  public void setUp() {
    p1 = new PolynomialImpl();
    p2 = new PolynomialImpl();
    p3 = new PolynomialImpl("+3x^0 +2x^3 +4x^1");
  }

  @Test
  public void testAddTerm() {
    // added terms with same power to see if coefficients are added
    p1.addTerm(1, 3);
    p1.addTerm(1, 3);
    p1.addTerm(4, 1);
    p1.addTerm(3, 0);
    assertEquals("2x^3 +4x^1 +3x^0", p1.toString());

    // add terms in random order to check if terms are being added in the correct position
    // negative coefficients included
    p2.addTerm(2, 0);
    p2.addTerm(5, 3);
    p2.addTerm(-4, 1);
    p2.addTerm(3, 2);
    assertEquals("5x^3 +3x^2 -4x^1 +2x^0", p2.toString());


    // check the implementation of the constructor that takes a string
    assertEquals("2x^3 +4x^1 +3x^0", p3.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddTermNegativeCoefficient() {
    p1.addTerm(2, -3);
  }

  @Test
  public void testRemove() {
    // remove head
    p3.removeTerm(3);
    assertEquals("4x^1 +3x^0", p3.toString());

    // remove middle
    p3.addTerm(2, 3);
    p3.removeTerm(1);
    assertEquals("2x^3 +3x^0", p3.toString());

    // remove tail or last
    p3.addTerm(4, 1);
    p3.removeTerm(0);
    assertEquals("2x^3 +4x^1", p3.toString());
  }

}