import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  Student Name : Pranchal Shah
 *  Semester : Summer 2023
 * This class tests all the public methods of the Knight class.
 */
public class TestKnight {
  private Knight whiteKnightStart;
  private Knight whiteKnightEdge;
  private Knight blackKnightKill;
  private Knight blackKnight4;

  @Before
  public void setUp() {
    whiteKnightStart = new Knight(0, 1, Color.WHITE);
    whiteKnightEdge = new Knight(6, 2, Color.WHITE);
    blackKnightKill = new Knight(2, 2, Color.BLACK);
    blackKnight4 = new Knight(4, 2, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidKnight() {
    // try to instantiate a Knight with invalid row and column
    Knight invalidKnight = new Knight(9, 9, Color.WHITE);
    // negative row and column
    Knight invalidKnight2 = new Knight(-1, -1, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(0, whiteKnightStart.getRow());
    assertEquals(6, whiteKnightEdge.getRow());
    assertEquals(2, blackKnightKill.getRow());
    assertEquals(4, blackKnight4.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(1, whiteKnightStart.getColumn());
    assertEquals(2, whiteKnightEdge.getColumn());
    assertEquals(2, blackKnightKill.getColumn());
    assertEquals(2, blackKnight4.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, whiteKnightStart.getColor());
    assertEquals(Color.WHITE, whiteKnightEdge.getColor());
    assertEquals(Color.BLACK, blackKnightKill.getColor());
    assertEquals(Color.BLACK, blackKnight4.getColor());
  }

  @Test
  public void testCanMove() {
    // 1. up right
    assertTrue(whiteKnightStart.canMove(1, 3));
    assertTrue(blackKnight4.canMove(3, 4));

    // 2. up left
    assertTrue(blackKnightKill.canMove(0, 3));

    // 3. down right
    assertTrue(whiteKnightStart.canMove(1, 3));
    assertTrue(blackKnight4.canMove(2, 3));

    // 4. down left
    assertTrue(whiteKnightEdge.canMove(5, 0));
    assertTrue(blackKnightKill.canMove(3, 4));

    // 5. invalid move
    assertFalse(whiteKnightStart.canMove(1, 2));
    assertFalse(blackKnight4.canMove(4, 4));

    // 6. move to same position
    assertFalse(whiteKnightStart.canMove(0, 1));
    assertFalse(blackKnight4.canMove(4, 2));

    // 7. move out of board
    assertFalse(whiteKnightStart.canMove(8, 8));
    assertFalse(blackKnight4.canMove(-1, -1));
  }

  @Test
  public void testCanKill() {
    // 1. up right
    assertTrue(whiteKnightStart.canKill(blackKnightKill));

    // 2. up left
    Pawn tempPawn = new Pawn(0, 3, Color.WHITE);
    assertTrue(blackKnightKill.canKill(tempPawn));

    // 3. down right
    Queen tempQueen = new Queen(4, 3, Color.BLACK);
    assertTrue(whiteKnightEdge.canKill(tempQueen));

    // 4. down left
    Rook tempRook = new Rook(4, 1, Color.BLACK);
    assertTrue(whiteKnightEdge.canKill(tempRook));

    // 5. invalid kill
    assertFalse(blackKnightKill.canKill(whiteKnightEdge));

    // 6. kill same color
    assertFalse(whiteKnightStart.canKill(whiteKnightEdge));
  }
}
