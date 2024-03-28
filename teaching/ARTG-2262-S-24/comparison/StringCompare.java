import java.util.Scanner; // Import the Scanner class

public class StringCompare {
  /**
   * String a = "Hello";
   * This creates a string literal in the string pool.
   * String literals in Java are stored in a special pool known as the string
   * pool,
   * and if a string with the same content already exists in the pool, the
   * existing reference is reused.
   * This helps save memory and improves performance.
   * 
   * "Hello" in the string pool is shared among all the references pointing to it.
   * Multiple variables can point to the same string literal, and they will share
   * the same memory.
   * 
   * 
   * new String("Hello") explicitly creates a new object in the heap, even if the
   * content is the same as an
   * existing string literal. This can lead to more memory consumption as each
   * object is distinct.
   */
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in); // Create a Scanner object

    System.out.print("Enter a string! : ");
    String stringOne = input.nextLine();
    String stringTwo = stringOne;

    System.out.println("String one is : " + stringOne);
    System.out.println("String two is : " + stringTwo);

    // create string literals
    // they will have the same hash codes, or same memory location
    System.out.println("-------------------- PART 1: Creating string literals ------------------ ");
    print_details(stringOne, "stringOne");
    print_details(stringTwo, "stringTwo");

    boolean result = stringOne == stringTwo;
    System.out.println("\nstringOne == stringTwo is :: " + result);

    System.out.println("\n\n");
    System.out.println("--------------------- PART 2: Uppercase ------------------ ");
    String OneUpper = stringOne.toUpperCase();
    String TwoUpper = stringTwo.toUpperCase();
    print_details(OneUpper, "OneUpper");
    print_details(TwoUpper, "TwoUpper");
    result = OneUpper == TwoUpper;
    System.out.println("\nOneUpper == TwoUpper is :: " + result);

    System.out.println("\n\n");
    System.out.println("--------------------- 2.1: Okay so now checking .equals() ------------------ ");
    System.out.println("OneUpper.equals(TwoUpper) is :: " + OneUpper.equals(TwoUpper));

    System.out.println("\n\n");
    System.out.println("--------------------- PART 3: Creating Objects instead of literals ------------------ ");
    String oneObject = new String(stringOne);
    String twoObject = new String(stringTwo);
    print_details(oneObject, "oneObject");
    print_details(twoObject, "twoObject");
    result = oneObject == twoObject;
    System.out.println("\noneObject == twoObject is :: " + result);

    System.out.println("\n\n");
    System.out.println("--------------------- 3.1 Okay so now checking with .equals() ------------------ ");
    System.out.println("stringOne.equals(stringTwo) is :: " + stringOne.equals(stringTwo));

    System.out.println("\n\n");
    System.out.println("Why this behaviour? ");
    System.out.println(
        "String literals in Java are stored in a special pool known as the string pool, and if a string with the same content already exists in the pool, the existing reference is reused. And that's why we see them working in the first case and then not working in the second case! \nThis helps save memory and improves performance!");
    System.out.println("And therefore always use .equals() to get predictable behaviour");
  }

  public static void print_details(String a, String name) {
    System.out.println("Memory address of " + name + " = " + System.identityHashCode(a));
    System.out.println(name + " = " + a);
  }

  public static void print_details(char a, String name) {
    System.out.println("Memory Location of " + name + " = " + System.identityHashCode(a));
    System.out.println(name + " = " + a);
  }

}

/**
 * // whereas for primitive data types, they will have the same hash codes
 * // even if we modify them
 * System.out.println("\n\n");
 * System.out.println("--------------------- PART 3: Chars literals
 * ------------------ ");
 * char aChar = 'a';
 * char bChar = 'a';
 * print_details(aChar, "aChar");
 * print_details(bChar, "bChar");
 * System.out.print("\naChar == bChar is ::");
 * System.out.print(aChar == bChar);
 * 
 * // Upper case
 * System.out.println("\n\n");
 * System.out.println("--------------------- 3.1: Chars to upper case
 * ------------------ ");
 * char aCharUpper = aChar.toUpperCase(aChar);
 * char bCharUpper = Character.toUpperCase(bChar);
 * print_details(aCharUpper, "aCharUpper");
 * print_details(bCharUpper, "bCharUpper");
 * System.out.print("\naCharUpper == bCharUpper is ::");
 * System.out.print(aCharUpper == bCharUpper);
 */
