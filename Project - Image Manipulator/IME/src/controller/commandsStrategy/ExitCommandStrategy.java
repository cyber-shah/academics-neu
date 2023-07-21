package controller.commandsStrategy;

import model.ImageDatabaseInterface;
import model.Operations.OperationInterface;

import java.util.Scanner;

public class ExitCommandStrategy implements CommandStrategyInterface {

    @Override
    public void run(String[] commandsList, ImageDatabaseInterface model) {
        return;
    }
}
