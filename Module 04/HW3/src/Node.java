/**
 * Node.
 * This class represents a node in a linked list.
 * Has a coefficient, power, and next node.
 */
public class Node {
  private int coefficient;
  private int power;
  private Node next;

  /**
   * Constructor.
   *
   * @param coefficient The coefficient of the term.
   * @param power       The power of the term.
   */
  public Node(int coefficient, int power) {
    this.coefficient = coefficient;
    this.power = power;
    this.next = null;
  }

  /**
   * Constructor.
   *
   * @param coefficient The coefficient of the term.
   * @param power       The power of the term.
   * @param next        The next node.
   */
  public Node(int coefficient, int power, Node next) {
    this.coefficient = coefficient;
    this.power = power;
    this.next = next;
  }

  /**
   * Returns the coefficient of the term.
   *
   * @return The coefficient of the term.
   */
  public int getCoefficient() {
    return this.coefficient;
  }

  /**
   * Returns the power of the term.
   *
   * @return The power of the term.
   */
  public int getPower() {
    return this.power;
  }

  /**
   * Returns the next node.
   *
   * @return The next node.
   */
  public Node getNext() {
    return this.next;
  }

  /**
   * Sets the coefficient of the term.
   *
   * @param coefficient The coefficient of the term.
   */
  public void setCoefficient(int coefficient) {
    this.coefficient = coefficient;
  }

  /**
   * Sets the power of the term.
   *
   * @param power The power of the term.
   */
  public void setPower(int power) {
    this.power = power;
  }

  /**
   * Sets the next node.
   *
   * @param next The next node.
   */
  public void setNext(Node next) {
    this.next = next;
  }

  /**
   * Returns a string representation of the node.
   *
   * @return A string representation of the node.
   */
  public String toString() {
    if (power == 0) {
      return String.valueOf(this.coefficient);
    }
    else {
      return this.coefficient + "x^" + this.power;
    }
  }
}