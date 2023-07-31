package controller.commandmanager;

import controller.commandsstrategy.CommandStrategyInterface;
import model.operations.OperationInterface;
import model.operations.color.SepiaColor;

import java.util.HashMap;
import java.util.Map;

public class GUICommandsManager{
  private Map<String, CommandStrategyInterface> commandsMap;

  public GUICommandsManager() {
    commandsMap = new HashMap<>();
  }

  public OperationInterface getOperation(String commandName) {
    return (OperationInterface) commandsMap.getOrDefault(commandName, null);
  }

  public void registerAllCommands() {
//    // filter operations
//    commandsMap.put("Blur", new model.operations.filters.BlurFilter());
//    commandsMap.put("Sharpen", new model.operations.filters.SharpenFilter());
//    // color transformations
//    commandsMap.put("Sepia", new model.operations.color.SepiaColor());
//    commandsMap.put("Greyscale", new model.operations.color.GreyscaleColor());
//    // greyscale operations
//    commandsMap.put("Luma", new model.operations.greyscale.LumaOperation());
//    commandsMap.put("Intensity", new model.operations.greyscale.IntensityOperation());
//    commandsMap.put("Value", new model.operations.greyscale.ValueOperation());

  }
}
