import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModelState;
import cs5004.marblesolitaire.view.AbstractTextView;
import cs5004.marblesolitaire.view.TriangleSolitaireTextView;
import org.junit.Before;
import cs5004.marblesolitaire.model.hw07.TriangleSolitaireModel;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestTriangleSolitaireModel {
  private MarbleSolitaireModel model;
  private AbstractTextView view;

  @Before
  public void setUp() {
    model = new TriangleSolitaireModel();
  }

  @Test
  public void testConstructorDefault() {
    model = new TriangleSolitaireModel();

    assertEquals(5, model.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(2,0));
  }

/*
  @Test
  public void testConstructorArmThickness() {
    model = new TriangleSolitaireModel(6);

    assertEquals(6, model.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(3,3));
  }

  @Test
  public void testConstructorWithRowColumn() {
    model = new TriangleSolitaireModel(7, 0, 0);

    assertEquals(7, model.getBoardSize());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, model.getSlotAt(0,0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, model.getSlotAt(3,3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalid() {
    model = new TriangleSolitaireModel(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRowColumnInvalid() {
    model = new TriangleSolitaireModel(5, 0, 5);
  }
*/


  // MOVE TESTS ==================================================
  @Test
  public void testMoves() {
    // vertical move - up
    model.move(2, 0, 0, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(0, 0));

    // horizontal move - left
    model.move(2, 2, 2, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(2, 2));

    // diagonal move - up left
    model.move(4,3,2,1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(4, 3));

    // diagonal move - down right
    model.move(0,0,2,2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(0, 0));

    // horizontal move - right
    model.move(4,1,4,3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(4, 1));

    // vertical move - up
    model.move(3,0,1,0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(3, 0));


    // assert game is NOT OVER
    assertFalse(model.isGameOver());

    // vertical move - down
    model.move(2,1,4,1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(2, 1));

    // horizontal move - right
    model.move(4,0,4,2);

    model.move(3,3,1,1);

    model.move(4,3,4,1);

    // check if game is over
    assertTrue(model.isGameOver());

    // check score
    assertEquals(4, model.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalid() {
    model.move(0,0,0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNoMarbleInBetween() {
    model.move(2,0,0,0);
    model.move(0,0,2,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNoMarbleAtStart() {
    model.move(2,0,0,0);
    model.move(2,0,0,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveNotEmptyAtEnd() {
    model.move(2,2,0,0);
    model.move(4,0,2,0);
  }


  @Test
  public void testToStringDefault() {
    view = new TriangleSolitaireTextView(model);
    assertEquals(
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", view.toString());

    model.move(2, 0, 0, 0);
    assertEquals(
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", view.toString());

    model.move(2, 2, 2, 0);
    assertEquals(
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O", view.toString());
  }

  @Test
  public void testToStringSize7() {
    model = new TriangleSolitaireModel(7);
    view = new TriangleSolitaireTextView(model);

    assertEquals(
            "      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", view.toString());
  }





}
