package controller.commandsStrategy;

import model.ImageDatabaseInterface;

public interface CommandStrategyInterface {

    void run(String[] commandsList, ImageDatabaseInterface imageDatabase);
}
