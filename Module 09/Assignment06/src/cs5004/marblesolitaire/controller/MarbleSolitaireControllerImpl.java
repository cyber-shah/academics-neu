package cs5004.marblesolitaire.controller;

import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireView;

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
   * @param model of the type MarbleSolitaireModel
   * @param view of the type MarbleSolitaireView
   * @param readableInput of the type Readable
   * @throws IllegalArgumentException if the model, view, or readableInput is null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view, Readable readableInput) throws IllegalArgumentException {
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
    // create a scanner
    Scanner scanner = new Scanner(readableInput);
    int lineNumber = 0;
    // print the board before making any move
    try {
      // 1. render the board
      view.renderBoard();
      // 2. render the score
      view.renderMessage("\nScore at the Beginning: " + Integer.toString(model.getScore()) + "\n");
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new IllegalStateException("Error rendering the board or score");
    }
    // while scanner has next Line
    while (scanner.hasNextLine()) {
      // get the next Line
      String Line = scanner.nextLine();
      // values in the Line
      String[] values = Line.split(" ");
      if (isBadInput(values)) {
        try {
          view.renderMessage("\nBad Input. Play again. " + values + " is not a valid input");
        }
        catch (Exception e) {
          e.printStackTrace();
          throw new IllegalStateException("Error rendering the message, invalid move");
        }
        continue;
      }
      model.move(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
              Integer.parseInt(values[2]), Integer.parseInt(values[3]));
      // render the updated board and score after the move
      lineNumber++;
      try {
        // 1. render the board
        view.renderBoard();
        // 2. render the score
        view.renderMessage("\nScore after move " + lineNumber + ": " + Integer.toString(model.getScore()) + "\n");
      }
      catch (Exception e) {
        e.printStackTrace();
        throw new IllegalStateException("Error rendering the board or score");
      }
    }
  }

  /**
   * Checks if the input is bad.
   *
   * @param input the input to check.
   * @return true if the input is bad, false otherwise.
   */
  private boolean isBadInput(String[] input) {
    // scan all tokens in the line
    for (int i = 0; i < input.length; i++) {
      // if token is a NUMBER
      try {
        int token = Integer.parseInt(input[i]);
        if (token < 0 || token > this.model.getBoardSize()) {
          return true;
        }
      }
      // if token is a STRING
      catch (NumberFormatException e) {
        String token = input[i];
        if (!token.equals("q")) {
          return true;
        }
      }
    }
    return false;
  }

}