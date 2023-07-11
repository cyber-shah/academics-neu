import cs5004.marblesolitaire.controller.MarbleSolitaireController;
import cs5004.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs5004.marblesolitaire.model.hw05.AbstractRectangularModel;
import cs5004.marblesolitaire.model.hw05.EnglishSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireTextView;
import cs5004.marblesolitaire.view.MarbleSolitaireView;
import org.junit.Test;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the MarbleSolitaireControllerImpl class.
 */
public class TestMarbleSolitaireControllerImpl {

  private MarbleSolitaireController controller;
  private AbstractRectangularModel model;
  private MarbleSolitaireView view;
  private StringBuilder log;

  @Test
  public void testPlayingGameSpaceAndNewLine() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, logView);
    // Test a file with tokens seperated by space and moves by line
    Readable test = new StringReader("3 1 3 3\n5 2 3 2");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("move (3, 1) -> (3, 3)\n"
            + "move (5, 2) -> (3, 2)\n", log.toString());
  }

  @Test
  public void testPlayGameNewLines() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, logView);

    // Test a file with tokens seperated by line
    Readable test = new StringReader("3\n1\n3\n3\n5\n2\n3\n2");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("move (3, 1) -> (3, 3)\n"
            + "move (5, 2) -> (3, 2)\n", log.toString());
  }

  @Test
  public void testPlayGameOnlySpaces() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, logView);

    // Test a file with tokens seperated by line
    Readable test = new StringReader("3 1 3 3 5 2 3 2 5 2 3 2");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("move (3, 1) -> (3, 3)\n"
            + "move (5, 2) -> (3, 2)\n"
            + "move (5, 2) -> (3, 2)\n", log.toString());
  }

  @Test
  public void testPlayGameIncompleteMove() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, logView);

    // Test a file with tokens seperated by line
    Readable test = new StringReader("3 1 3 3 5 2 3 2 5 2 3");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("move (3, 1) -> (3, 3)\n"
            + "move (5, 2) -> (3, 2)\n", log.toString());
  }

  @Test
  public void testPlayGameEmptyInput() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, logView);

    // Test a file with tokens seperated by line
    Readable test = new StringReader("");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("", log.toString());
  }

  @Test
  public void testPlayGameBadInputs() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, logView);

    // Test a file with tokens seperated by line
    Readable test = new StringReader("3 1 3 r kk u v 3 5 2 3 2 hh g c 5 2 3 2 5 2 3 2");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("move (3, 1) -> (3, 3)\n"
            + "move (5, 2) -> (3, 2)\n"
            + "move (5, 2) -> (3, 2)\n"
            + "move (5, 2) -> (3, 2)\n", log.toString());
  }

  @Test
  public void testQuitGameMiddle() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, logView);

    Readable test = new StringReader("3\n1\n3\n3\n5\n2\n3\n2\nq\n1\n2");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Assert the quit message
    String[] expected = logView.toString().split("\\n");
    boolean isQuitMessageFound = false;
    for (String line : expected) {
      if (line.contains("Game quit!")) {
        isQuitMessageFound = true;
        break;
      }
    }
    assertTrue(isQuitMessageFound);
  }

  @Test
  public void testQuitGameFirst() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, logView);

    Readable test = new StringReader("q\n3\n1\n3\n3\n5\n2\n3\n2\n1\n2");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Assert the quit message
    String[] expected = logView.toString().split("\\n");
    boolean isQuitMessageFound = false;
    for (String line : expected) {
      if (line.contains("Game quit!")) {
        isQuitMessageFound = true;
        break;
      }
    }
    assertTrue(isQuitMessageFound);
  }

  @Test
  public void testRealModel() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model, logView);

    Readable test = new StringReader("4\n2\n4\n4\n6\n3\n4\n3");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("\n    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O" +
            "\nScore: 32\n\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O" +
            "\nScore: 31\n\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ O O O O O\n" +
            "O O _ O O O O\n" +
            "    _ O O\n" +
            "    O O O" +
            "\nScore: 30\n", logView.toString());

  }

  @Test
  public void testRealModelQuit() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model, logView);

    Readable test = new StringReader("2\n4\n4\n4\nq");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("\n    O O O\n"
              + "    O O O\n"
              + "O O O O O O O\n"
              + "O O O _ O O O\n"
              + "O O O O O O O\n"
              + "    O O O\n"
              + "    O O O"
              + "\nScore: 32\n\n"
              + "    O O O\n"
              + "    O _ O\n"
              + "O O O _ O O O\n"
              + "O O O O O O O\n"
              + "O O O O O O O\n"
              + "    O O O\n"
              + "    O O O"
              + "\nScore: 31\n\n"
              + "Game quit!\n"
              + "State of game when quit:\n"
              + "    O O O\n"
              + "    O _ O\n"
              + "O O O _ O O O\n"
              + "O O O O O O O\n"
              + "O O O O O O O\n"
              + "    O O O\n"
              + "    O O O"
              + "\nScore: 31\n", logView.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = null;
    view = new MarbleSolitaireTextView(model, logView);

    Readable test = new StringReader("4\n2\n4\n4\n6\n3\n4\n3");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new EnglishSolitaireModel();
    view = null;

    Readable test = new StringReader("4\n2\n4\n4\n6\n3\n4\n3");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new EnglishSolitaireModel();
    view = new MarbleSolitaireTextView(model, logView);

    Readable test = null;
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
