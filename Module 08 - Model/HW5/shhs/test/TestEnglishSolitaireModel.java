import org.junit.Before;
import org.junit.Test;

import cs5004.marblesolitaire.model.hw05.EnglishSolitaireModel;
import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;
import cs5004.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * This class tests the EnglishSolitaireModel class.
 */
public class TestEnglishSolitaireModel {

  private EnglishSolitaireModel model;
  private MarbleSolitaireTextView view;

  @Before
  public void setUp() {
    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model);
  }

  @Test
  public void testDefaultConstructor() {
    final MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);

    // Test board size
    assertEquals(7, model.getBoardSize());

    // Test center slot is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(3, 3));
  }

  @Test
  public void testConstructorWithPosition() {
    model = new EnglishSolitaireModel(2, 4);

    // Test board size
    assertEquals(7, model.getBoardSize());

    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(2, 4));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithPosition() {
    model = new EnglishSolitaireModel(0, 0);

    // Test board size
    assertEquals(7, model.getBoardSize());

    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(0, 0));
  }

  @Test
  public void testConstructorWithArmThicknessAndPosition() {
    model = new EnglishSolitaireModel(5, 1, 5);

    // Test board size
    assertEquals(13, model.getBoardSize());

    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(1, 5));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidConstructorWithArmThickness() {
    model = new EnglishSolitaireModel(5, 1, 3);

    // Test board size
    assertEquals(13, model.getBoardSize());

    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(1, 3));
  }

  @Test
  public void testConstructorWithBoardSize() {
    model = new EnglishSolitaireModel(9);

    // Test board size
    assertEquals(9, model.getBoardSize());

    // Test center slot is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(4, 4));
  }

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
    model = new EnglishSolitaireModel(2, 4);

    // Test board size
    assertEquals(7, model.getBoardSize());

    // Test specified position is empty
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(2, 4));
  }
  @Test (expected = IllegalArgumentException.class)
  public void testMoveInvalidNoMarbleInBetween() {
    EnglishSolitaireModel model1 = new EnglishSolitaireModel(2, 4);
    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(model1);

    model.move(1, 3, 3, 3);
    // invalid move
    model.move(0, 3, 2, 3);
  }

  @Test
  public void testIsGameOverFalse() {
    assertFalse(model.isGameOver());
  }

  @Test
  public void testIsGameOverTrue() {
    EnglishSolitaireModel model2 = new EnglishSolitaireModel();

    model2.move(3, 1, 3, 3);
    model2.move(3, 0, 3, 2);
    model2.move(4, 2, 2, 2);
    model2.move(2, 0, 2, 2);
    model2.move(2, 5, 2, 3);
    model2.move(2, 2, 2, 4);
    model2.move(3, 3, 1, 3);
    model2.move(3, 1, 3, 3);
    model2.move(3, 3, 3, 1);
    model2.move(2, 3, 2, 1);
    model2.move(2, 1, 2, 3);
    model2.move(2, 0, 2, 2);
    model2.move(2, 2, 2, 4);
    model2.move(2, 4, 2, 2);
    model2.move(2, 2, 4, 2);
    model2.move(3, 4, 3, 2);
    model2.move(3, 2, 3, 4);
    model2.move(4, 3, 2, 3);
    model2.move(2, 3, 2, 1);
    model2.move(0, 3, 2, 3);

    assertEquals(true, model2.isGameOver());
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
    assertEquals(32, model.getScore());
    model.move(3, 1, 3, 3);
    assertEquals(31, model.getScore());
  }

  @Test
  public void testToString() {
    model.move(3, 1, 3, 3);
    assertEquals(view.toString(), "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O");

    EnglishSolitaireModel model1 = new EnglishSolitaireModel(2, 4);
    MarbleSolitaireTextView view1 = new MarbleSolitaireTextView(model1);
    assertEquals(view1.toString(),
              "    O O O\n" +
                    "    O O O\n" +
                    "O O O O _ O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O");

    EnglishSolitaireModel model2 = new EnglishSolitaireModel(13);
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(model2);
    assertEquals(view2.toString(),
                "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O _ O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O");

    EnglishSolitaireModel model3 = new EnglishSolitaireModel(5, 4, 4);
    MarbleSolitaireTextView view3 = new MarbleSolitaireTextView(model3);
    assertEquals(view3.toString(),
                      "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "O O O O _ O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O");

  }


}
