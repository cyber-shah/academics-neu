package controller;

import controller.commandmanager.CommandsManager;
import controller.commandmanager.CommandsManagerInterface;
import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabaseInterface;
import view.gui.CustomEvent;
import view.gui.GrimeView;

import java.util.Objects;

public class ControllerGUI implements ControllerGUIInterface {
  private final ImageDatabaseInterface imageDatabase;
  private final GrimeView view;
  private final CommandsManagerInterface commandsManager;


  public ControllerGUI(ImageDatabaseInterface imageDatabase, GrimeView view) {
    this.imageDatabase = Objects.requireNonNull(imageDatabase);
    this.view = Objects.requireNonNull(view);
    // NOTE : Subscribe to the view's custom events.
    view.addEventsListener(this);
    this.commandsManager = new CommandsManager();
    commandsManager.registerAllCommands();
  }

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
        view.showMessage(event.getEventType() + " " + event.getEventName() + " " + "executed successfully");
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
        view.showMessage(event.getEventType() + " " + "Applied successfully");
      }
      catch (Exception e) {
        view.showMessage(e.getMessage());
      }
    }
  }

  @Override
  public void runProgram() {

  }

}
