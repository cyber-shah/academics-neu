package testcontroller.mocks;

import controller.commandsstrategy.CommandStrategyInterface;
import model.ImageDatabaseInterface;

public class MockCommandStrategy implements CommandStrategyInterface {

  @Override
  public void run(String[] commandsList, ImageDatabaseInterface imageDatabase) {
  }
}
