/**
 *  Student Name : Pranchal Shah.
 *  Semester : Summer 2023.
 *  Assignment : Lab 2.
 *
 * <p>
 * This is a class for Employee.
 * </p>
 */


public class Employee {
  private final String name;
  private final double payRate;
  private double hoursWorked;

  /**
   * Constructor for Employee class.
   * @param name Name of the employee
   * @param payRate Pay rate of the employee
   *                Pay rate has to be greater than 0
   *                If pay rate is less than or equal to 0, throw IllegalArgumentException
   *                Hours worked is initialized to 0
   */
  public Employee(String name, double payRate) {
    this.name = name;
    this.payRate = payRate;
    if (payRate <= 0.0) {
      throw new IllegalArgumentException("Pay rate has to be greater than 0");
    }
    this.hoursWorked = 0.0;
  }

  /**
   * Getter method for name of the employee.
   * @return Name of the employee
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter method for pay rate of the employee.
   * @return Pay rate of the employee
   */
  public double getPayRate() {
    return this.payRate;
  }

  /**
   * Getter method for hours worked by the employee.
   * @return Hours worked by the employee
   */
  public double getHoursWorked() {
    return this.hoursWorked;
  }

  /**
   * Adds hours worked by the employee.
   * @param hoursWorked Hours worked by the employee
   */
  public void addHoursWorked(double hoursWorked) {
    if (hoursWorked < 0.0) {
      throw new IllegalArgumentException("Hours worked cannot be negative");
    }
    else {
      this.hoursWorked += hoursWorked;
    }
  }

  /**
   * Resets hours worked by the employee.
   */
  public void resetHoursWorked() {
    this.hoursWorked = 0.0;
  }

  /**
   * Returns the weekly paycheck of the employee.
   * @return Weekly paycheck of the employee
   */
  public PayCheck getWeeklyCheck() {
    PayCheck pc = new PayCheck(this.name,this.payRate, this.hoursWorked);
    this.resetHoursWorked();
    return pc;
  }

  /**
   * Returns the string representation of the employee.
   * @return String representation of the employee
   */
  public String toString() {
    return this.name + ", " + this.payRate + ", " + this.hoursWorked;
  }
}