import org.junit.Before;
import org.junit.Test;

import cs5004.marblesolitaire.model.hw05.EnglishSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestEnglishSolitaireModel {
  private final EnglishSolitaireModel S1 = new EnglishSolitaireModel();
  private final MarbleSolitaireTextView V1 = new MarbleSolitaireTextView(S1);

  private final EnglishSolitaireModel S2 = new EnglishSolitaireModel(5,3);
  private final MarbleSolitaireTextView V2 = new MarbleSolitaireTextView(S2);

  private final EnglishSolitaireModel S3 = new EnglishSolitaireModel(5, 4, 4);
  private final MarbleSolitaireTextView V3 = new MarbleSolitaireTextView(S3);

  @Test
  public void testToString() {
    assertEquals("""
                        O O O
                        O O O
                    O O O O O O O
                    O O O _ O O O
                    O O O O O O O
                        O O O
                        O O O""",
            V1.toString());

    assertEquals("""
                        O O O
                        O O O
                    O O O O O O O
                    O O O O O O O
                    O O O O O O O
                        O _ O
                        O O O""",
            V2.toString());

    assertEquals("""
                            O O O O O
                            O O O O O
                            O O O O O
                            O O O O O
                    O O O O _ O O O O O O O O
                    O O O O O O O O O O O O O
                    O O O O O O O O O O O O O
                    O O O O O O O O O O O O O
                    O O O O O O O O O O O O O
                            O O O O O
                            O O O O O
                            O O O O O
                            O O O O O""",
            V3.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    // invalid empty tile
    EnglishSolitaireModel S1 = new EnglishSolitaireModel(5, 5);

    // out of bounds empty tile
    EnglishSolitaireModel S2 = new EnglishSolitaireModel(7,7);

    // negative empty tile
    EnglishSolitaireModel S3 = new EnglishSolitaireModel(-1, 3);

//    // invalid constructor - negative arm thickness
//    EnglishSolitaireModel S3 = new EnglishSolitaireModel(-1);
//
//    // invalid constructor - even arm thickness
//    EnglishSolitaireModel S4 = new EnglishSolitaireModel(4);
//
//    // invalid constructor - negative empty cell position
//    EnglishSolitaireModel S5 = new EnglishSolitaireModel(3, -1, 3);
//
//    // invalid constructor - empty cell position outside board
//    EnglishSolitaireModel S6 = new EnglishSolitaireModel(3, 6, 3);
//
//    // invalid constructor - empty cell position inside invalid tile
//    EnglishSolitaireModel S7 = new EnglishSolitaireModel(3, 0, 3);
  }

  @Test
  public void testMove() {
    // valid move
    S1.move(3, 1, 3, 3);
    assertEquals("    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O",
            V1.toString());

  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveInvalid() {
    // invalid move - no marble at origin
    S1.move(3, 1, 3, 4);

    // invalid move - not empty at destination
    S1.move(3, 1, 3, 3);

    // invalid move - not in same row or column
    S1.move(3, 1, 4, 3);

    // invalid move - not 2 spaces away
    S1.move(3, 4, 3, 1);

    // invalid move - negative
    S1.move(-1, 4, 3, 1);

    // invalid move - inside invalid tile
    S1.move(3, 0, 3, 3);

    // invalid move - outside board
    S1.move(3, 6, 3, 8);

    // invalid move - diagonal
    S1.move(1, 3, 3, 1);
  }
}
