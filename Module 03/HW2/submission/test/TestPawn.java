import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *  Student Name : Pranchal Shah
 *  Semester : Summer 2023
 * This class tests all the public methods of the Pawn class.
 */

public class TestPawn {
  private Pawn whitePawnStart;
  private Pawn whitePawnEdge;
  private Pawn blackPawnKill;
  private Pawn blackPawn4;

  @Before
  public void setUp() {
    whitePawnStart = new Pawn(1, 1, Color.WHITE);
    whitePawnEdge = new Pawn(6, 2, Color.WHITE);
    blackPawnKill = new Pawn(2, 2, Color.BLACK);
    blackPawn4 = new Pawn(4, 2, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPawn() {
    // try to instantiate a Pawn with invalid row and column
    Pawn invalidPawn = new Pawn(9, 9, Color.WHITE);
    // negative row and column
    Pawn invalidPawn2 = new Pawn(-1, -1, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(1, whitePawnStart.getRow());
    assertEquals(6, whitePawnEdge.getRow());
    assertEquals(2, blackPawnKill.getRow());
    assertEquals(4, blackPawn4.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(1, whitePawnStart.getColumn());
    assertEquals(2, whitePawnEdge.getColumn());
    assertEquals(2, blackPawnKill.getColumn());
    assertEquals(2, blackPawn4.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, whitePawnStart.getColor());
    assertEquals(Color.WHITE, whitePawnEdge.getColor());
    assertEquals(Color.BLACK, blackPawnKill.getColor());
    assertEquals(Color.BLACK, blackPawn4.getColor());
  }

  @Test
  public void testCanMove() {
    // PAWN AT STARTING POSITION
    // 1. one step + 1
    assertTrue(whitePawnStart.canMove(2, 1));
    // 2. two steps + 2
    assertFalse(whitePawnStart.canMove(3, 1));
    // 3. kill - when no piece is there
    assertFalse(whitePawnStart.canMove(2,2));

    //PAWN IN THE MIDDLE
    // 4. move 1 step forward
    assertTrue(blackPawn4.canMove(3,2));
    // 5. two steps - 2 FALSE
    assertFalse(blackPawn4.canMove(2,1));
    // 6. check if it can move horizontal
    assertFalse(blackPawn4.canMove(4,2));

    // 7. kill when no peice is there - False
    assertFalse(blackPawn4.canMove(3,3));

    //PAWN AT THE EDGE
    // 8. move 1 step forward
    assertFalse(whitePawnEdge.canMove(8,2));
    // 9. move 1 step behind
    assertFalse(whitePawnEdge.canMove(6,2));

    // 10. move at the same position
    assertFalse(whitePawnEdge.canMove(6,2));
    
  }

  @Test
  public void testCanKill() {
    // 1. can it kill the opposite color diagonal?
    assertTrue(whitePawnStart.canKill(blackPawnKill));
    // 2. can it kill the same color diagonal?
    Pawn whitePawnTemp = new Pawn(2, 2, Color.WHITE);
    assertFalse(whitePawnStart.canKill(whitePawnTemp));
    // 3. kill something far
    assertFalse(whitePawnStart.canKill(blackPawn4));
    // 4. kill something behind
    Pawn blackPawnTemp = new Pawn(0, 2, Color.BLACK);
    assertFalse(whitePawnStart.canKill(blackPawnTemp));
  }
}