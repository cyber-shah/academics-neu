package controller.commands;

import model.ImageDatabaseInterface;

import java.util.Scanner;

public interface Command {

  void run(Scanner scanner, ImageDatabaseInterface model);

}
