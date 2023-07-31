package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class represents the view for the program.
 * NOTE: This class is an ActionListener, why?
 *      So that it can access all the widgets data and process it.
 */
public class ViewLecture extends JFrame implements ActionListener, KeyListener {
  private JButton saveButton;
  private JButton loadButton;
  private final JTextField enterText;
  private final JLabel showText;


  public ViewLecture() {
    super();
    // 1. Set the basic window properties
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setLayout(new BorderLayout());

    // 2. Initialize the widget
    this.enterText = new JTextField("Enter commands here");
    this.saveButton = new JButton("Save");
    this.loadButton = new JButton("Load");
    this.showText = new JLabel("Display Text");

    // 3. Add the widgets to the window
    this.add(saveButton, BorderLayout.EAST);
    this.add(loadButton, BorderLayout.WEST);
    this.add(enterText, BorderLayout.CENTER);
    this.add(showText, BorderLayout.SOUTH);

    // 4. Add the action listener to indentify which button was clicked
    this.saveButton.setActionCommand("save");
    this.loadButton.setActionCommand("load");

    this.saveButton.addActionListener(this);
    this.loadButton.addActionListener(this);
  }

  /**
   * This method is called when the user clicks on a button.
   * It processes the event and performs the appropriate action.
   *
   * @param e the event to be processed, set by the setActionCommand.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    // which button was clicked?
    switch (e.getActionCommand()) {
      case "save":
        // generate a custom event and pass it to the controller.

        break;
      case "load":
        showText.setText("Clicked Load");
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + e.getActionCommand());
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // FIXME : do not hard code these, let the user set these.
    if (e.getKeyChar() == 'l') {

    }

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
