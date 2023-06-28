
public class JTestsFibonacci {

    JFibonacci jFibonacci = new JFibonacci();

    @Before
    public void setUp() throws Exception {
        jFibonacci = new JFibonacci();
    }

    @Test
    public void testFibonacciIterative() {
        assertEquals(0L, jFibonacci.fibonacciIterative(0, 0));
        assertEquals(1L, jFibonacci.fibonacciIterative(1, 0));
        assertEquals(1, jFibonacci.fibonacciIterative(2, 0));
        assertEquals(2, jFibonacci.fibonacciIterative(3, 0));
        assertEquals(3, jFibonacci.fibonacciIterative(4, 0));
        assertEquals(5, jFibonacci.fibonacciIterative(5, 0));
        assertEquals(8, jFibonacci.fibonacciIterative(6, 0));
        assertEquals(13, jFibonacci.fibonacciIterative(7, 0));
        assertEquals(21, jFibonacci.fibonacciIterative(8, 0));
        assertEquals(34, jFibonacci.fibonacciIterative(9, 0));
        assertEquals(55, jFibonacci.fibonacciIterative(10, 0));
        assertEquals(89, jFibonacci.fibonacciIterative(11, 0));
        assertEquals(144, jFibonacci.fibonacciIterative(12, 0));
        assertEquals(233, jFibonacci.fibonacciIterative(13, 0));
        assertEquals(377, jFibonacci.fibonacciIterative(14, 0));
        assertEquals(610, jFibonacci.fibonacciIterative(15, 0));
        assertEquals(987, jFibonacci.fibonacciIterative(16, 0));
        assertEquals(1597, jFibonacci.fibonacciIterative(17, 0));
        assertEquals(2584, jFibonacci.fibonacciIterative(18, 0));
        assertEquals(4181, jFibonacci.fibonacciIterative(19, 0));
        assertEquals(6765, jFibonacci.fibonacciIterative(20, 0));
        assertEquals(10946, jFibonacci.fibonacciIterative(21, 0));
        assertEquals(17711, jFibonacci.fibonacciIterative(22, 0));
        assertEquals(28657, jFibonacci.fibonacciIterative(23, 0));
    }
}
