package testcontroller.mocks;

import model.image.ImageState;
import model.operations.OperationInterface;
import model.ImageDatabaseInterface;

import java.util.Arrays;

public class MockOperation implements OperationInterface {
  private ImageDatabaseInterface imageDatabase;
  private String[] commandsList;
  public MockOperation(ImageDatabaseInterface imageDatabase, String[] commandsList) {
    this.imageDatabase = imageDatabase;
    this.commandsList = commandsList;
  }

  @Override
  public ImageState applyOperation() {
    throw new IllegalArgumentException(Arrays.toString(commandsList));
  }
}
