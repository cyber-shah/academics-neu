/**
 * Student Name : Pranchal Shah
 * Semester : Summer 2023
 *
 * This class represents a 3D vector.
 * A vector (in 3D) is signified by a direction and a length (magnitude).
 * It is commonly represented as three components: x, y and z.
 */
public class Vector3D {

  // Instantiate The three components of the vector
  private final double x;
  private final double y;
  private final double z;

  /**
   * Constructor for a 3D vector.
   *    @param x The x component of the vector.
   *    @param y The y component of the vector.
   *    @param z The z component of the vector.
   */
  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Get X.
   *    @return float: The x component of the vector.
   */
  public double getX() {
    return x;
  }

  /**
   * Get Y.
   *    @return float: The y component of the vector.
   */
  public double getY() {
    return y;
  }

  /**
   * Get Z.
   *    @return float: The z component of the vector.
   */
  public double getZ() {
    return z;
  }

  /**
   * Convert the vector to a string.
   *    @return String: The string representation of the vector.
   *            in the form (x, y, z).
   */
  public String toString() {
    return String.format("(%.2f,%.2f,%.2f)", this.x, this.y, this.z);
  }

  /**
   * getMagnitude: Get the magnitude of the vector.
   *    @return float: The magnitude of the vector.
   */
  public double getMagnitude() {
    return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
  }

  /**
   * normalize: Normalize the vector.
   *    @return Vector3D: a new normalized vector.
   *    @throws IllegalStateException if the vector has zero magnitude.
   */
  public Vector3D normalize() {
    double magnitude = this.getMagnitude();
    if (magnitude == 0) {
      throw new IllegalStateException("Cannot normalize a vector with zero magnitude");
    }
    else {
      return new Vector3D(this.x / magnitude, this.y / magnitude, this.z / magnitude);
    }
  }


  /**
   * add Vector3D: Add two vectors.
   *    @param v The vector to add.
   *             (default value is 0).
   *    @return Vector3D: The sum of the two vectors.
   */
  public Vector3D add(Vector3D v) {
    return new Vector3D(this.x + v.getX(), this.y + v.getY(), this.z + v.getZ());
  }

  /**
   * multiply Vector3D: Multiply the vector with a constant
   * returning a new vector.
   *    @param constant The constant to multiply.
   *    @return Vector3D: The product of vector with a constant
   */
  public Vector3D multiply(double constant) {
    // --------------------------what if the constant is 0? --------------------------
    return new Vector3D(this.x * constant, this.y * constant, this.z * constant);
  }

  /**
   * dotProduct: Calculate the dot product of two vectors.
   *    @param v The vector to dot product with.
   *    @return float: The dot product of the two vectors.
   */
  public double dotProduct(Vector3D v) {
    // --------------------------what if the vector has magnitude is 0? --------------------------
    return this.x * v.getX() + this.y * v.getY() + this.z * v.getZ();
  }

  /**
   * checkZero: Check if the vector has magnitude 0.
   *    @param v The vector to check.
   *    @return boolean: True if the vector has magnitude 0.
   */
  private boolean checkZero(Vector3D v) {
    return v.getMagnitude() == 0;
  }

  /**
   * angleBetween: Calculate the angle between two vectors.
   *    @param v The vector to calculate the angle with.
   *    @return float: The angle between the two vectors.
   *    @throws IllegalStateException if either vector has zero magnitude.
   */
  public double angleBetween(Vector3D v) {
    if (this.checkZero(this) || this.checkZero(v)) {
      throw new IllegalStateException("Cannot calculate the angle between a zero vector");
    }
    else {
      double dotProduct = this.dotProduct(v);
      double angleRadians = Math.acos(dotProduct / (this.getMagnitude() * v.getMagnitude()));
      return Math.toDegrees(angleRadians);
    }
  }
}