package view.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GrimeView extends JFrame implements ActionListener {

  private final JLabel showText;
  private final List<CustomEventsListener> listeners;

  /**
   * Constructor for the view.
   * It is done in the following order:
   * 1. Initialize the center : image canvas.
   * 2. Initialize the south : show text.
   * 3. Initialize the west : toolbar panel.
   */
  public GrimeView() {
    super();
    setTitle("Grime");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.listeners = new ArrayList<>();
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    // FIXME : set the focus to always be on the main window
    // FIXME : add a list of images loaded - image database;
    // FIXME : add an option to view original image

    // 0. Create the main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    // 1. Set the imageCanvas inside a JScrollPane and add it to the main panel
    // NOTE : Main Panel's CENTER
    view.gui.Canvas imageCanvas = new Canvas();
    imageCanvas.setPreferredSize(new Dimension(400, 400));
    JScrollPane imagePanel = new JScrollPane(imageCanvas);
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    // 2. Add show text to the main panel
    // NOTE : Main Panel's SOUTH
    showText = new JLabel("Display Text");
    showText.setPreferredSize(new Dimension(mainPanel.getWidth(), 50));
    mainPanel.add(showText, BorderLayout.SOUTH);

    // 3. Add toolbar panel to main panel
    // NOTE : Main Panel's EAST
    JPanel toolbarPanel = new JPanel();
    toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.Y_AXIS));
    this.addToolbarPanel(toolbarPanel);
    // toolbarPanel.setPreferredSize(new Dimension(200, mainPanel.getHeight()));
    mainPanel.add(toolbarPanel, BorderLayout.EAST);

    // 4. Add main panel to the window
    setContentPane(mainPanel);
    setVisible(true);

  }

  /**
   * Helper method to add panels inside the toolbar panel.
   * Each panel has a different layout.
   *
   * @param toolbarPanel the toolbar added to the main panel
   */
  private void addToolbarPanel(JPanel toolbarPanel) {

    // 1. Add IO buttons, and add action listener to them
    toolbarPanel.add(new JLabel("IO"));
    JPanel ioPanel = new JPanel(new GridLayout(2, 2));
    // add buttons
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton("Load");
    JButton exitButton = new JButton("Exit");
    // add buttons to panel
    ioPanel.add(saveButton);
    ioPanel.add(loadButton);
    ioPanel.add(exitButton);
    // add action listeners to buttons
    saveButton.addActionListener(this);
    loadButton.addActionListener(this);
    exitButton.addActionListener(this);
    // add panel to toolbar
    toolbarPanel.add(ioPanel);

    // 2. Add GreyScale button, and add action listener to it
    toolbarPanel.add(new JLabel("Greyscale"));
    JPanel greyScalePanel = new JPanel(new GridLayout(3, 2));
    // add buttons
    JButton greyScaleButton = new JButton("Luma");
    JButton valueButton = new JButton("Value");
    JButton intensityButton = new JButton("Intensity");
    JButton redButton = new JButton("Red Component");
    JButton greenButton = new JButton("Green Component");
    JButton blueButton = new JButton("Blue Component");
    // add buttons to panel
    greyScalePanel.add(greyScaleButton);
    greyScalePanel.add(valueButton);
    greyScalePanel.add(intensityButton);
    greyScalePanel.add(redButton);
    greyScalePanel.add(greenButton);
    greyScalePanel.add(blueButton);
    // add action listeners to buttons
    greyScaleButton.addActionListener(this);
    valueButton.addActionListener(this);
    intensityButton.addActionListener(this);
    redButton.addActionListener(this);
    greenButton.addActionListener(this);
    blueButton.addActionListener(this);
    // add panel to toolbar
    toolbarPanel.add(greyScalePanel);

    // 3. Add Filter buttons, and add action listener to them
    toolbarPanel.add(new JLabel("Filter"));
    JPanel filterPanel = new JPanel(new GridLayout(1, 2));
    // add buttons
    JButton blurButton = new JButton("Blur");
    JButton sharpenButton = new JButton("Sharpen");
    // add buttons to panel
    filterPanel.add(blurButton);
    filterPanel.add(sharpenButton);
    // add action listeners to buttons
    blurButton.addActionListener(this);
    sharpenButton.addActionListener(this);
    // add panel to toolbar
    toolbarPanel.add(filterPanel);

    // 4. Add Color Transform buttons, and add action listener to them
    toolbarPanel.add(new JLabel("Color"));
    JPanel colorTransformPanel = new JPanel(new GridLayout(1, 2));
    // add buttons
    JButton sepiaButton = new JButton("Sepia");
    JButton greyscaleButton = new JButton("Greyscale");
    // add buttons to panel
    colorTransformPanel.add(sepiaButton);
    colorTransformPanel.add(greyscaleButton);
    // add action listeners to buttons
    sepiaButton.addActionListener(this);
    greyscaleButton.addActionListener(this);
    // add panel to toolbar
    toolbarPanel.add(colorTransformPanel);


    // 5. Add Brighten and Darken sliders + buttons and add action listener to them
    toolbarPanel.add(new JLabel("Brighten/Darken"));
    JPanel BrightenDarken = new JPanel(new BorderLayout());
    // create buttons, sliders and labels
    JSlider slider = new JSlider(JSlider.HORIZONTAL, -255, 255, 0);
    JButton applyButton = new JButton("Apply");
    JLabel Brighten = new JLabel("Brighten");
    JLabel Darken = new JLabel("Darken");
    // add them to the panel
    BrightenDarken.add(applyButton, BorderLayout.SOUTH);
    BrightenDarken.add(slider, BorderLayout.CENTER);
    BrightenDarken.add(Brighten, BorderLayout.EAST);
    BrightenDarken.add(Darken, BorderLayout.WEST);
    // add action listeners to buttons
    applyButton.addActionListener(this);
    // finally add it to the toolbar panel
    toolbarPanel.add(BrightenDarken);

    // 5. Add Histogram
    toolbarPanel.add(new JLabel("Histogram"));
    JScrollPane histogramPanel = new JScrollPane();
    histogramPanel.setPreferredSize(new Dimension(200, 400));
    toolbarPanel.add(histogramPanel);

    // 6. Add border to all panels
    /*
    JButton undoButton = new JButton("Undo");
    JButton redoButton = new JButton("Redo");
    JButton clearButton = new JButton("Clear");
    toolbarPanel.add(undoButton);
    toolbarPanel.add(redoButton);
    toolbarPanel.add(clearButton);
    */
  }

  public void addEventsListener(CustomEventsListener listener) {
    this.listeners.add(listener);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // NOTE : This is how an action has to be handled
    //        1. convert the action into an array of strings
    //        2. emit an event with the array of strings
    //        3. controller accepts the event in the format it needs
    //            so for example, load event will be emitted as
    //            ["Load", "path/to/file", image-name]
    //             or for grey scale, it will be
    //            ["greyscale", "Luma", image-name]
    //            ["greyscale", "Value", image-name]
    //        4. controller handles the event - calls appropriate model
    //        5. returns the imagedatabase
    //        6. view updates the image based on the newest image in the database



    if (e.getActionCommand().equals("Exit")) {
      System.exit(0);
    }
    else if (e.getActionCommand().equals("Load")) {
      final JFileChooser fchooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "JPG & GIF Images", "jpg", "gif");
      fchooser.setFileFilter(filter);
      int retvalue = fchooser.showOpenDialog(this);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fchooser.getSelectedFile();
        showText.setText(f.getAbsolutePath());
      }
      this.emit(new CustomEvent(this, "Load"));
    }
    // for any button clicked, emit an event
    String event = e.getActionCommand();
    this.emit(new CustomEvent(this, event));
    }

  private void emit(CustomEvent event) {
    for (CustomEventsListener listener : this.listeners) {
      listener.handleEvent(event);
    }
  }
}
