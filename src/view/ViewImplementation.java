package view;

import java.io.IOException;

public class ViewImplementation implements ViewInterface {
  private Appendable outAppendable;

  public ViewImplementation(Appendable out) {
    this.outAppendable = out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.outAppendable.append(message);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IOException("Failed to transmit message.", e);
    }
  }
}

