package controller;

import model.Image;
import model.ImageOperations;

import java.io.FileNotFoundException;

public class ImageProcessingControllerImpl implements ImageProcessingController {

  private Image image;
  private ImageOperations imageOperations;
  private ImageLoader imageLoader;
  private CommandParser commandParser;

  public ImageProcessingControllerImpl() throws FileNotFoundException {
  }

  // 1. ImageLoader Load the image

  // 2. CommandParser to parse users commands and get the command

  // 3. Pass the command to the ImageOperations

  // 4. ImageWriter to write the image to the disk
}
