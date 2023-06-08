/**
 * PolynomialImpl.
 * This class implements the Polynomial interface.
 * Uses LinkedList to store the terms of the polynomial.
 */
public class PolynomialImpl implements Polynomial {
  private Node head;

  /**
   * Constructor.
   */
  public PolynomialImpl() {
    this.head = null;
  }

  /**
   * Constructor.
   *
   * @param polynomial The polynomial in string form.
   */
  public PolynomialImpl(String polynomial) {
    // split string into terms
    String[] terms = polynomial.split(" ");
    // for each term
    for (String term : terms) {
      // split term into coefficient and power
      String[] parts = term.split("x\\^");
      if (parts.length == 1) {
        parts = new String[]{parts[0], "0"};
      }
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
    addTermHelper(coefficient, power, this.head);
  }

  /**
   * Helper method that recursively adds a term to the polynomial.
   * @param coefficient The coefficient of the term.
   * @param power The power of the term.
   * @param current The current node.
   */
  private void addTermHelper(int coefficient, int power, Node current) {
    // base cases ---------------------------------------------------------
    // if power is negative, throw exception
    if (power < 0) {
      throw new IllegalArgumentException("power must be positive");
    }
    else if (coefficient == 0) {
      return;
    }

    // if empty list, set head to new node
    if (this.head == null) {
      this.head = new Node(coefficient, power);
      return;
    }

    // if new node has same power as current node, add coefficients
    if (current.getPower() == power) {
      current.setCoefficient(current.getCoefficient() + coefficient);
      return;
    }
    // if current's power is less than new node's power, insert new node before current node
    else if (current.getPower() < power) {
      Node newNode = new Node(coefficient, power);
      newNode.setNext(current);
      this.head = newNode;
      return;
    }
    // reached last node
    else if (current.getNext() == null) {
      current.setNext(new Node(coefficient, power));
      return;
    }
    // insert after current node if next node has lower power
    else if (current.getNext().getPower() < power) {
      Node newNode = new Node(coefficient, power);
      newNode.setNext(current.getNext());
      current.setNext(newNode);
      return;
    }
    // recursive case -----------------------------------------------------
    else {
      addTermHelper(coefficient, power, current.getNext());
    }
  }

  /**
   * Removes a term from the polynomial.
   *
   * @param power The power of the term to remove.
   */
  public void removeTerm(int power) {
    removeTermHelper(power, head);
  }

  /**
   * Helper method that recursively removes a term from the polynomial.
   *
   * @param power The power of the term to remove.
   * @param current The current node.
   */
  public void removeTermHelper(int power, Node current) {
    // base cases ------------------------------------------------
    // empty list
    if (current == null) {
      return;
    }
    // head is the node to be removed
    else if (current.getPower() == power) {
      head = head.getNext();
      return;
    }
    // reached last node
    else if (current.getNext() == null) {
      // last node is the node to be removed
      if (current.getPower() == power) {
        current = null;
        return;
      }
      // current node is not the node to be removed
      else {
        return;
      }
    }
    // recursive case ---------------------------------------------
    else {
      if (current.getNext().getPower() == power) {
        current.setNext(current.getNext().getNext());
        return;
      }
      else {
        removeTermHelper(power, current.getNext());
      }
    }
  }

  /**
   * Returns the degree of the polynomial.
   *
   * @return The degree of the polynomial.
   */
  public int getDegree() {
    if (head == null) {
      return 0;
    }
    else {
      return head.getPower();
    }
  }

  /**
   * Returns the coefficient of the term with the specified power.
   *
   * @param power The power of the term whose coefficient is to be returned.
   * @return The coefficient of the term with the specified power.
   */
  public int getCoefficient(int power) {
    return getCoefficientHelper(power, head);
  }

  /**
   * Helper function for getCoefficient().
   * Recursively finds the coefficient of the term with the specified power.
   *
   * @param power The power of the term whose coefficient is to be returned.
   * @param current The current node.
   * @return The coefficient of the term with the specified power.
   */
  private int getCoefficientHelper(int power, Node current) {
    // base cases
    if (current == null) {
      return 0;
    }
    else if (current.getPower() == power) {
      return current.getCoefficient();
    }
    // recursive case
    else {
      return getCoefficientHelper(power, current.getNext());
    }
  }

  /**
   * Returns the value of the polynomial for the specified value of the variable.
   *
   * @param value The value of the variable.
   * @return The value of the polynomial for the specified value of the variable.
   */
  public double evaluate(double value) {
    return evaluateHelper(value, head);
  }

  /**
   * Helper function for evaluate(). Recursively evaluates the polynomial.
   * @param value The value of the variable.
   * @param current The current node.
   * @return The value of the polynomial for the specified value of the variable.
   */
  private double evaluateHelper(double value, Node current) {
    // base case
    if (current == null) {
      return 0;
    }
    // recursive case
    else {
      double termEvaluation = current.getCoefficient() * Math.pow(value, current.getPower());
      return termEvaluation + evaluateHelper(value, current.getNext());
    }
  }

  /**
   * Adds the specified polynomial to this polynomial and returns the result as a new polynomial.
   *
   * @param other The polynomial to add to this polynomial.
   * @return The result of adding the specified polynomial to this polynomial.
   */
  public Polynomial add(Polynomial other) {
    Polynomial result = new PolynomialImpl();
    int max_degree = Math.max(this.getDegree(), other.getDegree());
    return addHelper(other, result, max_degree);
  }

  /**
   * Helper function for add(). Recursively adds the specified polynomial to this polynomial.
   * @param other The polynomial to add to this polynomial.
   * @param result The polynomial to store the result of the addition.
   * @param maxDegree The maximum degree of the two polynomials.
   * @return The result of adding the specified polynomial to this polynomial.
   */
  private Polynomial addHelper(Polynomial other, Polynomial result, int maxDegree) {
    // base case
    if (maxDegree < 0) {
      return result;
    }
    // recursive case
    else {
      int coefficient = this.getCoefficient(maxDegree) + other.getCoefficient(maxDegree);
      result.addTerm(coefficient, maxDegree);
      return addHelper(other, result, maxDegree - 1);
    }
  }

  /**
   * Returns a string representation of the polynomial.
   * @return A string representation of the polynomial.
   */
  public String toString() {
    return toStringHelper(head);
  }

  /*
   * Helper function for toString()
   * @param current The current node
   * @return A string representation of the polynomial.
   */
  private String toStringHelper(Node current) {
    // Base case: empty list
    if (current == null) {
      return "0";
    }

    // Base case: last node
    if (current.getNext() == null) {
      if (current.getCoefficient() > 0) {
        return "+" + current.toString();
      }
      else {
        return current.toString();
      }
    }

    // Recursive case: append current node and call the helper function on the next node
    if (current == this.head) {
      // for head, no sign if positive
      return current.toString() + " " + toStringHelper(current.getNext());
    }
    else {
      if (current.getCoefficient() > 0) {
        return "+" + current.toString() + " " + toStringHelper(current.getNext());
      }
      else {
        return current.toString() + " " + toStringHelper(current.getNext());
      }
    }
  }
}