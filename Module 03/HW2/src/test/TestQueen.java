import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  Student Name : Pranchal Shah
 *  Semester : Summer 2023
 * This class tests all the public methods of the Rook class.
 */
public class TestQueen {
  private Queen whiteQueenStart;
  private Queen whiteQueenEdge;
  private Queen blackQueenKill;
  private Queen blackQueen4;

  @Before
  public void setUp() {
    whiteQueenStart = new Queen(0, 0, Color.WHITE);
    whiteQueenEdge = new Queen(6, 2, Color.WHITE);
    blackQueenKill = new Queen(2, 2, Color.BLACK);
    blackQueen4 = new Queen(4, 2, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidQueen() {
    // try to instantiate a Queen with invalid row and column
    Queen invalidQueen = new Queen(9, 9, Color.WHITE);
    // negative row and column
    Queen invalidQueen2 = new Queen(-1, -1, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(0, whiteQueenStart.getRow());
    assertEquals(6, whiteQueenEdge.getRow());
    assertEquals(2, blackQueenKill.getRow());
    assertEquals(4, blackQueen4.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(0, whiteQueenStart.getColumn());
    assertEquals(2, whiteQueenEdge.getColumn());
    assertEquals(2, blackQueenKill.getColumn());
    assertEquals(2, blackQueen4.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, whiteQueenStart.getColor());
    assertEquals(Color.WHITE, whiteQueenEdge.getColor());
    assertEquals(Color.BLACK, blackQueenKill.getColor());
    assertEquals(Color.BLACK, blackQueen4.getColor());
  }

  @Test
  public void testCanMove() {
    // 1. move vertically - up
    assertTrue(whiteQueenStart.canMove(3, 0));
    assertTrue(blackQueenKill.canMove(0, 2));

    // 2. move vertically - down
    assertTrue(whiteQueenEdge.canMove(0, 2));
    assertTrue(blackQueenKill.canMove(4, 2));

    // 3. move horizontally - right
    assertTrue(blackQueenKill.canMove(2, 7));
    assertTrue(whiteQueenStart.canMove(0, 7));
    // 3.1 move horizontally - right - out of bounds
    assertFalse(whiteQueenStart.canMove(0, 8));

    // 4. move horizontally - left
    assertTrue(whiteQueenEdge.canMove(0, 2));
    assertTrue(blackQueenKill.canMove(2, 0));
    // 4.1 move horizontally - left - out of bounds
    assertFalse(whiteQueenStart.canMove(0, -1));


    // 5. move diagonally - up right
    assertTrue(whiteQueenEdge.canMove(7, 3));
    // 5.1 move diagonally - down right
    assertTrue(blackQueen4.canMove(3,3));
    // 5.2 move diagonally - down right - out of bounds
    assertFalse(blackQueen4.canMove(3, 8));


    // 6. move diagonally - up left
    assertTrue(whiteQueenStart.canMove(3, 3));
    // 6.2 move diagonally - down left
    assertTrue(blackQueen4.canMove(3, 1));
    // 6.3 move diagonally - down left - out of bounds
    assertFalse(blackQueen4.canMove(3, -1));
  }

  @Test
  public void testCanKill() {
    // 1. kill vertically - up
    assertTrue(whiteQueenStart.canKill(blackQueenKill));
    assertTrue(blackQueenKill.canKill(whiteQueenStart));

    // 2. kill vertically - down
    assertTrue(whiteQueenEdge.canKill(blackQueenKill));
    assertTrue(blackQueenKill.canKill(whiteQueenEdge));

    // 3. kill horizontally - right
    assertTrue(blackQueenKill.canKill(whiteQueenStart));
    assertTrue(whiteQueenStart.canKill(blackQueenKill));

    // 4. kill horizontally - left
    assertTrue(whiteQueenEdge.canKill(blackQueenKill));
    assertTrue(blackQueenKill.canKill(whiteQueenEdge));

    // 5. kill diagonally - up right
    assertTrue(whiteQueenEdge.canKill(blackQueenKill));
    assertTrue(blackQueenKill.canKill(whiteQueenEdge));

    // 6. kill diagonally - up left
    assertTrue(whiteQueenStart.canKill(blackQueenKill));
    assertTrue(blackQueenKill.canKill(whiteQueenStart));

    // 7. same color
    assertFalse(whiteQueenStart.canKill(whiteQueenEdge));
    assertFalse(whiteQueenStart.canKill(whiteQueenStart));

    // 8. not in the same row, column or diagonal
    assertFalse(whiteQueenStart.canKill(blackQueen4));
    assertFalse(whiteQueenStart.canKill(blackQueen4));
  }
}
