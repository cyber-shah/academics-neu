package cs5004.marblesolitaire.controller;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireView;

import java.util.Scanner;

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
    private final MarbleSolitaireModel model;
    private final MarbleSolitaireView view;
    private final Readable rd;
    private final Scanner scn;

    MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                  MarbleSolitaireView view, Readable rd) {
        if (model == null || view == null || rd == null) {
            throw new IllegalArgumentException("Model, view, and readable cannot be null");
        }
        this.model = model;
        this.view = view;
        this.rd = rd;
        this.scn = new Scanner(rd);
    }


    @Override
    public void playGame() throws IllegalStateException {
        while (model.isGameOver() != true) {
            view.renderBoard();
            view.renderMessage(Integer.toString(model.getScore()));
            rd
        }

    }
}