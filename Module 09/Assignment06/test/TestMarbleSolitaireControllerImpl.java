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
    public void testPlayingGame() {
        // A file with tokens seperated by space and moves by line
        String test1 = "3 1 3 3\n5 2 3 2";
        try {
            controller = new MarbleSolitaireControllerImpl(model, view, test1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            controller.playGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
