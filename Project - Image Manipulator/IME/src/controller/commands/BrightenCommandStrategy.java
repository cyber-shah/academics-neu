package controller.commands;

import model.Image.ImageState;
import model.ImageDatabaseInterface;
import model.Operations.BrightenOperation;
import java.util.Scanner;

public class BrightenCommandStrategy implements CommandStrategyInterface {

  /**
   * This method parses the arguments and then calls the brighten method on the image.
   * Once the image is brightened, it is added to the imageDatabase.
   *
   * @param scanner       Scanner object.
   * @param imageDatabase ImageDatabaseInterface object.
   */
  @Override
  public void run(Scanner scanner, ImageDatabaseInterface imageDatabase) {
    // 0. Validate all the arguments.
    String[] args;
    try {
      args = validateArguments(scanner);
    } catch (IllegalStateException e) {
      throw new IllegalStateException(e.getMessage());
    }

    String sourceImageID = args[0];
    int value = Integer.parseInt(args[1]);
    String destinationID = args[2];

    // Once all the arguments are validated, call the brighten method.
    // 1. Get the sourceImage from the imageDatabase using the sourceImageID.
    // 2. Create a new BrightenTransformation object using the sourceImage and the value.
    ImageState newImage = new BrightenOperation().
            applyOperation(imageDatabase.getImage(sourceImageID), value);

    // 3. Add the new image to the imageDatabase using the destinationID.
    imageDatabase.addImage(destinationID, newImage);
  }

  /**
   * This method validates the arguments passed to the command.
   *
   * @param scanner Scanner object.
   */
  private String[] validateArguments(Scanner scanner) {
    String[] args = new String[3];
    // 1. Validate the sourceImageID.
    if (!scanner.hasNext()) {
      throw new IllegalStateException("SourceImageID not found.");
    }
    args[0] = scanner.next();

    // 2. Validate the value.
    if (!scanner.hasNextInt()) {
      throw new IllegalStateException("Value must be an integer.");
    }
    args[1] = Integer.toString(scanner.nextInt());

    // 3. Validate the destinationID.
    if (!scanner.hasNext()) {
      throw new IllegalStateException("DestinationID not found.");
    }
    args[2] = scanner.next();

    // 4. Validate that there are no more arguments.
    if (scanner.hasNext()) {
      throw new IllegalStateException("Too many arguments.");
    }
    return args;
  }
}
