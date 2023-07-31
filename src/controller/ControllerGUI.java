package controller;

import controller.commandmanager.CommandsManagerInterface;
import model.ImageDatabaseInterface;
import view.gui.CustomEvent;
import view.gui.GrimeView;

import java.util.Objects;

public class ControllerGUI implements ControllerGUIInterface {
  private final ImageDatabaseInterface imageDatabase;
  private final GrimeView view;


  public ControllerGUI(ImageDatabaseInterface imageDatabase, GrimeView view) {
    this.imageDatabase = Objects.requireNonNull(imageDatabase);
    this.view = Objects.requireNonNull(view);
    // NOTE : Subscribe to the view's custom events.
    view.addEventsListener(this);
  }

  @Override
  public void handleEvent(CustomEvent event) {
    String filePath = event.getFilePath();
    String eventName = event.getEventName();
  }

  @Override
  public void runProgram() {

  }

}
