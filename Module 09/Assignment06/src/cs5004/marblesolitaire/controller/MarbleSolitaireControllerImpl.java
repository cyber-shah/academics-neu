package cs5004.marblesolitaire.controller;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireView;

import java.util.Scanner;

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
    private final MarbleSolitaireModel model;
    private final MarbleSolitaireView view;
    private final Readable readableInput;

    MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                  MarbleSolitaireView view, Readable readableInput) {
        if (model == null || view == null || readableInput == null) {
            throw new IllegalArgumentException("Model, view, and readable cannot be null");
        }
        this.model = model;
        this.view = view;
        this.readableInput = readableInput;}


    /**
     * Play a new game of Marble Solitaire using the model.
     * Plays the game until it is over or the user quits.
     * Uses Readable to get input from the user.
     *
     * @throws IllegalStateException if it encounters issues with input or output.
     */
    @Override
    public void playGame() throws IllegalStateException {
        Scanner scanner = new Scanner(readableInput);
        while (model.isGameOver() != true) {
            view.renderBoard();
            view.renderMessage(Integer.toString(model.getScore()));
            scanner.p
        }

    }
}