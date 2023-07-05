import cs5004.marblesolitaire.controller.MarbleSolitaireController;
import cs5004.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs5004.marblesolitaire.model.hw05.EnglishSolitaireModel;
import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireTextView;
import cs5004.marblesolitaire.view.MarbleSolitaireView;
import org.junit.Test;
import org.junit.Before;

import java.io.File;
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
    private final StringBuilder log = new StringBuilder();

    @Before
    public void setUp() {
        model = new MockSolitaireModel(this.log);
        view = new MarbleSolitaireTextView(model, System.out);
        try {
            FileReader fileReader = new FileReader("moves.txt");
            controller = new MarbleSolitaireControllerImpl(model, view, fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPlayingGame() {
        // Test a file with tokens seperated by space and moves by line
        Readable test1 = new StringReader("3 1 3 3\n5 2 3 2");
        controller = new MarbleSolitaireControllerImpl(model, view, test1);
        try {
            controller.playGame();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("move (3, 1) -> (3, 3)\n" +
                "move (5, 2) -> (3, 2)\n", log.toString());

        // Test a file with tokens and moves seperated by line
        Readable test2 = new StringReader("3\n1\n3\n3\n5\n2\n3\n2");
        controller = new MarbleSolitaireControllerImpl(model, view, test2);
        try {
            controller.playGame();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("move (3, 1) -> (3, 3)\n" +
                "move (5, 2) -> (3, 2)\n", log.toString());
    }



}
