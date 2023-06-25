public class Fibonacci {

  // TODO : Find the Nth value of the Fibonacci sequence
  //          1. Iteratively
  //          2. Recursion
  //          3. Dynamic programming

  // TODO : Print the Fibonacci Series from 1 to N:
  //          1. Print just N
  //          2. Print nothing/execute correctly

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: java Fibonacci <N> <MODE> <PRINT>");
      System.out.println("    N: The Nth value of the Fibonacci sequence");
      System.out.println(" MODE: 1 - Iteratively");
      System.out.println("       2 - Recursion");
      System.out.println("       3 - Dynamic programming");
      System.out.println("PRINT: 0 - No print");
      System.out.println("       1 - Nth number only");
      System.out.println("       2 - print all numbers");
      System.exit(1);
    }

    int N = Integer.parseInt(args[0]);
    if (N < 1) {
      System.out.println("N must be greater than 0");
      System.exit(1);
    }
    if (N > 100) {
      System.out.println("N must be less than 100");
      System.exit(1);
    }

    // defaults
    int mode = 1;
    int print = 0;

    if (args.length > 2) {
      mode = Integer.parseInt(args[1]);
    }
    if (args.length > 3) {
      print = Integer.parseInt(args[2]);
    }

    fibonacciManager(N, mode, print);
  }

  public static void fibonacciManager(int N, int mode, int print) {
    switch (mode) {
      case 1:
        fibonacciIterative(N, print);
        break;
      case 2:
        fibonacciRecursive(N, print);
        break;
      case 3:
        fibonacciDynamic(N, print);
        break;
      default:
        fibonacciIterative(N, print);
        break;
    }
  }

  public static void fibonacciIterative(int N, int print) {
    
  }

  public static void fibonacciRecursive(int N, int print) {
    // TODO : Implement
  }

  public static void fibonacciDynamic(int N, int print) {
    // TODO : Implement
  }
}