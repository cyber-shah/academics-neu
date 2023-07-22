import controller.ControllerImplementation;
import model.ImageDatabase;
import model.ImageDatabaseInterface;
import model.operations.OperationInterface;
import view.ViewImplementation;

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
    ImageDatabaseInterface model = new ImageDatabase();
    ViewImplementation view = new ViewImplementation(System.out);
    Readable in = new InputStreamReader(System.in);
    ControllerImplementation controller = new ControllerImplementation(model, view, in);
    OperationInterface operations;
    controller.runProgram();
  }
}
