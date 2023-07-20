package controller;

import model.ModelInterface;
import view.ViewInterface;

public class ControllerImplementation implements ControllerInterface {
  private final ModelInterface model;
  private final ViewInterface view;
  private final Readable inReadable;
  private CommandParser commandParser;

  /**
   * Default constructor, initializes all fields.
   * @param model IModel object.
   * @param view IView object.
   * @param inReadable Readable object.
   * @throws IllegalArgumentException if any of the arguments are null.
   */
  public ControllerImplementation(ModelInterface model, ViewInterface view, Readable inReadable)
          throws IllegalArgumentException {
    if (model == null || view == null || inReadable == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.inReadable = inReadable;
  }


  public void execute() {
    // TODO implement here
  }
}
