package controller;

import model.ImageDatabaseInterface;
import view.ViewLecture;

import java.util.Objects;

public class ControllerGUI implements ControllerInterface {
  private final ImageDatabaseInterface imageDatabase;
  private final ViewLecture view;


  public ControllerGUI(ImageDatabaseInterface imageDatabase, ViewLecture view) {
    this.imageDatabase = Objects.requireNonNull(imageDatabase);
    this.view = Objects.requireNonNull(view);
    // NOTE : Subscribe to the view's custom events.
    view.addCustomEventListener(this);
  }

  @Override
  public void runProgram() {

  }

  @Override
  public void handleLoadEvent() {

  }

  @Override
  public void handleSaveEvent() {

  }
}
