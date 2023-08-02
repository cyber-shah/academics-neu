package view.gui;

import javax.swing.*;
import java.awt.*;

public class HistogramPanel extends JScrollPane {
  private int[] redHistogram;
  private int[] greenHistogram;
  private int[] blueHistogram;
  private int[] averageHistogram;

  /**
   * Constructs a new HistogramPanel object.
   */
  public HistogramPanel() {
    super();
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
    // iterate over all the 256 values
    for (int i = 0; i < histogram.length; i++) {
      g.setColor(color);
      // Draw the bar
      g.fillRect(i * barWidth, maxHeight - histogram[i], barWidth, histogram[i]);
    }
  }
}
