package controller;

public class Command {
  private final CommandType commandType;
  private final String imagePath;
  private final String imageName;
  private final String destinationName;

  public Command(CommandType commandType, String imagePath, String imageName, String destinationName) {
    this.commandType = commandType;
    this.imagePath = imagePath;
    this.imageName = imageName;
    this.destinationName = destinationName;
  }

  public CommandType getCommandType() {
    return this.commandType;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public String getImageName() {
    return this.imageName;
  }

  public String getDestinationName() {
    return this.destinationName;
  }
}
