/**
 *  Student Name : Pranchal Shah.
 *  Semester : Summer 2023.
 *  Assignment : Lab 2.
 *
 * <p>
 * This is a class for PayCheck.
 * </p>
 */
public class PayCheck {

  private final double payRate;
  private final double hoursWorked;

  /**
   * Constructor for PayCheck class.
   * @param name Name of the employee
   * @param payRate Pay rate of the employee
   * @param hoursWorked Hours worked by the employee
   */
  public PayCheck(String name, double payRate, double hoursWorked) {
    this.payRate = payRate;
    this.hoursWorked = hoursWorked;
  }

  /**
   * Getter method for getting the total pay of the employee.
   * @return Total pay of the employee.
   */
  public double getTotalPay() {
    return this.payRate * this.hoursWorked;
  }

  /**
   * To string method for PayCheck class.
   * @return String representation of the total pay of the employee.
   */
  public String toString() {
    return String.format("$%.2f", this.getTotalPay());
  }
}