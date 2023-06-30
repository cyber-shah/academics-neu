import org.junit.Assert;
import org.junit.Test;

public class JFibonacciTest {

    @Test
    public void testFibonacciIterative() {
        // Test case 1: N = 0
        Long result1 = JFibonacci.fibonacciIterative(0L, 1);
        Assert.assertEquals(0L, result1.longValue());

        // Test case 2: N = 1
        Long result2 = JFibonacci.fibonacciIterative(1L, 1);
        Assert.assertEquals(1L, result2.longValue());

        // Test case 3: N = 10
        Long result3 = JFibonacci.fibonacciIterative(10L, 1);
        Assert.assertEquals(55L, result3.longValue());
    }

    @Test
    public void testFibonacciRecursive() {
        // Test case 1: N = 0
        Long result1 = JFibonacci.fibonacciRecursive(0L);
        Assert.assertEquals(0L, result1.longValue());

        // Test case 2: N = 1
        Long result2 = JFibonacci.fibonacciRecursive(1L);
        Assert.assertEquals(1L, result2.longValue());

        // Test case 3: N = 10
        Long result3 = JFibonacci.fibonacciRecursive(10L);
        Assert.assertEquals(55L, result3.longValue());
    }

    @Test
    public void testFibonacciDynamicManager() {
        // Test case 1: N = 0
        Long result1 = JFibonacci.fibonacciDynamicManager(0L, 1);
        Assert.assertEquals(0L, result1.longValue());

        // Test case 2: N = 1
        Long result2 = JFibonacci.fibonacciDynamicManager(1L, 1);
        Assert.assertEquals(1L, result2.longValue());

        // Test case 3: N = 10
        Long result3 = JFibonacci.fibonacciDynamicManager(10L, 1);
        Assert.assertEquals(55L, result3.longValue());
    }
}
