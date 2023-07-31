package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ViewLecture extends JFrame{
  private JButton saveButton;
  private JButton loadButton;
  private final JTextField enterText;
  private final JLabel showText;
  private final ViewActionListener actionListener = new ViewActionListener();


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

    this.saveButton.addActionListener(this.actionListener);
    this.loadButton.addActionListener(this.actionListener);
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
        showText.setText("Clicked Save");
        break;
      case "load":
        showText.setText("Clicked Load");
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + e.getActionCommand());
    }
  }
}
