import controller.ControllerGUI;
import model.ImageDatabase;
import model.ImageDatabaseInterface;
import view.gui.GrimeView;

/**
 * This class is the main class for the program.
 * It creates the model, view, and controller objects and runs the program.
 */
public class GrimeGUI {

  /**
   * This method is the main method for the program.
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    ImageDatabaseInterface imageDatabase = new ImageDatabase();
    GrimeView view = new GrimeView();
    ControllerGUI controller = new ControllerGUI(imageDatabase, view);
    controller.runProgram();
    view.setVisible(true);
  }
}
