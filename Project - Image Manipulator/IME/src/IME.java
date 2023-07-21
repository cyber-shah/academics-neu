import controller.ControllerImplementation;
import model.ImageDatabase;
import model.ImageDatabaseInterface;
import model.Operations.OperationInterface;
import view.ViewImplementation;

import java.io.InputStream;
import java.io.InputStreamReader;

public class IME {

  public static void main(String[] args) {
    ImageDatabaseInterface model = new ImageDatabase();
    Appendable out = System.out;
    Readable in = new InputStreamReader(System.in);
    ControllerImplementation controller = new ControllerImplementation(model, out, in);
    ViewImplementation view = new ViewImplementation(controller, out);
    OperationInterface operations;
    controller.go();
  }
}
