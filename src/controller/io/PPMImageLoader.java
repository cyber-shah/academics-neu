package controller.io;

import model.image.CImage;
import model.image.CPixel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents an ImageLoader.
 * It takes in the file path of a PPM file and returns an Image object.
 */
public class PPMImageLoader implements ImageLoaderInterface {

  /**
   * Loads a PPM file and returns an Image object.
   *
   * @param filePath String value of the file path.
   * @return Image object.
   *
   * @throws FileNotFoundException if the file is not found.
   * @throws NoSuchElementException if the file is not a valid PPM file.
   * @throws IllegalArgumentException if the file is not a 'P3' PPM file.
   */
  public CImage load(String filePath) throws FileNotFoundException {

    try (Scanner scanner = new Scanner(new FileInputStream(filePath))) {

      // 0. read the file into a string
      // and convert it to a format that we can use
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (scanner.hasNextLine()) {
        String s = scanner.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }
      //now set up the scanner to read from the string we just built
      Scanner newScanner = new Scanner(builder.toString());

      // 1. get the image parameters, and check if they are valid
      int[] imageParameters = readImageParameters(newScanner);
      int width;
      int height;
      int maxValue;
      width = imageParameters[0];
      height = imageParameters[1];
      maxValue = imageParameters[2];

      // 2. if valid parameters, create the image
      CImage customImage = new CImage(width, height, maxValue);

      // 3. read the pixels
      try {
        for (int i = 0; i < customImage.getWidth(); i++) {
          for (int j = 0; j < customImage.getHeight(); j++) {
            int red = newScanner.nextInt();
            int green = newScanner.nextInt();
            int blue = newScanner.nextInt();
            customImage.setPixel(i, j, new CPixel(red, green, blue, maxValue));
          }
        }
      }
      catch (NoSuchElementException e) {
        throw new NoSuchElementException("From ImageLoader: File " + filePath
                + " is not a complete PPM file!");
      }
      return customImage;
    }
    catch (FileNotFoundException e)  {
      throw new IllegalArgumentException("From ImageLoader: File " + filePath
              + " not found!");
    }
  }

  /**
   * Reads the image parameters from the PPM file.
   *
   * @param scanner Scanner object.
   * @return int[] array of the image parameters.
   *
   * @throws NoSuchElementException if the file does not have enough lines to read.
   * @throws IllegalArgumentException if the file is not a 'P3' PPM file.
   * @throws IllegalArgumentException if the file does not have valid ints as parameters.
   */
  private int[] readImageParameters(Scanner scanner) {
    int[] params = new int[3];
    String line;

    try {
      // Read the first two lines
      for (int lineCount = 0; lineCount < 3; lineCount++) {
        line = scanner.nextLine();
        // Ignore comments
        if (line.charAt(0) == '#') {
          lineCount--;
          continue;
        }

        // Get the type of the image (line 1)
        if (lineCount == 0) {
          if (!line.equals("P3")) {
            throw new IllegalArgumentException("Invalid PPM file: "
                    + "plain RAW file should begin with P3");
          }
        }
        // Get the width and height of the image (line 2)
        else if (lineCount == 1) {
          String[] dimensions = line.split(" ");
          params[0] = Integer.parseInt(dimensions[0]);
          params[1] = Integer.parseInt(dimensions[1]);
        }
        // Get the max value (line 3)
        else if (lineCount == 2) {
          params[2] = Integer.parseInt(line);
        }
      }
      return params;
    }
    catch (NoSuchElementException e) {
      throw new NoSuchElementException("Invalid PPM file: not enough lines to read");
    }
  }

}
