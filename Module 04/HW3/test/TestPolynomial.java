import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPolynomial {
  private Polynomial p1;

  @Before
  public void setUp() {
    p1 = new PolynomialImpl();
  }

  @Test
  public void testAddTerm() {
    p1.addTerm(2, 3);
    p1.addTerm(4, 1);
    p1.addTerm(3, 0);

    assertEquals("2x^3 + 4x^1 + 3x^0", p1.toString());
  }

}