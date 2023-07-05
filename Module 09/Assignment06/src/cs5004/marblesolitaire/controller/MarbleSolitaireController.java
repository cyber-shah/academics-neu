package cs5004.marblesolitaire.controller;

public interface MarbleSolitaireController {

    /**
     * Play a new game of Marble Solitaire using the model.
     * Plays the game until it is over or the user quits.
     * Uses Readable to get input from the user.
     *
     * @throws IllegalStateException if it encounters issues with input or output.
     */
    void playGame() throws IllegalStateException;
}
