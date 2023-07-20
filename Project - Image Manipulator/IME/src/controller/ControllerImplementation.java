package controller;

import model.ImageDatabaseInterface;
import model.ModelInterface;
import view.ViewInterface;

import java.util.Scanner;

public class ControllerImplementation implements ControllerInterface {
  private final ImageDatabaseInterface model;
  private final Appendable view;
  private final Readable inReadable;
  private CommandParser commandParser;

  /**
   * Default constructor, initializes all fields.
   * @param model IModel object.
   * @param view IView object.
   * @param inReadable Readable object.
   * @throws IllegalArgumentException if any of the arguments are null.
   */
  public ControllerImplementation(ImageDatabaseInterface model, Appendable view, Readable inReadable)
          throws IllegalArgumentException {
    if (model == null || view == null || inReadable == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.inReadable = inReadable;
  }


  public void go() {
    Scanner scanner = new Scanner(this.inReadable);

    while (scanner.hasNextLine()) {
      String command = scanner.next();
      // TODO: Here goes the command pattern
    }


  }
}
