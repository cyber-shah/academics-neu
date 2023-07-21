package controller.IO;

import model.Image.Image;

import java.io.FileNotFoundException;

public interface ImageLoaderInterface {

  public Image load(String filePath) throws FileNotFoundException;
}
