/**
 * This interface represents a polynomial.
 * A polynomial is a mathematical expression of a sum of terms,
 * where each term is a constant multiplied by a variable raised to some power.
 */
public interface Polynomial {

  /**
   * Adds a term to the polynomial.
   *
   * @param coefficient The coefficient of the term.
   * @param power The power of the term.
   */
  void addTerm(int coefficient, int power);

  /**
   * Removes a term from the polynomial.
   *
   * @param power The power of the term to remove.
   */
  void removeTerm(int power);

  /**
   * Returns the degree of the polynomial.
   *
   * @return The degree of the polynomial.
   */
  int getDegree();

  /**
   * Returns the coefficient of the term with the specified power.
   *
   * @param power The power of the term whose coefficient is to be returned.
   * @return The coefficient of the term with the specified power.
   */
  int getCoefficient(int power);

  /**
   * Returns the value of the polynomial for the specified value of the variable.
   *
   * @param value The value of the variable.
   * @return The value of the polynomial for the specified value of the variable.
   */
  double evaluate(double value);

  /**
   * Adds the specified polynomial to this polynomial and returns the result as a new polynomial.
   *
   * @param polynomial The polynomial to add to this polynomial.
   * @return The result of adding the specified polynomial to this polynomial.
   */
  Polynomial add(Polynomial polynomial);

}