import view.ViewLecture;

import javax.swing.*;

public class IMEGui {

  /**
   * This method is the main method for the program.
   * @param args command line arguments.
   */
  public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    ViewLecture view = new ViewLecture();
    view.setVisible(true);
  }
}
