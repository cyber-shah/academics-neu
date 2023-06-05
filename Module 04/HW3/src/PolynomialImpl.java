/**
 * PolynomialImpl
 */
public class PolynomialImpl implements Polynomial {
  private Node head;


  public PolynomialImpl() {
    this.head = null;
  }

  /**
   * Adds a term to the polynomial.
   *
   * @param coefficient The coefficient of the term.
   * @param power       The power of the term.
   */
  public void addTerm(int coefficient, int power) {
    if (coefficient < 0) {
      throw new IllegalArgumentException("coefficient must be positive");
    }

    // create new node
    Node newNode = new Node(coefficient, power);

    // if head is null, set head to new node
    if (head == null) {
      this.head = newNode;
    }
    // else, traverse to the correct position and insert
    else {
      Node current = head;
//      while (current.getPower() > power && current.getNext() != null) {
//        current = current.getNext();
//      }
      if (current.getPower() > power) {
        newNode.setNext(current);
        this.head = newNode;
        return;
      }
      while (current.getNext() != null && current.getNext().getPower() < power) {
        current = current.getNext();
      }
      current.setNext(newNode);
    }
  }

  /**
   * Removes a term from the polynomial.
   *
   * @param power The power of the term to remove.
   */
  public void removeTerm(int power) {
    return;
  }

  /**
   * Returns the degree of the polynomial.
   *
   * @return The degree of the polynomial.
   */
  public int getDegree() {
    return 0;
  }

  /**
   * Returns the coefficient of the term with the specified power.
   *
   * @param power The power of the term whose coefficient is to be returned.
   * @return The coefficient of the term with the specified power.
   */
  public int getCoefficient(int power) {
    return 0;
  }

  /**
   * Returns the value of the polynomial for the specified value of the variable.
   *
   * @param value The value of the variable.
   * @return The value of the polynomial for the specified value of the variable.
   */
  public double evaluate(double value) {
    return 0;
  }

  /**
   * Adds the specified polynomial to this polynomial and returns the result as a new polynomial.
   *
   * @param polynomial The polynomial to add to this polynomial.
   * @return The result of adding the specified polynomial to this polynomial.
   */
  public Polynomial add(Polynomial polynomial) {
    return null;
  }

  /**
   * Returns a string representation of the polynomial.
   * @return A string representation of the polynomial.
   */
  public String toString () {
    return this.head.toString();
//    return toStringHelper(head);
  }

  /**
   * Helper function for toString()
   * @param current The current node
   * @return A string representation of the polynomial.

  private String toStringHelper(Node current) {
    // Base case: empty list
    if (current == null) {
      return "";
    }

    // Base case: last node
    if (current.getNext() == null) {
      return current.toString();
    }

    // Recursive case: append current node and call the helper function on the next node
    return current.toString() + " + " + toStringHelper(current.getNext());
  }
  */

}