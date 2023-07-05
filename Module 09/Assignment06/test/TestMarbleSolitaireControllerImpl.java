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


public class TestMarbleSolitaireControllerImpl {

    private MarbleSolitaireController controller;
    private MarbleSolitaireModel model;
    private MarbleSolitaireView view;

    @Before
    public void setUp() {
        model = new EnglishSolitaireModel();
        view = new MarbleSolitaireTextView(model, System.out);
        try {
            FileReader fileReader = new FileReader("moves.txt");
            controller = new MarbleSolitaireControllerImpl(model, view, fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPlayGame() {
        try {
            controller.playGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
