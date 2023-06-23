import org.junit.Test;

import cs5004.marblesolitaire.model.hw05.EnglishSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;

public class TestEnglishSolitaireModel {
  private final EnglishSolitaireModel s1 = new EnglishSolitaireModel();
  private final MarbleSolitaireTextView v1 = new MarbleSolitaireTextView(s1);

  private final EnglishSolitaireModel s2 = new EnglishSolitaireModel(5,3);
  private final MarbleSolitaireTextView v2 = new MarbleSolitaireTextView(s2);

  private final EnglishSolitaireModel s3 = new EnglishSolitaireModel(5, 4, 4);
  private final MarbleSolitaireTextView v3 = new MarbleSolitaireTextView(s3);

  private final EnglishSolitaireModel s4 = new EnglishSolitaireModel(13);
  private final MarbleSolitaireTextView v4 = new MarbleSolitaireTextView(s4);

  @Test
  public void testToString() {
    assertEquals("    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O",
            v1.toString());

    assertEquals("    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "    O _ O\n"
                    + "    O O O",
            v2.toString());

    assertEquals("        O O O O O\n"
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
                    + "        O O O O O",
            v3.toString());

    assertEquals("        O O O O O\n"
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
                    + "        O O O O O",
            v4.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    // invalid empty tile
    EnglishSolitaireModel S1 = new EnglishSolitaireModel(5, 5);

    // out of bounds empty tile
    EnglishSolitaireModel S2 = new EnglishSolitaireModel(7,7);

    // negative empty tile
    EnglishSolitaireModel S3 = new EnglishSolitaireModel(-1, 3);

    // invalid constructor - negative arm thickness
    EnglishSolitaireModel S6 = new EnglishSolitaireModel(-1);

    // invalid constructor - even arm thickness
    EnglishSolitaireModel S7 = new EnglishSolitaireModel(4);

    // invalid constructor - negative empty cell position
    EnglishSolitaireModel S8 = new EnglishSolitaireModel(3, -1, 3);

    // invalid constructor - empty cell position outside board
    EnglishSolitaireModel S9 = new EnglishSolitaireModel(3, 6, 3);

    // invalid constructor - empty cell position inside invalid tile
    EnglishSolitaireModel S10 = new EnglishSolitaireModel(3, 0, 3);
  }

  @Test
  public void testMove() {
    // valid move
    s1.move(3, 1, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O",
            v1.toString());

    s1.move(3,0,3,3);

  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveInvalid() {

    // invalid move - no marble at origin
    s1.move(3, 1, 3, 4);

    // invalid move - not empty at destination
    s1.move(3, 1, 3, 3);

    // invalid move - not in same row or column
    s1.move(3, 1, 4, 3);

    // invalid move - not 2 spaces away
    s1.move(3, 4, 3, 1);

    // invalid move - negative
    s1.move(-1, 4, 3, 1);

    // invalid move - inside invalid tile
    s1.move(3, 0, 3, 3);

    // invalid move - outside board
    s1.move(3, 6, 3, 8);

    // invalid move - diagonal
    s1.move(1, 3, 3, 1);
  }

  @Test
  public void testGetScore() {
    assertEquals(32, s1.getScore());
    assertEquals(104, s4.getScore());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(7, s1.getBoardSize());
    assertEquals(7, s2.getBoardSize());
    assertEquals(13, s3.getBoardSize());
    assertEquals(13, s4.getBoardSize());
  }
}
