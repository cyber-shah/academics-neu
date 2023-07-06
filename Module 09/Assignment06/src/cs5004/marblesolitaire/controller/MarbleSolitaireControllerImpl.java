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

  enum checkValues { QUIT, BAD_INPUT, GOOD_INPUT }

  private String[] resetMove(String[] move) {
    move[0] = "-1";
    move[1] = "-1";
    move[2] = "-1";
    move[3] = "-1";
    return move;
  }




  public void playGame1() throws IllegalStateException {
    // 1. game over to false
    boolean gameOver = false;

    while (!gameOver) {
      // 1. transmit the current state of game to the view
      // 2. transmit the score to the view
      this.printBoard();

      // 3. scanner.next = token
      Scanner scanner = new Scanner(this.readableInput);

    }




        // 4. check if token is valid

            // 4.1 check if token is quit

        // 5. if valid add it to the move array

        // 6. if move array is full, make the move

        // 7. if model.move throws an error, print the error
        // 8. move on to the next and continue to the next iteration

        // 9. check if game over
          // 9.1 if game is over, break out of the loop
          // 9.2 transmit the current state of game to the view
          // 9.3 transmit the score to the view
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
    Scanner scanner = new Scanner(readableInput);
    int moveNumber = 0;

    // print the board before making any move
    this.printBoard(moveNumber);
    String[] move = new String[4];

    while (!model.isGameOver() && scanner.hasNextLine()) {

      String input = scanner.nextLine();
      // for input types = "3\n1\n3\n3\n5\n2\n3\n2"
      if (input.matches("\\d+")) {
        move[0] = input;
        for (int i = 1; i < 4; i++) {
          try {
            input = scanner.nextLine();
          }
          catch (Exception e) {
            continue;
          }
          move[i] = input;
        }
      }
      else if (input.equalsIgnoreCase("q")) {
        try {
          view.renderMessage("\nGame Quit!");
        }
        catch (Exception e) {
          e.printStackTrace();
          throw new IllegalStateException("Error rendering the message, game quit");
        }
        return;
      }

      // for input types = "3 1 3 3\n5 2 3 2" and "3 1 3 3 5 2 3 2"
      else if (input.length() > 3) {
        move = input.split(" ");
      }

      // check if the input is valid
      checkValues valid = checkValues(move);

      // if the input is quit, return
      if (valid == checkValues.QUIT) {
        try {
          view.renderMessage("\nGame Quit!");
        }
        catch (Exception e) {
          e.printStackTrace();
          throw new IllegalStateException("Error rendering the message, game quit");
        }
        return;
      }

      // if the input is invalid, continue
      else if (valid == checkValues.BAD_INPUT) {
        try {
          view.renderMessage("\nBad Input. Play again. " + move + " is not a valid input");
        }
        catch (Exception e) {
          e.printStackTrace();
          throw new IllegalStateException("Error rendering the message, bad input");
        }
        continue;
      }

      // if the input is valid, make the move
      else {
        model.move(Integer.parseInt(move[0])  - 1, Integer.parseInt(move[1]) - 1,
                Integer.parseInt(move[2]) - 1, Integer.parseInt(move[3]) - 1);
        moveNumber++;
        move = resetMove(move);
      }

      // after move is made
      this.printBoard(moveNumber);
    }

    // if the game is over
    if (model.isGameOver()) {
      try {
        view.renderMessage("\nGame over!");
        view.renderMessage("\nScore: " + Integer.toString(model.getScore()));
      }
      catch (Exception e) {
        e.printStackTrace();
        throw new IllegalStateException("Error rendering the message, game over");
      }
      return;
    }
  }

  /**
   * This method checks the set of values to see if they are valid.
   *
   * @param move of the type String[]
   * @return checkValues of the type enum
   */
  private checkValues checkValues(String[] move) {
    // if the input is bad
    if (isBadInput(move)) {
      try {
        view.renderMessage("\nBad Input. Play again. " + move + " is not a valid input");
      }
      catch (Exception e) {
        e.printStackTrace();
        throw new IllegalStateException("Error rendering the message, invalid move");
      }
      return checkValues.BAD_INPUT;
    }

    // if the user wants to quit
    if (isQPresent(move)) {
      try {
        view.renderMessage("\nGame quit!");
        view.renderMessage("\nState of game when quit:");
        view.renderBoard();
        view.renderMessage("\nScore: " + Integer.toString(model.getScore()));
      } catch (Exception e) {
        e.printStackTrace();
        throw new IllegalStateException("Error rendering the message, game quit");
      }
      return checkValues.QUIT;
    }

    // if it passes all checks
    return checkValues.GOOD_INPUT;
  }

  /**
   * This method calls other methods in the view to print the board and the score.
   * @param lineNumber of the type int
   */
  private void printBoard(int lineNumber) {
    try {
      // 1. render the board
      view.renderBoard();
      // 2. render the score
      view.renderMessage("\nScore after move " + lineNumber + ": "
              + Integer.toString(model.getScore()) + "\n");
    }
    catch (Exception e) {
      e.printStackTrace();
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

  /**
   * Checks if the input is quit.
   *
   * @param values the input to check.
   * @return true if the input is quit, false otherwise.
   */
  private boolean isQPresent(String[] values) {
    for (int i = 0; i < values.length; i++) {
      if (values[i].equalsIgnoreCase("q")) {
        return true;
      }
    }
    return false;
  }
}