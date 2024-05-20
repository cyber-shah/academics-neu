package view.gui;

import model.image.CustomImageState;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class represents the main view of the application.
 * It uses Java Swing to create the GUI.
 * It creates custom events which are then handled by the controller.
 */
public class ViewGUI extends JFrame implements ViewGUIInterface {

  private final JLabel showText;
  private HistogramPanel histogramPanel;
  private final ImageCanvas imageCanvas;
  private String currentImageID;
  private final List<CustomEventsListener> listeners;
  // Brightness sliders and text -----------------
  private JSlider brightnessSlider;
  private JLabel valueText;

  /**
   * Constructor for the view.
   * It is done in the following order:
   * 1. Initialize the center : image canvas.
   * 2. Initialize the south : show text.
   * 3. Initialize the west : toolbar panel.
   */
  public ViewGUI() {
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
    // OPTIMIZE : set the focus to always be on the main window
    // OPTIMIZE : show the stack of images in the main window

    // 0. Create the main panel
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    // 1. Set the imageCanvas inside a JScrollPane and add it to the main panel
    // NOTE : Main Panel's CENTER
    JScrollPane imagePanel = new JScrollPane();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image"));
    this.imageCanvas = new ImageCanvas();
    imagePanel.setViewportView(this.imageCanvas);
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
   * Adds Subscribers to the listeners list.
   *
   * @param listener the subscriber to be added.
   */
  @Override
  public void addEventsListener(CustomEventsListener listener) {
    this.listeners.add(listener);
  }

  /**
   * It catches the event, modifies it to a custom event and emits it.
   *
   * @param e the event to be processed.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    /*
    This is how an action has to be handled
    0. view stores the current image ID
    1. convert the action into a class - `CustomEvent`
    2. set all the parameters of the event
    3. controller accepts the event as a ```CustomEvent``` object
        3.1 it will extract the parameters from the event
        3.2 call the command strategy in the format required
            so for example, load event will be emitted as
            ["Load", "path/to/file", image-name]
            or for grey scale, it will be
            ["greyscale", "Luma", image-name]
     4. view updates the image with current-image-id to new-image-id (stored by the view)
     5. updates the image with new-image-id
     */
    String actionName = e.getActionCommand();
    String newImageID;

    if (actionName.equals("Load")) {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "Supported Images", "jpg", "bmp", "png", "ppm", "jpeg");
      fileChooser.setFileFilter(filter);
      int revalue = fileChooser.showOpenDialog(this);
      if (revalue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        String filePath = f.getAbsolutePath();
        // NOTE : 1. updated the current image ID here
        //        2. IO events always use source ID and destination ID as null
        currentImageID = UUID.randomUUID().toString();
        this.emit(new CustomEvent(this, "io", "load",
                filePath, currentImageID, null));
      }
    }

    else if (actionName.equals("Save")) {
      final JFileChooser saveChooser = new JFileChooser(".");
      int rvalue = saveChooser.showSaveDialog(this);
      if (rvalue == JFileChooser.APPROVE_OPTION) {
        File f = saveChooser.getSelectedFile();
        String filePath = f.getAbsolutePath();
        // NOTE : 2. IO events always use source ID and destination ID as null
        this.emit(new CustomEvent(this, "io", "save",
                filePath, currentImageID, null));
      }
    }

    // CHECK IF IMAGE ALREADY LOADED --------------------
    if (currentImageID == null && !actionName.equals("Load")) {
      this.showText.setText("Please load an image first");
    }


    else if (actionName.equals("Blur") || actionName.equals("Sharpen")) {
      // NOTE : updated the current image ID here
      newImageID = UUID.randomUUID().toString();
      this.emit(new CustomEvent(this, "filter", e.getActionCommand(),
              null, currentImageID, newImageID));
      currentImageID = newImageID;
    }

    else if (actionName.equals("Sepia") || actionName.equals("Greyscale")) {
      // NOTE : updated the current image ID here
      newImageID = UUID.randomUUID().toString();
      this.emit(new CustomEvent(this, "color", e.getActionCommand(),
              null, currentImageID, newImageID));
      currentImageID = newImageID;
    }

    else if (actionName.equals("Luma") || actionName.equals("Intensity")
            || actionName.equals("Value") || actionName.equals("Red")
            || actionName.equals("Green") || actionName.equals("Blue")) {
      // NOTE : updated the current image ID here
      newImageID = UUID.randomUUID().toString();
      this.emit(new CustomEvent(this, "greyscale", e.getActionCommand(),
              null, currentImageID, newImageID));
      currentImageID = newImageID;
    }

    else if (actionName.equals("Apply")) {
      int sliderValue = this.brightnessSlider.getValue();
      newImageID = UUID.randomUUID().toString();
      this.emit(new CustomEvent(this, "brighten", String.valueOf(sliderValue),
              null, currentImageID, newImageID));
      currentImageID = newImageID;
    }

    else {
      this.showMessage("Invalid Action");
    }
  }


  /**
   * Helper method to emit an event to all the listeners.
   *
   * @param event the event to be emitted.
   */
  private void emit(CustomEvent event) {
    for (CustomEventsListener listener : this.listeners) {
      listener.handleEvent(event);
    }
  }

  /**
   * Updates the image canvas with the given image.
   *
   * @param image the image to be updated.
   */
  @Override
  public void updateImageCanvas(CustomImageState image) {
    this.imageCanvas.setImage(image.getBufferedImage());
  }

  /**
   * Updates the GUI with the given message.
   *
   * @param message the message to be updated.
   */
  @Override
  public void showMessage(String message) {
    showText.setText(message);
  }

  /**
   * Redraws the histogram panel with given values.
   *
   * @param histogramValues the values to be updated.
   */
  @Override
  public void updateHistogram(int[][] histogramValues) {
    this.histogramPanel.setHistogram(histogramValues);
  }

  /**
   * This method updates the JLabel with a value whenever the slider is moved.
   *
   * @param e a ChangeEvent object.
   */
  @Override
  public void stateChanged(ChangeEvent e) {
    if (e.getSource() == this.brightnessSlider) {
      int value = this.brightnessSlider.getValue();
      // Update text field with slider value
      this.valueText.setText(String.valueOf(value));
    }
  }



  // HELPER METHODS to add panels to the toolbar panel ---------------------------------------

  /**
   * Helper method to add panels inside the toolbar panel.
   * Each panel has a different layout.
   *
   * @param toolbarPanel the toolbar added to the main panel
   */
  private void addToolbarPanel(JPanel toolbarPanel) {
    this.addIOPanel(toolbarPanel);
    this.addGreyscalePanel(toolbarPanel);
    this.addFilterPanel(toolbarPanel);
    this.addColorPanel(toolbarPanel);
    this.addBrightenDarkenPanel(toolbarPanel);
    this.addHistogramPanel(toolbarPanel);
  }

  /**
   * Helper method to add IO buttons to the toolbar panel.
   * Each button has a different action command.
   *
   * @param toolbarPanel the toolbar added to the main panel
   */
  private void addIOPanel(JPanel toolbarPanel) {
    // 1. Add IO buttons, and add action listener to them
    JPanel ioPanel = new JPanel(new GridLayout(1, 2));
    ioPanel.setBorder(BorderFactory.createTitledBorder("IO"));
    // add buttons
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");
    // add buttons to panel
    ioPanel.add(loadButton);
    ioPanel.add(saveButton);
    // add action listeners to buttons
    loadButton.addActionListener(this);
    saveButton.addActionListener(this);
    // add panel to toolbar
    toolbarPanel.add(ioPanel);
  }

  /**
   * Helper method to add color transform buttons to the toolbar panel.
   * Each button has a different action command.
   *
   * @param toolbarPanel the toolbar added to the main panel
   */
  private void addGreyscalePanel(JPanel toolbarPanel) {
    JPanel greyScalePanel = new JPanel(new GridLayout(3, 2));
    greyScalePanel.setBorder(BorderFactory.createTitledBorder("Greyscale"));
    // add buttons
    JButton greyScaleButton = new JButton("Luma");
    JButton valueButton = new JButton("Value");
    JButton intensityButton = new JButton("Intensity");
    JButton redButton = new JButton("Red");
    JButton greenButton = new JButton("Green");
    JButton blueButton = new JButton("Blue");
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
  }

  /**
   * Helper method to add color transform buttons to the toolbar panel.
   *
   * @param toolbarPanel the toolbar added to the main panel
   */
  private void addFilterPanel(JPanel toolbarPanel) {
    JPanel filterPanel = new JPanel(new GridLayout(1, 2));
    filterPanel.setBorder(BorderFactory.createTitledBorder("Filter"));
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
  }

  /**
   * Helper method to add color transform buttons to the toolbar panel.
   *
   * @param toolbarPanel the toolbar added to the main panel
   */
  private void addColorPanel(JPanel toolbarPanel) {
    JPanel colorTransformPanel = new JPanel(new GridLayout(1, 2));
    colorTransformPanel.setBorder(BorderFactory.createTitledBorder("Color Transform"));
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
  }

  /**
   * Helper method to add Brighten Darken to the toolbar panel.
   *
   * @param toolbarPanel the toolbar added to the main panel
   */
  private void addBrightenDarkenPanel(JPanel toolbarPanel) {
    JPanel brightenDarken = new JPanel(new BorderLayout());
    brightenDarken.setBorder(BorderFactory.createTitledBorder("Brighten/Darken"));
    // create buttons, sliders and labels
    this.brightnessSlider = new JSlider(JSlider.HORIZONTAL, -255, 255, 0);
    JButton applyButton = new JButton("Apply");
    JLabel brighten = new JLabel("Brighten");
    JLabel darken = new JLabel("Darken");
    this.valueText = new JLabel("0", JLabel.CENTER);
    // add them to the panel
    brightenDarken.add(applyButton, BorderLayout.SOUTH);
    brightenDarken.add(this.brightnessSlider, BorderLayout.CENTER);
    brightenDarken.add(brighten, BorderLayout.EAST);
    brightenDarken.add(darken, BorderLayout.WEST);
    brightenDarken.add(valueText, BorderLayout.NORTH);
    // add action listeners to buttons
    applyButton.addActionListener(this);
    this.brightnessSlider.addChangeListener(this);
    // finally add it to the toolbar panel
    toolbarPanel.add(brightenDarken);
  }

  /**
   * Helper method to add Histogram panel to the toolbar panel.
   *
   * @param toolbarPanel the toolbar added to the main panel
   */
  private void addHistogramPanel(JPanel toolbarPanel) {
    JPanel histogramArea = new JPanel();
    histogramArea.setLayout(new BorderLayout());
    this.histogramPanel = new HistogramPanel();
    histogramArea.add(this.histogramPanel, BorderLayout.CENTER);
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    this.histogramPanel.setPreferredSize(new Dimension(200, 400));
    toolbarPanel.add(histogramArea);
  }

}
