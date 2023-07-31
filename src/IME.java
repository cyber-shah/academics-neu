import commandmanager.CommandsManager;
import commandmanager.CommandsManagerInterface;
import controller.ControllerImplementation;
import model.ImageDatabase;
import model.ImageDatabaseInterface;
import view.ViewImplementation;
import view.ViewLecture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * This class is the main class for the program.
 * It creates the model, view, and controller objects and starts the program.
 */
public class IME {

  /**
   * This method is the main method for the program.
   * @param args command line arguments.
   */
  public static void main(String[] args) {

    String filePath = null;

    // If the user provides a file path, use it ------------------
    // 1. retrieve the file path
    // 2. set the input to file reader
    if (args.length > 1 && args[0].equals("-file")) {

      filePath = args[1];

      // If no file path is provided, display an error message and terminate
      if (filePath == null) {
        System.err.println("Please provide a file path using: "
                + "-file command-line argument.");
      }

      try {
        // 1. Create readable object from file path
        BufferedReader inReadable = new BufferedReader(new FileReader(filePath));

        // 2. set up the model, view, and controller
        ImageDatabaseInterface model = new ImageDatabase();
        ViewImplementation view = new ViewImplementation(System.out);
        CommandsManagerInterface commandsManager = new CommandsManager();

        // 3. use the file as readable input
        ControllerImplementation controller = new ControllerImplementation(model, view,
                inReadable, commandsManager);
        controller.runProgram();
        inReadable.close();
      }
      catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
      }
    }

    // if the user does not provide a file,
    // use the command line interface--------------------------------
    else {
      ImageDatabaseInterface model = new ImageDatabase();
      ViewImplementation view = new ViewImplementation(System.out);
      Readable inReadable = new InputStreamReader(System.in);


      CommandsManagerInterface commandsManager = new CommandsManager();
      ControllerImplementation controller = new ControllerImplementation(model, view,
              inReadable, commandsManager);
      controller.runProgram();
    }

    ViewLecture viewLecture = new ViewLecture();
  }
}
