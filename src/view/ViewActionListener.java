package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewActionListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    // which button was clicked?
    switch (e.getActionCommand()) {
      case "save":
        showText.setText("Clicked Save");
        break;
      case "load":
        showText.setText("Clicked Load");
        break;
    }
  }
}
