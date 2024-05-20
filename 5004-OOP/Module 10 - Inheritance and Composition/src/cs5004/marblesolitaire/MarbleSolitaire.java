package cs5004.marblesolitaire;

import cs5004.marblesolitaire.controller.MarbleSolitaireController;
import cs5004.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs5004.marblesolitaire.model.hw05.EnglishSolitaireModel;
import cs5004.marblesolitaire.model.hw05.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.hw07.EuropeanSolitaireModel;
import cs5004.marblesolitaire.model.hw07.TriangleSolitaireModel;
import cs5004.marblesolitaire.view.MarbleSolitaireTextView;
import cs5004.marblesolitaire.view.MarbleSolitaireView;
import cs5004.marblesolitaire.view.TriangleSolitaireTextView;

import java.io.InputStreamReader;

/**
 * This class represents the main class for the Marble Solitaire game.
 * It parses command-line arguments and creates the appropriate model and view.
 */
public class MarbleSolitaire {

  /**
   * Print the usage instructions for the game.
   * The user must provide the name of the board shape (english, european, or triangular).
   * The user may also provide the size of the board and the position of the hole.
   *
   * @param args the command-line arguments.
   */
  public static void main(String[] args) {

    // Create and configure the appropriate model and view based on the board shape
    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    MarbleSolitaireController controller;

    // Parse command-line arguments
    if (args.length < 1) {
      printUsageInstructions();
      System.exit(1);
    }

    String boardName = args[0];
    int size = -1;
    int holeRow = -1;
    int holeColumn = -1;

    for (int i = 1; i < args.length; i++) {
      if (args[i].equals("-size")) {
        if (i + 1 < args.length) {
          size = Integer.parseInt(args[i + 1]);
          i++;
        } else {
          System.out.println("Invalid command. Please provide a board size.");
          System.exit(1);
        }
      } else if (args[i].equals("-hole")) {
        if (i + 2 < args.length) {
          holeRow = Integer.parseInt(args[i + 1]);
          holeColumn = Integer.parseInt(args[i + 2]);
          i += 2;
        } else {
          System.out.println("Invalid command. Please provide hole position (row and column).");
          System.exit(1);
        }
      }
    }


    switch (boardName) {
      case "english":
        // default values
        if (holeRow == -1 || holeColumn == -1) {
          holeRow = 3;
          holeColumn = 3;
        }
        if (size == -1) {
          size = 3;
        }
        model = new EnglishSolitaireModel(size, holeRow, holeColumn);
        view = new MarbleSolitaireTextView(model);
        // Create and configure the controller
        controller = new
                MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));

        // Start the game
        controller.playGame();
        break;


      case "european":
        // default values
        if (holeRow == -1 || holeColumn == -1) {
          holeRow = 3;
          holeColumn = 3;
        }
        if (size == -1) {
          size = 3;
        }
        model = new EuropeanSolitaireModel(size, holeRow, holeColumn);
        view = new MarbleSolitaireTextView(model);
        // Create and configure the controller
        controller = new
                MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));

        // Start the game
        controller.playGame();
        break;


      case "triangle":
        // default values
        if (holeRow == -1 || holeColumn == -1) {
          holeRow = 0;
          holeColumn = 0;
        }
        if (size == -1) {
          size = 5;
        }
        model = new TriangleSolitaireModel(size, holeRow, holeColumn);
        view = new TriangleSolitaireTextView(model);
        // Create and configure the controller
        controller = new
                MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));

        // Start the game
        controller.playGame();
        break;

      default:
        System.out.println("Invalid board shape: " + boardName);
        System.exit(1);
    }
  }


  /**
   * Print the usage instructions for the game.
   */
  private static void printUsageInstructions() {
    System.out.println("Usage: java MarbleSolitaire <TYPE> <SIZE> <HOLE>");
    System.out.println(" TYPE: english");
    System.out.println("       european");
    System.out.println("       triangle");
    System.out.println(" SIZE: the size of the board");
    System.out.println("       (optional)");
    System.out.println(" HOLE: the initial hole position");
    System.out.println("       (optional)");
  }
}
