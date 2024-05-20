import controller.ControllerImplementation;
import model.ImageDatabase;
import model.ImageDatabaseInterface;
import model.Operations.OperationInterface;
import view.ViewImplementation;

import java.io.InputStreamReader;

public class IME {

  public static void main(String[] args) {
    ImageDatabaseInterface model = new ImageDatabase();
    ViewImplementation view = new ViewImplementation(System.out);
    Readable in = new InputStreamReader(System.in);
    ControllerImplementation controller = new ControllerImplementation(model, view, in);
    OperationInterface operations;
    controller.go();
  }
}
