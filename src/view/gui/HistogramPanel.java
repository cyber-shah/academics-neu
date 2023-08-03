package view.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

/**
 * This class represents the Histogram Panel.
 * It is used to display the histogram of the image.
 */
public class HistogramPanel extends JPanel {
  private int[] redHistogram;
  private int[] greenHistogram;
  private int[] blueHistogram;
  private int[] averageHistogram;

  /**
   * Constructs a new HistogramPanel object.
   */
  public HistogramPanel() {
    super();
    // Set the preferred size
    setPreferredSize(new Dimension(300, 300));
    this.averageHistogram = new int[256];
    this.redHistogram = new int[256];
    this.greenHistogram = new int[256];
    this.blueHistogram = new int[256];
  }

  /**
   * Sets the histogram values to be displayed by this panel.
   * Paints the panel.
   *
   * @param histogramValues the histogram values to be displayed by this panel.
   */
  public void setHistogram(int[][] histogramValues) {
    this.redHistogram = histogramValues[0];
    this.greenHistogram = histogramValues[1];
    this.blueHistogram = histogramValues[2];
    this.averageHistogram = histogramValues[3];
    revalidate();
    repaint();
  }

  /**
   * Paints the histogram.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawHistogram(g);
  }

  /**
   * Draws the histogram.
   * Goes ahead to call drawHistogramComponent on each of the histogram components.
   *
   * @param g the <code>Graphics</code> object.
   */
  private void drawHistogram(Graphics g) {
    // Get the width and height of the panel
    int barWidth = this.getWidth() / 256;
    int maxHeight = this.getHeight() - 5;

    drawHistogramComponent(g, redHistogram, barWidth, maxHeight, Color.RED);
    drawHistogramComponent(g, greenHistogram, barWidth, maxHeight, Color.GREEN);
    drawHistogramComponent(g, blueHistogram, barWidth, maxHeight, Color.BLUE);
    drawHistogramComponent(g, averageHistogram, barWidth, maxHeight, Color.BLACK);
  }

  /**
   * Draws a histogram component.
   *
   * @param g the graphics object.
   * @param histogram the histogram values.
   * @param barWidth the width of the bar.
   * @param maxPossibleHt the maximum height of the bar.
   * @param color the color of the bar.
   */
  private void drawHistogramComponent(
          Graphics g, int[] histogram, int barWidth, int maxPossibleHt, Color color) {

    // Find the maximum value in the histogram
    int maxHistogramValue = 0;
    for (int value : histogram) {
      maxHistogramValue = Math.max(maxHistogramValue, value);
    }

    // Calculate the scaling factor to fit the histogram within the maxPossibleHt
    double scale = (double) maxPossibleHt / maxHistogramValue;

    // Iterate over all the 256 values
    for (int i = 0; i < histogram.length; i++) {
      // Scale the histogram value to fit within maxPossibleHt
      int scaledValue = (int) (histogram[i] * scale);

      // Calculate the alpha value based on the scaled value
      int alpha = (int) ((scaledValue / (double) maxPossibleHt) * 255);

      // Create a transparent version of the color
      Color transparentColor = new Color(color.getRed(),
              color.getGreen(), color.getBlue(), alpha);
      // Set the color with transparency
      g.setColor(transparentColor);
      // Draw the bar
      g.fillRect(i * barWidth, maxPossibleHt - scaledValue, barWidth, scaledValue);
    }
  }

}
