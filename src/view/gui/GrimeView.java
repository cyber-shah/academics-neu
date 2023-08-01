package view.gui;

import model.image.CustomImageState;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GrimeView extends JFrame implements ActionListener {

  private final JLabel showText;
  private JPanel imageDatabasePanel;
  private JScrollPane histogramPanel;
  private final Canvas imageCanvas;
  private List<CustomImageState> imageDatabaseList;
  private String currentImageID;

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
    this.imageDatabaseList = new ArrayList<>();
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
    JScrollPane imagePanel = new JScrollPane();
    this.imageCanvas = new Canvas();
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
    this.addImageDatabasePanel(toolbarPanel);
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
    /* NOTE : This is how an action has to be handled
            1. convert the action into an a class - `CustomEvent`
            2. set all the parameters of the event
            3. controller accepts the event as a ```CustomEvent``` object
                3.1 it will extract the parameters from the event
                3.2 call the command strategy in the format required
                so for example, load event will be emitted as
                ["Load", "path/to/file", image-name]
                 or for grey scale, it will be
                ["greyscale", "Luma", image-name]
            5. returns the image database
            6. view updates the image based on the newest image in the database
     */

    // can the controller handle events in this format?
    // this.emit (new CustomEvent (this, e.getActionCommand(), Panel.getLatestImageName()));
    // then the controller passes it to the appropriate model.
    // the model updates the image database to controller.
    // controller passes the updated image database to the view.
    // view updates the image database.
    // view canvas shows the latest image in the database.

    if (e.getActionCommand().equals("Exit")) {
      System.exit(0);
    }

    // 1. Load
    else if (e.getActionCommand().equals("Load")) {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "Supported Images", "jpg", "bmp", "png", "ppm", "jpeg");
      fileChooser.setFileFilter(filter);
      int retvalue = fileChooser.showOpenDialog(this);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        String filePath = f.getAbsolutePath();
        // NOTE : 1. updated the current image ID here
        //        2. IO events always use source ID and destination ID as null
        currentImageID = UUID.randomUUID().toString();
        this.emit(new CustomEvent(this,"io", "load", filePath, currentImageID, null));
      }
    }

    // 2. Save
    else if (e.getActionCommand().equals("Save")) {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
              "Supported Images", "jpg", "bmp", "png", "ppm", "jpeg");
      fileChooser.setFileFilter(filter);
      int retvalue = fileChooser.showSaveDialog(this);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        String filePath = f.getAbsolutePath();
        // NOTE : 1. updated the current image ID here
        //        2. IO events always use source ID and destination ID as null
        this.emit(new CustomEvent(this,"io","save", filePath, currentImageID, null));
      }
    }

    // 3. for Filter
    else if (e.getActionCommand().equals("Blur") ||
            e.getActionCommand().equals("Sharpen")) {
      // NOTE : updated the current image ID here
      String newImageID = UUID.randomUUID().toString();
      this.emit(new CustomEvent(this, "filter", e.getActionCommand(), null, currentImageID, newImageID));
      currentImageID = newImageID;
    }

    // 4. for color
    else if (e.getActionCommand().equals("Sepia") ||
            e.getActionCommand().equals("Greyscale")) {
      // NOTE : updated the current image ID here
      String newImageID = UUID.randomUUID().toString();
      this.emit(new CustomEvent(this, "color", e.getActionCommand(), null, currentImageID, newImageID));
      currentImageID = newImageID;
    }
  }

  private void emit(CustomEvent event) {
    for (CustomEventsListener listener : this.listeners) {
      listener.handleEvent(event);
    }
  }

  public void updateImageCanvas(CustomImageState image) {
    this.imageCanvas.setImage(image.getBufferedImage());
  }


  public void showMessage(String message) {
    showText.setText(message);
  }






  // HELPER METHODS to add panels to the toolbar panel ---------------------------------------

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
    JPanel BrightenDarken = new JPanel(new BorderLayout());
    BrightenDarken.setBorder(BorderFactory.createTitledBorder("Brighten/Darken"));
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
  }

  /**
   * Helper method to add Histogram panel to the toolbar panel.
   *
   * @param toolbarPanel the toolbar added to the main panel
   */
  private void addHistogramPanel(JPanel toolbarPanel) {
    this.histogramPanel = new JScrollPane();
    this.histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram"));
    this.histogramPanel.setPreferredSize(new Dimension(200, 400));
    toolbarPanel.add(this.histogramPanel);
  }

  /**
   * Helper method to add Image Database panel to the toolbar panel.
   *
   * @param toolbarPanel the toolbar added to the main panel.
   */
  private void addImageDatabasePanel(JPanel toolbarPanel) {
    this.imageDatabasePanel = new JPanel();
    this.imageDatabasePanel.setBorder(BorderFactory.createTitledBorder("Image Database"));
    DefaultListModel<CustomImageState> listModel = new DefaultListModel<>();
    JList<CustomImageState> imageList = new JList<>(listModel);
    imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    toolbarPanel.add(this.imageDatabasePanel);
  }
}
