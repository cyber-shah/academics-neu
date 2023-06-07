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
      String[] parts = term.split("x\\^");
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
    if (power < 0) {
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
      // if new node has same power as current node, add coefficients
      if (current.getPower() == power) {
        current.setCoefficient(current.getCoefficient() + coefficient);
        return;
      }

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
    // PART 1 - CHECKS and BASE CASES ------------
    // if head is null, return
    if (head == null) {
      return;
    }
    // if head is the term to remove, set head to next node
    if (head.getPower() == power) {
      head = head.getNext();
      return;
    }
    // if head is greater than power, return since there is no
    // term with the specified power
    else if (head.getPower() < power) {
      return;
    }

    // PART 2 - TRAVERSAL ----------------------
    // else, start traversing at HEAD
    Node current = head;
    // traverse until current node's next node has the power to remove
    while (current.getNext() != null && current.getNext().getPower() != power) {
      current = current.getNext();
    }

    // if current node's next node is null, simply remove the tail
    // by setting the next node to null.
    if (current.getNext().getNext() == null) {
      current.setNext(null);
      return;
    }
    // else, remove the next node by setting the current node's next node
    else {
      current.setNext(current.getNext().getNext());
      return;
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
    // if head is null, return 0
    if (head == null) {
      return 0;
    }
    else {
      Node current = head;
      // traverse until current node's power is equal to the specified power or
      // you reach last node
      while (current.getNext() != null && current.getPower() != power) {
        current = current.getNext();
      }
      // if node not found, return 0
      if (current.getPower() != power) {
        return 0;
      }
      // else, return coefficient
      else {
        return current.getCoefficient();
      }
    }
  }

  /**
   * Returns the value of the polynomial for the specified value of the variable.
   *
   * @param value The value of the variable.
   * @return The value of the polynomial for the specified value of the variable.
   */
  public double evaluate(double value) {
    double evaluation = 0;

    Node current = head;
    while (current!= null) {
      evaluation += current.getCoefficient() * Math.pow(value, current.getPower());
      current = current.getNext();
    }
    return evaluation;
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
      return "";
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