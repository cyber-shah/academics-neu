import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  Student Name : Pranchal Shah
 *  Semester : Summer 2023
 * This class tests all the public methods of the Bishop class.
 */


public class TestBishop {
  private Bishop whiteBishopStart;
  private Bishop whiteBishopEdge;
  private Bishop blackBishopKill;
  private Bishop blackBishop4;

  @Before
  public void setUp() {
    whiteBishopStart = new Bishop(1, 1, Color.WHITE);
    whiteBishopEdge = new Bishop(6, 2, Color.WHITE);
    blackBishopKill = new Bishop(2, 2, Color.BLACK);
    blackBishop4 = new Bishop(4, 2, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBishop() {
    // try to instantiate a Bishop with invalid row and column
    Bishop invalidBishop = new Bishop(9, 9, Color.WHITE);
    // negative row and column
    Bishop invalidBishop2 = new Bishop(-1, -1, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(1, whiteBishopStart.getRow());
    assertEquals(6, whiteBishopEdge.getRow());
    assertEquals(2, blackBishopKill.getRow());
    assertEquals(4, blackBishop4.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(1, whiteBishopStart.getColumn());
    assertEquals(2, whiteBishopEdge.getColumn());
    assertEquals(2, blackBishopKill.getColumn());
    assertEquals(2, blackBishop4.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, whiteBishopStart.getColor());
    assertEquals(Color.WHITE, whiteBishopEdge.getColor());
    assertEquals(Color.BLACK, blackBishopKill.getColor());
    assertEquals(Color.BLACK, blackBishop4.getColor());
  }

  @Test
  public void testCanMove() {
    // 1. move forward - right
    assertTrue(whiteBishopStart.canMove(2, 2));
    assertTrue(whiteBishopStart.canMove(7, 7));

    // 1.1 move forward - left
    assertTrue(blackBishopKill.canMove(3, 1));

    // 2. move backward
    assertTrue(whiteBishopEdge.canMove(5, 1));

    // 3. move out of bounds
    assertFalse(whiteBishopStart.canMove(8, 8));
    assertFalse(whiteBishopStart.canMove(-1, -1));

    // 4. move at the same position
    assertFalse(blackBishop4.canMove(4, 2));

    // 5. move non diagonally
    assertFalse(blackBishop4.canMove(4, 4));
    assertFalse(blackBishop4.canMove(2, 2));

  }

  @Test
  public void testCanKill() {
    // 1. Kill a bishop
    assertTrue(whiteBishopStart.canKill(blackBishopKill));

    // 2. same color
    Bishop tempBishop = new Bishop(2, 2, Color.WHITE);
    assertFalse(whiteBishopStart.canKill(tempBishop));

    // 3. Kill not possible
    assertFalse(whiteBishopStart.canKill(blackBishop4));

    // 4. Kill other chess piece
    Pawn pawn = new Pawn(2, 2, Color.BLACK);
    assertTrue(whiteBishopStart.canKill(pawn));
  }
}