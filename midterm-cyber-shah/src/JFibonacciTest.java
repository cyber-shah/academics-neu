import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JFibonacciTest {

    @Test
    public void testFibonacciIterative() {
        long result = JFibonacci.fibonacciIterative(0L, 0);
        Assertions.assertEquals(0L, result);

        result = JFibonacci.fibonacciIterative(1L, 0);
        Assertions.assertEquals(1L, result);

        result = JFibonacci.fibonacciIterative(2L, 0);
        Assertions.assertEquals(1L, result);

        result = JFibonacci.fibonacciIterative(5L, 0);
        Assertions.assertEquals(5L, result);

        result = JFibonacci.fibonacciIterative(10L, 0);
        Assertions.assertEquals(55L, result);
    }

    @Test
    public void testFibonacciRecursive() {
        long result = JFibonacci.fibonacciRecursive(0L);
        Assertions.assertEquals(0L, result);

        result = JFibonacci.fibonacciRecursive(1L);
        Assertions.assertEquals(1L, result);

        result = JFibonacci.fibonacciRecursive(2L);
        Assertions.assertEquals(1L, result);

        result = JFibonacci.fibonacciRecursive(5L);
        Assertions.assertEquals(5L, result);

        result = JFibonacci.fibonacciRecursive(10L);
        Assertions.assertEquals(55L, result);
    }

    @Test
    public void testFibonacciDynamicManager() {
        long result = JFibonacci.fibonacciDynamicManager(0L, 0);
        Assertions.assertEquals(0L, result);

        result = JFibonacci.fibonacciDynamicManager(1L, 0);
        Assertions.assertEquals(1L, result);

        result = JFibonacci.fibonacciDynamicManager(2L, 0);
        Assertions.assertEquals(1L, result);

        result = JFibonacci.fibonacciDynamicManager(5L, 0);
        Assertions.assertEquals(5L, result);

        result = JFibonacci.fibonacciDynamicManager(10L, 0);
        Assertions.assertEquals(55L, result);
    }
}
