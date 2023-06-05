import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPolynomial {
  private Polynomial p1;
  private Polynomial p2;

  @Before
  public void setUp() {
    p1 = new PolynomialImpl();
    p2 = new PolynomialImpl();
  }

  @Test
  public void testAddTerm() {
    // added terms in order of their positions
    p1.addTerm(2, 3);
    p1.addTerm(4, 1);
    p1.addTerm(3, 0);
    assertEquals("2x^3 + 4x^1 + 3x^0", p1.toString());

    // add terms in non-ascending order
    // to check if terms are being added in the correct position
    p2.addTerm(2, 0);
    p2.addTerm(4, 1);
    p2.addTerm(3, 2);
    p2.addTerm(5, 3);
    assertEquals("5x^3 + 3x^2 + 4x^1 + 2x^0", p2.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddTermNegativeCoefficient() {
    p1.addTerm(-2, 3);
  }

}