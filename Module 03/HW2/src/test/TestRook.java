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

public class TestRook {

  private Rook whiteRookStart;
  private Rook whiteRookEdge;
  private Rook blackRookKill;
  private Rook blackRook4;

  @Before
  public void setUp() {
    whiteRookStart = new Rook(0, 0, Color.WHITE);
    whiteRookEdge = new Rook(6, 2, Color.WHITE);
    blackRookKill = new Rook(2, 2, Color.BLACK);
    blackRook4 = new Rook(4, 2, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRook() {
    // try to instantiate a Rook with invalid row and column
    Rook invalidRook = new Rook(9, 9, Color.WHITE);
    // negative row and column
    Rook invalidRook2 = new Rook(-1, -1, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(0, whiteRookStart.getRow());
    assertEquals(6, whiteRookEdge.getRow());
    assertEquals(2, blackRookKill.getRow());
    assertEquals(4, blackRook4.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(0, whiteRookStart.getColumn());
    assertEquals(2, whiteRookEdge.getColumn());
    assertEquals(2, blackRookKill.getColumn());
    assertEquals(2, blackRook4.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, whiteRookStart.getColor());
    assertEquals(Color.WHITE, whiteRookEdge.getColor());
    assertEquals(Color.BLACK, blackRookKill.getColor());
    assertEquals(Color.BLACK, blackRook4.getColor());
  }

  @Test
  public void testCanMove() {
    // 1. vertical - forward
    assertTrue(whiteRookStart.canMove(7, 0));
    assertTrue(blackRookKill.canMove(7,2));

    // 2. vertical - backward
    assertTrue(whiteRookEdge.canMove(6,0));
    assertTrue(blackRook4.canMove(0,2));

    // 3. horizontal - right
    assertTrue(blackRookKill.canMove(2,7));
    assertTrue(whiteRookStart.canMove(0, 7));


    // 4. horizontal - left
    assertTrue(blackRook4.canMove(4,0));
    assertTrue(blackRook4.canMove(4,7));


    // 5. invalid move - diagonal
    assertFalse(whiteRookStart.canMove(1,1));
    assertFalse(blackRookKill.canMove(1,1));

    // 6. invalid move - L shape
    assertFalse(whiteRookStart.canMove(2,1));
    assertFalse(blackRookKill.canMove(5,1));

    // 7. outside limits
    assertFalse(whiteRookStart.canMove(8,8));
    assertFalse(blackRookKill.canMove(10,10));

    // 8. negative row and column
    assertFalse(whiteRookStart.canMove(-1,-1));
  }

  @Test
  public void testCanKill() {
    // 1. same color - False
    assertFalse(whiteRookStart.canKill(whiteRookEdge));
    assertFalse(blackRookKill.canKill(blackRook4));

    // 2. opposite peice - True
    assertTrue(whiteRookEdge.canKill(blackRookKill));
    assertTrue(blackRook4.canKill(whiteRookEdge));

    // 3. invalid move - False
    assertFalse(whiteRookStart.canKill(whiteRookStart));
    assertFalse(blackRookKill.canKill(blackRookKill));
  }
}