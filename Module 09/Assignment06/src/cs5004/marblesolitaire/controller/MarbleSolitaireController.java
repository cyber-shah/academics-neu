package cs5004.marblesolitaire.controller;

public interface MarbleSolitaireController {
    /**
     * Play a new game of Marble Solitaire using the provided model.
     *
     * @throws IllegalStateException if it encounters issues with input or output.
     */
    void playGame() throws IllegalStateException;
}
