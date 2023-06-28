import java.util.Arrays;
import java.lang.Long;

public class Fibonacci {

  public static void main(String[] args) {
    if (args.length < 3) {
      System.out.println("Usage: java Fibonacci <N> <MODE> <PRINT>");
      System.out.println("    N: The Nth value of the Fibonacci sequence");
      System.out.println(" MODE: 11 - Iteratively");
      System.out.println("       12 - Recursion");
      System.out.println("       13 - Dynamic programming");
      System.out.println("PRINT: 0 - No print");
      System.out.println("       1 - Nth number only");
      System.out.println("       2 - print all numbers");
      System.exit(1);
    }

    Long N = Long.parseLong(args[0]);
    if (N < 1) {
      System.out.println("N must be greater than 0");
      System.exit(1);
    }

    // defaults
    int mode = 1;
    int print = 0;

    if (args.length > 1) {
      mode = Integer.parseInt(args[1]);
    }
    if (args.length > 2) {
      print = Integer.parseInt(args[2]);
    }

    fibonacciManager(N, mode, print);
  }

  public static void fibonacciManager(Long N, int mode, int print) {
    switch (mode) {
      case 12:
        fibonacciRecursiveManager(N, print);
        break;
      case 13:
        fibonacciDynamicManager(N, print);
        break;
      default:
        fibonacciIterative(N, print);
        break;
    }
  }

  public static Long fibonacciIterative(Long N, int print) {
    Long a = 0L, b = 1L;
    Long c = a + b;

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
      if (i == N) {
        return c;
      } else {
        a = b;
        b = c;
        c = a + b;
      }
    }
    return c;
  }

  public static Long fibonacciRecursive(Long N) {
    if (N == 0) {
      return 0L;
    } else if (N == 1) {
      return 1L;
    } else {
      return fibonacciRecursive(N - 1) + fibonacciRecursive(N - 2);
    }
  }

  public static void fibonacciRecursiveManager(Long N, int print) {
    Long value = fibonacciRecursive(N);
    if (print > 0) {
      System.out.println("Recursive Fibonacci Value at " + N + " = " + value);
    }
  }

  public static Long fibonacciDynamicManager(Long N, int print) {
    Long[] valueTable = new Long[N.intValue() + 1];

    fibonacciDP(N, valueTable);

    if (print == 1) {
      System.out.println("Dynamic Programming Fibonacci Value at " + N + " = " + valueTable[N.intValue()]);
    } else if (print == 2) {
      for (int i = 0; i <= N; i++) {
        if (i == N) {
          System.out.print(valueTable[i] + "\n");
        } else {
          System.out.print(valueTable[i] + ", ");
        }
      }
    }
    return valueTable[N.intValue()];
  }

  public static void fibonacciDP(Long N, Long[] valueTable) {
    valueTable[0] = 0L;
    if (N > 0) {
      valueTable[1] = 1L;
      for (int i = 2; i <= N; i++) {
        valueTable[i] = valueTable[i - 1] + valueTable[i - 2];
      }
    }
  }
}
