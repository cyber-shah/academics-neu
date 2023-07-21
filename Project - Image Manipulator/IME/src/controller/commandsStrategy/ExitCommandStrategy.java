package controller.commandsStrategy;

import model.ImageDatabaseInterface;

public class ExitCommandStrategy implements CommandStrategyInterface {

    @Override
    public void run(String[] commandsList, ImageDatabaseInterface model) {
        System.exit(0);
    }
}
