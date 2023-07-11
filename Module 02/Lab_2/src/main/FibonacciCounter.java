/**
 * Student Name : Pranchal Shah.
 * Semester : Summer 2023.
 * Assignment : Lab 2.
 *
 * <p>
 * This is a class for Fibonacci Counter using Binet's Formula.
 * </p>
 */

public class FibonacciCounter {
  private final int count;

  /**
   * Create a new FibonacciCounter with an initial count of zero.
   *
   * @param count the initial count.
   */
  public FibonacciCounter(int count) {
    this.count = count;
    if (this.count < 0) {
      throw new IllegalArgumentException("count must be non-negative.");
    }
  }

  /**
   * Increment the current count.
   *
   * @return the current count + 1.
   */
  public FibonacciCounter increment() {
    return new FibonacciCounter(count + 1);
  }

  /**
   * Decrement the count by one.
   *
   * @return a new FibonacciCounter with the count decremented by one.
   * @throws IllegalStateException if the count is already zero.
   */
  public FibonacciCounter decrement() {
    if (count == 0) {
      throw new IllegalStateException("Cannot decrement below zero.");
    }
    return new FibonacciCounter(count - 1);
  }

  /**
   * Get the current count.
   *
   * @return the current count.
   */
  public int getCount() {
    return count;
  }

  /**
   * Get the Fibonacci number corresponding to the current count.
   *
   * @return the Fibonacci number corresponding to the current count.
   * @throws ArithmeticException if the Fibonacci number exceeds Integer.MAX_VALUE.
   */
  public int getFibonacci() {
    if (this.count == 0) {
      return 0;
    }
    if (this.count == 1) {
      return 1;
    } else {
      double sqrt5 = Math.sqrt(5);
      double a = (1 + sqrt5) / 2;
      double b = (1 - sqrt5) / 2;
      long fibonacci = (long) ((Math.pow(a, count) - Math.pow(b, count)) / sqrt5);

      if (fibonacci > Integer.MAX_VALUE) {
        throw new ArithmeticException("Fibonacci number exceeds Integer.MAX_VALUE");
      } else {
        return Math.round(fibonacci);
      }
    }
  }
}