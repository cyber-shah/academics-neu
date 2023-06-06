/**
 * PolynomialImpl
 * This class implements the Polynomial interface.
 * Uses LinkedList to store the terms of the polynomial.
 */
public class PolynomialImpl implements Polynomial {
  private Node head;


  /**
   * Constructor
   */
  public PolynomialImpl() {
    this.head = null;
  }

  /**
   * Constructor
   *
   * @param polynomial The polynomial in string form.
   */
  public PolynomialImpl (String polynomial) {
    // split string into terms
    String[] terms = polynomial.split(" ");
    // for each term
    for (String term : terms) {
      // split term into coefficient and power
      String[] parts = term.split("x^");
      int coefficient = Integer.parseInt(parts[0]);
      int power = Integer.parseInt(parts[1]);
      // add term to polynomial
      this.addTerm(coefficient, power);
    }
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

    // if head is null, set head to new node or
    // if new node has lower power than head, set head to new node
    if (head == null || head.getPower() < power) {
      newNode.setNext(head);
      this.head = newNode;
      return;
    }

    // else, start traversing at HEAD
    else {
      Node current = head;


      // else, traverse until new node has higher power than current node's next node
      while (current.getNext() != null && current.getNext().getPower() > power) {
        current = current.getNext();
      }
      // insert new node after current node
      newNode.setNext(current.getNext());
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