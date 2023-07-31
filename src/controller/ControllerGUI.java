package controller;

import controller.commandmanager.CommandsManagerInterface;
import controller.commandsstrategy.io.LoadCommandStrategy;
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
    LoadCommandStrategy loadCommandStrategy = new LoadCommandStrategy();
    String[] args = new String[3];
    args[0] = event.getEventName();
    args[1] = event.getFilePath();
    args[2] = event.getDestID();
    loadCommandStrategy.run(args, imageDatabase);
//    view.updateImageDatabase(imageDatabase);
//    view.showMessage("Image loaded successfully!");
//    view.updateImage
  }

  @Override
  public void runProgram() {

  }

}
