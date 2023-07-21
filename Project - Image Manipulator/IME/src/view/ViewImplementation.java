package view;

import controller.ControllerInterface;

public class ViewImplementation implements ViewInterface {
  private ControllerInterface controller;
  private Appendable outAppendable;

  public ViewImplementation(ControllerInterface controller, Appendable out) {
    if (controller == null || out == null) {
      throw new IllegalArgumentException("Controller and Appendable must not be null");
    }
    this.controller = controller;
    this.outAppendable = out;
  }

  @Override
  public void render() {

  }
}

