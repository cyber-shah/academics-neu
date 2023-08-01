package controller;

import controller.commandmanager.CommandsManager;
import controller.commandmanager.CommandsManagerInterface;
import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabaseInterface;
import view.gui.CustomEvent;
import view.gui.GUIView;

import java.util.Objects;

/**
 * This class is the controller for the GUI.
 * It implements the ControllerGUIInterface.
 */
public class ControllerGUI implements ControllerGUIInterface {
  private final ImageDatabaseInterface imageDatabase;
  private final GUIView view;
  private final CommandsManagerInterface commandsManager;


  /**
   * This constructor creates a new ControllerGUI object.
   *
   * @param imageDatabase the model.
   * @param view the view.
   */
  public ControllerGUI(ImageDatabaseInterface imageDatabase, GUIView view) {
    this.imageDatabase = Objects.requireNonNull(imageDatabase);
    this.view = Objects.requireNonNull(view);
    // NOTE : Subscribe to the view's custom events.
    view.addEventsListener(this);
    this.commandsManager = new CommandsManager();
    commandsManager.registerAllCommands();
  }

  /**
   * This method runs the program.
   *
   * @param event the event to be handled.
   */
  @Override
  public void handleEvent(CustomEvent event) {
    if (event.getEventType().equalsIgnoreCase("io")) {
      // NOTE : 2. IO events always use source ID and destination ID as null
      //        <load/save> <filePath> <imageID>
      String[] commandList = new String[3];
      commandList[0] = event.getEventName();
      commandList[1] = event.getFilePath();
      commandList[2] = event.getSourceID();
      try {
        CommandStrategyInterface commandStrategyObject =
                commandsManager.getCommandStrategy(commandList);
        commandStrategyObject.run(commandList, this.imageDatabase);
        view.updateImageCanvas(this.imageDatabase.getImage(event.getSourceID()));
        view.showMessage(event.getEventType() + " "
                + event.getEventName() + " " + "executed successfully");
      }
      catch (Exception e) {
        view.showMessage(e.getMessage());
      }
    }
    // for all other events, source ID is the source image ID and destination ID is the destination
    // and file path is null
    else {
      // NOTE : command of the format:
      //        <eventType> <eventName> <sourceID> <destID>
      String[] commandList = new String[4];
      commandList[0] = event.getEventType(); // filter, color, brighten, greyscale ...
      commandList[1] = event.getEventName(); // blur, sharpen, sepia OR VALUE ...
      commandList[2] = event.getSourceID(); // source image ID
      commandList[3] = event.getDestID(); // destination image ID
      try {
        CommandStrategyInterface commandStrategyObject =
                commandsManager.getCommandStrategy(commandList);
        commandStrategyObject.run(commandList, this.imageDatabase);
        view.updateImageCanvas(this.imageDatabase.getImage(event.getDestID()));
        view.showMessage(event.getEventType() + " "
                + event.getEventName() + " " + "Applied successfully");
      }
      catch (Exception e) {
        view.showMessage(e.getMessage());
      }
    }
  }
}
