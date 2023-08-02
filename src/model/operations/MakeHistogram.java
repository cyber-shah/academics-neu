package model.operations;

import model.ImageDatabaseInterface;
import model.image.CustomImageState;

/**
 * This class represents the operation to make a histogram of an image.
 * It implements the OperationInterface.
 */
public class MakeHistogram implements OperationInterface {
  private CustomImageState image;
  private ImageDatabaseInterface histogramDatabase;

  /**
   * This constructor creates a new MakeHistogram object.
   *
   * @param image the image to be made into a histogram.
   */
  public MakeHistogram(CustomImageState image, ImageDatabaseInterface histogramDatabase) {
    this.image = image;
    this.histogramDatabase = histogramDatabase;
  }

  /**
   * 1. Create arrays for each color channel. - 256
   * 2. Iterate through the image and increment the count of each color channel.
   * 3. Create a new image with the histogram.
   */
  @Override
  public CustomImageState applyOperation() {
    CustomImageState histogram = new CustomImageState ();
    histogramDatabase.addImage(histogram);
  }
}
