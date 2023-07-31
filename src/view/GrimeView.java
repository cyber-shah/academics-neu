package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class GrimeView extends JFrame implements ActionListener, KeyListener {

  private final JPanel mainPanel;
  private final JLabel showText;
  private final JPanel toolbarPanel;

  private final view.Canvas imageCanvas;

  public GrimeView() {
    super();
    setTitle("Grime");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    // FIXME : set the focus to always be on the main window

    // 0. Create the main panel
    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    // 1. Set the imageCanvas inside a JScrollPane and add it to the main panel
    // NOTE : Main Panel's CENTER
    this.imageCanvas = new Canvas();
    this.imageCanvas.setPreferredSize(new Dimension(400, 400));
    JScrollPane imagePanel = new JScrollPane(this.imageCanvas);
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    // 2. Add show text to the main panel
    // NOTE : Main Panel's SOUTH
    this.showText = new JLabel("Display Text");
    this.showText.setPreferredSize(new Dimension(mainPanel.getWidth(), 50));
    mainPanel.add(this.showText, BorderLayout.SOUTH);

    // 3. Add toolbar panel to main panel
    // NOTE : Main Panel's EAST
    toolbarPanel = new JPanel();
    toolbarPanel.setLayout(new BoxLayout(toolbarPanel, BoxLayout.Y_AXIS));
    this.addToolbarPanel(toolbarPanel);
//    toolbarPanel.setPreferredSize(new Dimension(200, mainPanel.getHeight()));
    mainPanel.add(toolbarPanel, BorderLayout.EAST);



    setContentPane(mainPanel);
    setVisible(true);

  }


  private void addToolbarPanel(JPanel toolbarPanel) {
    int borderThickness = 1;
    Color borderColor = Color.BLACK;

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
    toolbarPanel.add(new JLabel("Grey-scale"));
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
    histogramPanel.setPreferredSize(new Dimension(200, 200));
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
  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
