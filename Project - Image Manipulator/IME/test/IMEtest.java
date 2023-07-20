import controller.ControllerInterface;
import model.ModelInterface;
import org.junit.Before;
import view.ViewInterface;


public class IMEtest {
  private ModelInterface model;
  private ViewInterface view;
  private ControllerInterface controller;

  @Before
  public void setUp() throws Exception {
    model = new ModelInterface();
    view = new ViewInterface();
    controller = new ControllerInterface(model, view, System.in);
  }
}
