import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;
import cs5004.marblesolitaire.model.hw07.AbstractModel;
import cs5004.marblesolitaire.model.hw07.EuropeanSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireTextView;
import cs5004.marblesolitaire.view.MarbleSolitaireView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the EuropeanSolitaireModel class.
 */
public class TestEuropeanSolitaireModel {
  private AbstractModel model;
  private MarbleSolitaireView view;

  @Before
  public void setUp() {
    model = new EuropeanSolitaireModel();
    view = new MarbleSolitaireTextView(model);
  }

  // CONSTRUCTOR TESTS ==============================================
  @Test
  public void testDefaultConstructor() {
    // Test board size
    assertEquals(7, model.getBoardSize());

    // Test center slot is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(3, 3));
  }

  @Test
  public void testConstructorWithPosition() {
    model = new EuropeanSolitaireModel(2, 4);

    // Test board size
    assertEquals(7, model.getBoardSize());

    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(2, 4));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithPosition() {
    model = new EuropeanSolitaireModel(0, 0);

    // Test board size
    assertEquals(7, model.getBoardSize());

    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(0, 0));
  }

  @Test
  public void testConstructorWithArmThicknessOnly() {
    model = new EuropeanSolitaireModel(3);
    // Test board size
    assertEquals(7, model.getBoardSize());
    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(3, 3));
    try {
      model.move(0, 2, 3, 3);
    }
    catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
  }

  @Test
  public void testConstructorWithArmThicknessAndPosition() {
    model = new EuropeanSolitaireModel(5, 1, 5);
    // Test board size
    assertEquals(13, model.getBoardSize());
    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(1, 5));
  }

  @Test
  public void testConstructorWithArmThicknessAndPosition2() {
    model = new EuropeanSolitaireModel(3, 4, 4);
    // Test board size
    assertEquals(7, model.getBoardSize());
    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(4, 4));
  }

  @Test
  public void testValidConstructorWithArmThickness() {
    model = new EuropeanSolitaireModel(5, 1, 3);
    // Test board size
    assertEquals(13, model.getBoardSize());
    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(1, 3));
  }

  @Test
  public void testConstructorWithArmThicknessOnly2() {
    model = new EuropeanSolitaireModel(5);

    // Test board size
    assertEquals(13, model.getBoardSize());

    // Test center slot is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(6, 6));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithArmThicknessOnly() {
    model = new EuropeanSolitaireModel(6);
    // Test board size
    assertEquals(13, model.getBoardSize());
    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(7, 7));
  }

  // MOVE TESTS ========================================================

  @Test
  public void testMoveValid() {
    model.move(1, 3, 3, 3);

    // Test moved marble
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(1, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(3, 3));

    // Test middle slot is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(2, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidOutOfBounds() {
    model.move(0, 0, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidNotTwoSpacesAway() {
    model.move(1, 3, 3, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidFromSlotEmpty() {
    model.move(3, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidToSlotNotEmpty() {
    model.move(1, 3, 3, 3);
    model.move(3, 1, 3, 3);
  }

  {
    model = new EuropeanSolitaireModel(2, 4);

    // Test board size
    assertEquals(7, model.getBoardSize());

    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(2, 4));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveInvalidNoMarbleInBetween() {
    model.move(1, 3, 3, 3);
    // invalid move
    model.move(0, 3, 2, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveNotSameRowNorSameColumn() {
    model.move(0, 2, 3, 3);
  }



  @Test
  public void testIsGameOverFalse() {
    assertFalse(model.isGameOver());
  }


  @Test
  public void testGetBoardSize() {
    assertEquals(7, model.getBoardSize());
  }

  @Test
  public void testGetSlotAtValid() {
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(3, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalid() {
    model.getSlotAt(-1, 0);
  }

  @Test
  public void testGetScore() {
    assertEquals(36, model.getScore());
    model.move(3, 1, 3, 3);
    assertEquals(35, model.getScore());
  }

  @Test
  public void testDefaultToString() {
    assertEquals(view.toString(), "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O");

    model.move(3, 1, 3, 3);
    assertEquals(view.toString(), "    O O O\n"
            + "  O O O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O");
  }

  @Test
  public void test5ToString() {
    model = new EuropeanSolitaireModel(5);
    view = new MarbleSolitaireTextView(model);
    assertEquals(view.toString(), "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", view.toString());

    model.move(6, 4, 6, 6);
    assertEquals(view.toString(), "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O _ _ O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O", view.toString());
  }


}
