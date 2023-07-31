import view.GrimeView;
import view.ViewLecture;

import javax.swing.*;
import javax.swing.text.View;

public class IMEGui {

  /**
   * This method is the main method for the program.
   * @param args command line arguments.
   */
  public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    GrimeView view = new GrimeView();
    view.setVisible(true);
  }
}
