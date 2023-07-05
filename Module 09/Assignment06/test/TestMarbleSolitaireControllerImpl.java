import cs5004.marblesolitaire.controller.MarbleSolitaireController;
import cs5004.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireTextView;
import cs5004.marblesolitaire.view.MarbleSolitaireView;
import org.junit.Test;
import org.junit.Before;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the MarbleSolitaireControllerImpl class.
 */
public class TestMarbleSolitaireControllerImpl {

  private MarbleSolitaireController controller;
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private StringBuilder log;

/*  @Before
  public void setUp() {
    log = new StringBuilder();
    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, System.out);
    try {
      FileReader fileReader = new FileReader("moves.txt");
      controller = new MarbleSolitaireControllerImpl(model, view, fileReader);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }*/

  @Test
  public void testPlayingGameSpace() {
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
    }
    catch (Exception e) {
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
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("move (3, 1) -> (3, 3)\n"
            + "move (5, 2) -> (3, 2)\n", log.toString());
  }

  @Test
  public void testQuitGame() {
    // initialize the logs
    log = new StringBuilder();
    StringBuilder logView = new StringBuilder();

    model = new MockSolitaireModel(this.log);
    view = new MarbleSolitaireTextView(model, logView);

    Readable test = new StringReader("3\n1\n3\n3\n5\n2\n3\n2\nq\n1\n2");
    controller = new MarbleSolitaireControllerImpl(model, view, test);
    try {
      controller.playGame();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    // Remove empty spaces
    assertEquals("move (3, 1) -> (3, 3)\n"
            + "move (5, 2) -> (3, 2)\n", log.toString());

    // Check last line of logView
    String[] lines = logView.toString().split("\\n");
    String lastLine = lines[lines.length - 1].trim();
    assertEquals("Game Quit!", lastLine);
  }
}
