package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the view for the program.
 * NOTE: This class is an ActionListener, why?
 *      So that it can access all the widgets data and process it.
 * ==================================================================
 * NOTE : the physicality of how the events are generated via button clicks, keyboards, mouse,
 *        is all handled in the view. The controller doesn't care how the events are generated.
 */
public class ViewLecture extends JFrame implements ActionListener, KeyListener {

  private JButton saveButton;
  private JButton loadButton;
  private final JLabel showText;
  private final List<CustomEventsListener> listeners;
  private final Canvas canvas;


  /**
   * Constructor for the view.
   * It initializes the window and widgets.
   * It also adds the widgets to the window and adds the action listener to the widgets.
   */
  public ViewLecture() {
    super();
    setTitle("");
    // 0. Initialize fields
    this.listeners = new ArrayList<>();
    this.canvas = new Canvas();
    BorderLayout toolbar = new BorderLayout();
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 1. Set the basic window properties
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setLayout(new BorderLayout());

    // 2. Initialize the widget
    this.saveButton = new JButton("Save");
    this.loadButton = new JButton("Load");
    this.showText = new JLabel("Display Text");

    // 3. Add the widgets to the window
    add(this.saveButton, BorderLayout.WEST);
    add(this.loadButton, BorderLayout.EAST);
    add(this.showText, BorderLayout.SOUTH);
    add(this.canvas, BorderLayout.CENTER);

    // 4. Add the action listener to indentify which button was clicked
    this.saveButton.setActionCommand("save");
    this.loadButton.setActionCommand("load");
    // TODO: when you click the load button, a fileDIALOG will open up
    //       select the PPM file, emit the load event give that file to the controller.
    //       handleLoadEvent(String Path)

    // TODO: basically translate what the command line did into a GUI.

    // TODO: then the last part is giving the image to the view so it can render it.
    //       similar to setViewText you will have SetImage(Image image).
    //       use g.drawImage to draw the image on the canvas.
    // view gets the image -> canvas -> canvas renders it

    // FIXME : set the focus to the window always so we can set shortcuts.
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
        fireSaveEvent();
        break;
      case "load":
        fireLoadEvent();
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + e.getActionCommand());
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // FIXME : do not hard code these, let the user set these.
    if (e.getKeyChar() == 'l') {
      fireLoadEvent();
    }
    else if (e.getKeyChar() == 's') {
      fireSaveEvent();
    }

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }




  /**
   * This method adds a listener to the list of listeners.
   * Must implement the CustomEvents interface.
   * NOTE : The view doesn't know there is a controller.
   *        It just knows that there is a listener, which can handle the event.
   *
   * @param listener the listener to be added.
   */
  public void addCustomEventListener(CustomEventsListener listener) {
    this.listeners.add(listener);
  }

  private void fireLoadEvent() {
    for (CustomEventsListener listener : this.listeners) {
      listener.handleLoadEvent();
    }
  }

  private void fireSaveEvent() {
    for (CustomEventsListener listener : this.listeners) {
      listener.handleSaveEvent();
    }
  }
}
