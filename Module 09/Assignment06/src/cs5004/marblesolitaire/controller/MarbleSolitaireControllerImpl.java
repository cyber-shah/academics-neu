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

  // OLD CODE --------------------

//  enum checkValues { QUIT, BAD_INPUT, GOOD_INPUT }
//
//  private String[] resetMove(String[] move) {
//    move[0] = "-1";
//    move[1] = "-1";
//    move[2] = "-1";
//    move[3] = "-1";
//    return move;
//  }
//
//  /**
//   * Play a new game of Marble Solitaire using the model.
//   * Plays the game until it is over or the user quits.
//   * Uses Readable to get input from the user.
//   *
//   * @throws IllegalStateException if it encounters issues with input or output.
//   */
//  @Override
//  public void playGame() throws IllegalStateException {
//    Scanner scanner = new Scanner(readableInput);
//    int moveNumber = 0;
//
//    // print the board before making any move
//    this.transmitBoard(moveNumber);
//    String[] move = new String[4];
//
//    while (!model.isGameOver() && scanner.hasNextLine()) {
//
//      String input = scanner.nextLine();
//      // for input types = "3\n1\n3\n3\n5\n2\n3\n2"
//      if (input.matches("\\d+")) {
//        move[0] = input;
//        for (int i = 1; i < 4; i++) {
//          try {
//            input = scanner.nextLine();
//          }
//          catch (Exception e) {
//            continue;
//          }
//          move[i] = input;
//        }
//      }
//      else if (input.equalsIgnoreCase("q")) {
//        try {
//          view.renderMessage("\nGame Quit!");
//        }
//        catch (Exception e) {
//          e.printStackTrace();
//          throw new IllegalStateException("Error rendering the message, game quit");
//        }
//        return;
//      }
//
//      // for input types = "3 1 3 3\n5 2 3 2" and "3 1 3 3 5 2 3 2"
//      else if (input.length() > 3) {
//        move = input.split(" ");
//      }
//
//      // check if the input is valid
//      checkValues valid = checkValues(move);
//
//      // if the input is quit, return
//      if (valid == checkValues.QUIT) {
//        try {
//          view.renderMessage("\nGame Quit!");
//        }
//        catch (Exception e) {
//          e.printStackTrace();
//          throw new IllegalStateException("Error rendering the message, game quit");
//        }
//        return;
//      }
//
//      // if the input is invalid, continue
//      else if (valid == checkValues.BAD_INPUT) {
//        try {
//          view.renderMessage("\nBad Input. Play again. " + move + " is not a valid input");
//        }
//        catch (Exception e) {
//          e.printStackTrace();
//          throw new IllegalStateException("Error rendering the message, bad input");
//        }
//        continue;
//      }
//
//      // if the input is valid, make the move
//      else {
//        model.move(Integer.parseInt(move[0])  - 1, Integer.parseInt(move[1]) - 1,
//                Integer.parseInt(move[2]) - 1, Integer.parseInt(move[3]) - 1);
//        moveNumber++;
//        move = resetMove(move);
//      }
//
//      // after move is made
//      this.transmitBoard(moveNumber);
//    }
//
//    // if the game is over
//    if (model.isGameOver()) {
//      try {
//        view.renderMessage("\nGame over!");
//        view.renderMessage("\nScore: " + Integer.toString(model.getScore()));
//      }
//      catch (Exception e) {
//        e.printStackTrace();
//        throw new IllegalStateException("Error rendering the message, game over");
//      }
//      return;
//    }
//  }
//  /**
//   * This method checks the set of values to see if they are valid.
//   *
//   * @param move of the type String[]
//   * @return checkValues of the type enum
//   */
//  private checkValues checkValues(String[] move) {
//    // if the input is bad
//    if (isBadInput(move)) {
//      try {
//        view.renderMessage("\nBad Input. Play again. " + move + " is not a valid input");
//      }
//      catch (Exception e) {
//        e.printStackTrace();
//        throw new IllegalStateException("Error rendering the message, invalid move");
//      }
//      return checkValues.BAD_INPUT;
//    }
//
//    // if the user wants to quit
//    if (isQPresent(move)) {
//      try {
//        view.renderMessage("\nGame quit!");
//        view.renderMessage("\nState of game when quit:");
//        view.renderBoard();
//        view.renderMessage("\nScore: " + Integer.toString(model.getScore()));
//      } catch (Exception e) {
//        e.printStackTrace();
//        throw new IllegalStateException("Error rendering the message, game quit");
//      }
//      return checkValues.QUIT;
//    }
//
//    // if it passes all checks
//    return checkValues.GOOD_INPUT;
//  }


  // NEW CODE --------------------
//  /**
//   * Checks if the input is quit.
//   *
//   * @param values the input to check.
//   * @return true if the input is quit, false otherwise.
//   */
//  private boolean isQPresent(String[] values) {
//    for (int i = 0; i < values.length; i++) {
//      if (values[i].equalsIgnoreCase("q")) {
//        return true;
//      }
//    }
//    return false;
//  }

  /**
   * Play a new game of Marble Solitaire using the model.
   * Plays the game until it is over or the user quits.
   * Uses Readable to get input from the user.
   *
   * @throws IllegalStateException if it encounters issues with input or output.
   */
  @Override
  public void playGame() throws IllegalStateException {

    // create array and scanner
    String[] moves = new String[4];
    Scanner scanner = new Scanner(this.readableInput);

    while (!model.isGameOver() && scanner.hasNext()) {
      // 1. transmit the current state of game to the view
      // 2. transmit the score to the view
      this.transmitBoard();

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
            view.renderMessage("Game quit!\n");
            view.renderMessage("State of game when quit:\n");
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

      // 9. move on to the next and continue to the next iteration
      moves = new String[4];
    }

    // 9. check if game over
    if (model.isGameOver()) {
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

  /*
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
    }*/

}