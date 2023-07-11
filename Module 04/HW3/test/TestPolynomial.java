import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * This class tests the Polynomial interface and the PolynomialImpl class.
 * Tests include adding terms, removing terms, getting the degree, getting the coefficient,
 * evaluating the polynomial, and adding two polynomials.
 * Tests also include testing the constructor that takes a string.
 */
public class TestPolynomial {
  private Polynomial p1;
  private Polynomial p3;
  private Polynomial p2;
  private Polynomial p4;

  @Before
  public void setUp() {
    p1 = new PolynomialImpl();
    p2 = new PolynomialImpl();
    p4 = new PolynomialImpl("8x^4 +4x^3 -11x^2 -2x^1 +1");
    p3 = new PolynomialImpl("3x^0 +2x^3 +4x^1");}

  @Test
  public void testAddTerm() {
    // added terms with same power to see if coefficients are added
    p1.addTerm(1, 3);
    p1.addTerm(1, 3);
    p1.addTerm(4, 1);
    p1.addTerm(3, 0);
    assertEquals("2x^3 +4x^1 +3", p1.toString());

    // add terms in random order to check if terms are being added in the correct position
    // negative coefficients included
    p2.addTerm(2, 0);
    p2.addTerm(5, 3);
    p2.addTerm(-4, 1);
    p2.addTerm(3, 2);
    assertEquals("5x^3 +3x^2 -4x^1 +2", p2.toString());

    // check the implementation of the constructor that takes a string
    assertEquals("2x^3 +4x^1 +3", p3.toString());

    // adding a term with zero coefficient
    // should not add anything
    p3.addTerm(0, 3);
    assertEquals("2x^3 +4x^1 +3", p3.toString());
  }

  @Test
  public void testToString() {
    // single term
    Polynomial p6 = new PolynomialImpl("-8x^4");
    assertEquals("-8x^4", p6.toString());

    // empty polynomial
    Polynomial p7;
    p7 = new PolynomialImpl();
    assertEquals("0", p7.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testAddTermNegativeCoefficient() {
    p1.addTerm(2, -3);
  }

  @Test
  public void testRemove() {
    // remove head or highest power
    p3.removeTerm(3);
    assertEquals("4x^1 +3", p3.toString());

    // remove middle
    p3.addTerm(2, 3);
    p3.removeTerm(1);
    assertEquals("2x^3 +3", p3.toString());

    // remove tail or last or lowest power
    p3.addTerm(4, 1);
    p3.removeTerm(0);
    assertEquals("2x^3 +4x^1", p3.toString());

    // remove non-existent term, should not change the term
    p3.removeTerm(5);
    assertEquals("2x^3 +4x^1", p3.toString());

    // remove only term in polynomial
    p3.removeTerm(3);
    p3.removeTerm(1);
    assertEquals("0", p3.toString());

    // remove term with zero coefficient
    p3.addTerm(0, 3);
    p3.removeTerm(3);
    assertEquals("0", p3.toString());

    // add everything back
    p3.addTerm(2, 3);
    p3.addTerm(4, 1);
    p3.addTerm(3, 0);
    assertEquals("2x^3 +4x^1 +3", p3.toString());
  }

  @Test
  public void testGetDegree() {
    // highest power in multiple terms
    assertEquals(3, p3.getDegree());
    assertEquals(4, p4.getDegree());

    // testing an empty polynomial
    assertEquals(0, p1.getDegree());

    // testing a polynomial with only one term
    p1.addTerm(2, 3);
    assertEquals(3, p1.getDegree());
    p1.removeTerm(3);
  }

  @Test
  public void testGetCoefficient() {
    // testing a polynomial with multiple terms
    assertEquals(4, p4.getCoefficient(3));
    assertEquals(-11, p4.getCoefficient(2));
    assertEquals(-2, p4.getCoefficient(1));
    assertEquals(1, p4.getCoefficient(0));

    // testing a polynomial with only one term
    p1.addTerm(2, 3);
    assertEquals(2, p1.getCoefficient(3));
    p1.removeTerm(3);

    // testing a polynomial with no terms
    assertEquals(0, p1.getCoefficient(3));

    // testing a polynomial with a term with zero coefficient
    p1.addTerm(0, 3);
    assertEquals(0, p1.getCoefficient(3));
    p1.removeTerm(3);

    // testing a term that does not exist
    assertEquals(0, p4.getCoefficient(5));
  }

  @Test
  public void testEvaluate() {
    // evaluate zero
    assertEquals(1, p4.evaluate(0), 0.0001);

    //positive value
    assertEquals(0, p4.evaluate(1), 0.0001);

    // negative value
    assertEquals(-4, p4.evaluate(-1), 0.0001);

    // testing a polynomial with only one term
    p1.addTerm(2, 3);
    assertEquals(16, p1.evaluate(2), 0.0001);
    p1.removeTerm(3);

    // testing a polynomial with no terms
    assertEquals(0, p1.evaluate(2), 0.0001);

    // testing a polynomial with a term with zero coefficient
    p1.addTerm(0, 3);
    assertEquals(0, p1.evaluate(2), 0.0001);
    p1.removeTerm(3);
  }

  @Test
  public void testAdd() {
    // empty polynomial
    Polynomial p5;
    p5 = new PolynomialImpl();
    assertEquals("8x^4 +4x^3 -11x^2 -2x^1 +1", p4.add(p5).toString());

    // two polynomials with no common power
    Polynomial p8;
    p8 = new PolynomialImpl("3x^3 +1x^1");
    Polynomial p9;
    p9 = new PolynomialImpl("-4x^4 -2x^2 -0");
    assertEquals("-4x^4 +3x^3 -2x^2 +1x^1", p8.add(p9).toString());

    // two polynomials with common power
    Polynomial p10;
    p10 = new PolynomialImpl("3x^3 +1x^1");
    Polynomial p11;
    p11 = new PolynomialImpl("-4x^3 -2x^1 -0");
    Polynomial p12 = p10.add(p11);
    assertEquals(p12.toString(), p10.add(p11).toString());
  }
}