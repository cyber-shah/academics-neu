package cs5004.marblesolitaire.controller;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireView;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents the controller for the Marble Solitaire game.
 * It implements the MarbleSolitaireController interface.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable readableInput;

  /**
   * This is the constructor for the MarbleSolitaireControllerImpl.
   *
   * @param model         of the type MarbleSolitaireModel
   * @param view          of the type MarbleSolitaireView
   * @param readableInput of the type Readable
   * @throws IllegalArgumentException if the model, view, or readableInput is null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view,
                                       Readable readableInput) throws IllegalArgumentException {
    if (model == null || view == null || readableInput == null) {
      throw new IllegalArgumentException("Model, view, and readable cannot be null");
    }
    this.model = model;
    this.view = view;
    this.readableInput = readableInput;
  }

  /**
   * Play a new game of Marble Solitaire using the model.
   * Plays the game until it is over or the user quits.
   * Uses Readable to get input from the user.
   *
   * @throws IllegalStateException if it encounters issues with input or output.
   */
  @Override
  public void playGame() throws IllegalStateException {

    this.transmitBoard();

    // create array and scanner
    String[] moves = new String[4];
    Scanner scanner = new Scanner(this.readableInput);

    while (!model.isGameOver() && scanner.hasNext()) {
      // 1. transmit the current state of game to the view
      // 2. transmit the score to the view

      // run the loop 4 times until the moves array is full
      for (int i = 0; i < moves.length; i++) {

        // 3. scanner.next = token
        String token;
        try {
          token = scanner.next();
        }
        catch (NoSuchElementException e) {
          throw new IllegalStateException("No more inputs found");
        }

        // 4. while token is invalid, continue to update token.
        while (this.isBadInput(token)) {
          token = scanner.next();
        }

        // 5. check if token is quit
        if (token.equalsIgnoreCase("q")) {
          try {
            view.renderMessage("\nGame quit!\nState of game when quit:");
          } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Error rendering the message, game quit");
          }
          this.transmitBoard();
          // 5.1 if quit, break out of the loop
          return;
        }

        // 6. once token is validated add it to the moves array
        else {
          moves[i] = token;
        }
      }

      // ONCE WE HAVE ALL FOUR MOVE TOKENS -----------------
      // 7. try to make the move
      try {
        int fromRow = Integer.parseInt(moves[0]) - 1;
        int fromCol = Integer.parseInt(moves[1]) - 1;
        int toRow = Integer.parseInt(moves[2]) - 1;
        int toCol = Integer.parseInt(moves[3]) - 1;

        model.move(fromRow, fromCol, toRow, toCol);
      }

      // 8. if the input is bad, print the error
      catch (NumberFormatException e) {
        try {
          view.renderMessage("Invalid move. Play again.");
        }
        catch (Exception e2) {
          e2.printStackTrace();
          throw new IllegalStateException("Error rendering the message, invalid move");
        }
        continue;
      }

      // 8. if model.move throws an error, print the error
      catch (IllegalArgumentException e) {
        try {
          view.renderMessage(e.getMessage());
        }
        catch (Exception e2) {
          e2.printStackTrace();
          throw new IllegalStateException("Error rendering the message, invalid move");
        }
        continue;
      }
      this.transmitBoard();
      // 9. move on to the next and continue to the next iteration
      moves = new String[4];
    }

    // 9. when readable runs out of inputs
    if (!model.isGameOver() && !scanner.hasNext()) {
      this.transmitBoard();
      throw new IllegalStateException("No more inputs found");
    }

    // 9. check if game over
    else if (model.isGameOver()) {
      // 9.1 if game is over, print the game over message
      try {
        view.renderMessage("Game over!");
      }
      catch (Exception e) {
        e.printStackTrace();
        throw new IllegalStateException("Error rendering the message, game over");
      }

      // 9.2 transmit the current state of game to the view
      // 9.3 transmit the score to the view
      this.transmitBoard();
      return;
    }

    // otherwise show the end status of the game
    else {
      this.transmitBoard();
    }

  }

  /**
   * This method calls other methods in the view to print the board and the score.
   *
   * @throws IllegalStateException if it encounters issues with input or output.
   */
  private void transmitBoard() throws IllegalStateException {
    try {
      // 1. render the board
      view.renderBoard();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error rendering the board");
    }
    try {
      // 2. render the score
      view.renderMessage("\nScore: "
              + Integer.toString(model.getScore()) + "\n");
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error rendering the score");
    }

  }

  /**
   * Checks if the input is bad.
   *
   * @param token the input to check.
   * @return true if the input is bad, false otherwise.
   */
  private boolean isBadInput(String token) {
    // if value is a NUMBER
    try {
      int value = Integer.parseInt(token);
      if (value < 0 || value > this.model.getBoardSize()) {
        return true;
      }
    }
    // if value is a STRING
    catch (NumberFormatException e) {
      String value = token;
      if (!value.equals("q")) {
        return true;
      }
    }
    return false;
  }

}