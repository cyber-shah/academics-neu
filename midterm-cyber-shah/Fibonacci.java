import java.util.Arrays;

public class Fibonacci {

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

    // defaults
    int mode = 1;
    int print = 0;

    if (args.length > 2) {
      mode = Integer.parseInt(args[1]);
    }
    if (args.length >= 2) {
      print = Integer.parseInt(args[2]);
    }

    fibonacciManager(N, mode, print);
  }

  public static void fibonacciManager(int N, int mode, int print) {
    switch (mode) {
      case 2:
        fibonacciRecursiveManager(N, print);
        break;
      case 3:
        fibonacciDynamicManager(N, print);
        break;
      default:
        fibonacciIterative(N, print);
        break;
    }
  }

  public static void fibonacciIterative(int N, int print) {
    int a = 0, b = 1;
    int c = a + b;

    if (print > 1) {
      if (N >= 1) {
        System.out.print(a);
      }
    }

    for (int i = 2; i <= N; i++) {
      if (print == 1) {
        if (i == N) {
          System.out.println("Iterative Fibonacci value at " + i + " = " + c);
        }
      }
      if (print > 1) {
        if (i == N) {
          System.out.println(", " + c);
        } else {
          System.out.print(", " + c);
        }
      }
      a = b;
      b = c;
      c = a + b;
    }
  }

  public static int fibonacciRecursive(int N) {
    if (N == 0) {
      return 0;
    } else if (N == 1) {
      return 1;
    } else {
      return fibonacciRecursive(N - 1) + fibonacciRecursive(N - 2);
    }
  }

  public static void fibonacciRecursiveManager(int N, int print) {
    int value = fibonacciRecursive(N);
    if (print > 0) {
      System.out.println("Recursive Fibonacci Value at " + N + " = " + value);
    }
  }

  public static int fibonacciDynamicManager(int N, int print) {
    int[] valueTable = new int[N + 1];

    fibonacciDP(N, valueTable);

    if (print == 1) {
      System.out.println("Dynamic Programming Fibonacci Value at " + N + " = " + valueTable[N]);
    } 
    else if (print == 2) {
      for (int i = 0; i <= N; i++) {
        if (i == N) {
          System.out.print(valueTable[i] + "\n");
        } 
        else {
          System.out.print(valueTable[i] + ", ");
        }
      }
    }
    return valueTable[N];
  }

  public static void fibonacciDP(int N, int[] valueTable) {
    valueTable[0] = 0;
    if (N > 0) {
      valueTable[1] = 1;
      for (int i = 2; i <= N; i++) {
        valueTable[i] = valueTable[i - 1] + valueTable[i - 2];
      }
    }
  }
}