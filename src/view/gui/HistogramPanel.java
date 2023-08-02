package view.gui;

import javax.swing.*;
import java.awt.*;

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

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawHistogram(g);
  }

  private void drawHistogram(Graphics g) {
    // Get the width and height of the panel
    int barWidth = getWidth() / 256;
    int maxHeight = getHeight() - 15;

    drawHistogramComponent(g, redHistogram, barWidth, maxHeight, Color.RED);
    drawHistogramComponent(g, greenHistogram, barWidth, maxHeight, Color.GREEN);
    drawHistogramComponent(g, blueHistogram, barWidth, maxHeight, Color.BLUE);
    drawHistogramComponent(g, averageHistogram, barWidth, maxHeight, Color.BLACK);
  }

  private void drawHistogramComponent(
          Graphics g, int[] histogram, int barWidth, int maxHeight, Color color) {
    // Find the maximum value in the histogram
    int maxHistogramValue = 0;
    for (int value : histogram) {
      maxHistogramValue = Math.max(maxHistogramValue, value);
    }

    // Calculate the scaling factor to fit the histogram within the maxHeight
    double scale = (double) maxHeight / maxHistogramValue;

    // iterate over all the 256 values
    for (int i = 0; i < histogram.length; i++) {
      g.setColor(color);
      // Scale the histogram value to fit within maxHeight
      int scaledValue = (int) (histogram[i] * scale);
      // Draw the bar
      g.fillRect(i * barWidth, maxHeight - scaledValue, barWidth, scaledValue);
    }
  }
}
