package controller;

import model.Image;
import model.Pixel;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * This class represents an ImageLoader.
 * It creates an Image object from a given File.
 */
public class ImageLoader {

  private final File file;
//  private BufferedReader reader;

  /**
   * Constructor for ImageLoader.
   * It takes a String filePath and creates a File object.
   * If the file does not exist, it throws an IllegalArgumentException.
   *
   * @param filePath String value of filePath.
   * @throws IllegalArgumentException if the file does not exist.
   */
  public ImageLoader(String filePath) throws IllegalArgumentException {
    this.file = new File(filePath);
    if (!file.exists()) {
      throw new IllegalArgumentException("File not found");
    }
  }

  /**
   * This method loads the image from the file.
   * It creates an Image object from the file.
   *
   * @return Image object.
   * @throws FileNotFoundException if the file is not found.
   */
  public Image load() throws FileNotFoundException {
    Image image = null;

    // 1. create a scanner
    Scanner scanner = new Scanner(this.file);
    String line; int width; int height;
    int lineCount = 0;

    // 2. While the scanner has a next line
    while (scanner.hasNextLine()) {
      line = scanner.nextLine();

      // 0. Ignore comments!
      if (line.startsWith("#")) {
        continue;
      }

      // 3. Get the type of the image
      if (lineCount == 0) {
        String type = line;
      }

      // 4. Get the width and height of the image
      //    create an image object
      else if (lineCount == 1) {
        String[] dimensions = line.split(" ");
        width = Integer.parseInt(dimensions[0]);
        height = Integer.parseInt(dimensions[1]);
        try {
          image = new Image(width, height);
        }
        catch (NullPointerException e) {
          e.getStackTrace();
        }
      }

      // 5. once that is done
      //    get the pixels of the image
      else {
        try {
          int red = Integer.parseInt(line);
          int green = Integer.parseInt(scanner.nextLine());
          int blue = Integer.parseInt(scanner.nextLine());
          Pixel pixel = new Pixel(red, green, blue);
          image.setPixel(lineCount - 2, pixel);
        }
        catch (NoSuchElementException e) {
          e.getStackTrace();
          // end of file
          break;
        }
        catch (NumberFormatException f) {
          f.getStackTrace();
          break;
        }
      }
      // 6. increment the line count
      lineCount++;
    }
    return image;
  }
}

/*
    String line;

    int lineCount = 0;
    int width = 0;
    int height = 0;

    int pixelLocation = 0;
    while ((line = this.reader.readLine()) != null) {
      // 0. Ignore comments!
      if (line.startsWith("#")) {
        continue;
      }

      // 1. Split the data into lines
      if (lineCount == 0) {
        // 2. Get the type of the image
        String type = line;
      }

      else if (lineCount == 1) {
        // 3. Get the width and height of the image
        String[] dimensions = line.split(" ");
        width = Integer.parseInt(dimensions[0]);
        height = Integer.parseInt(dimensions[1]);
        image = new Image(width, height);
      }

      else {
        // 4. Get the pixels of the image
        int red = Integer.parseInt(line[scanner.nextLine()]);
        int green = Integer.parseInt(line[scanner.nextLine()]);
        int blue = Integer.parseInt(line[scanner.nextLine()]);
        image.setPixel(pixelLocation, new model.Pixel(red, green, blue));
        pixelLocation++;
      }
    }
    return image;
  }
}
*/

/*
    // 1. Split the data into lines
    String[] lines = this.filePath.split("\n");

    // 2. Get the type of the image
    String type = lines[0];

    // 3. Get the width and height of the image
    String[] dimensions = lines[1].split(" ");
    int width = Integer.parseInt(dimensions[0]);
    int height = Integer.parseInt(dimensions[1]);
    Image image = new Image(width, height);

    int pixelLocation = 0;
    // 4. Get the pixels of the image
    for (int i = 2; i < lines.length; i += 3) {
      // 4. ignore all the comments
      if (lines[i].charAt(0) == '#') {
        continue;
      }
      else {
        int red = Integer.parseInt(lines[i]);
        int green = Integer.parseInt(lines[i + 1]);
        int blue = Integer.parseInt(lines[i + 2]);
        image.setPixel(pixelLocation, new model.Pixel(red, green, blue));
        pixelLocation++;
      }
    }
    return image;
  }
}*/
