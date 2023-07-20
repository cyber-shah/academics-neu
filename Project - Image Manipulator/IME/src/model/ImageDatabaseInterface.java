package model;

import model.Image.Image;
import model.Image.ImageState;

public interface ImageDatabaseInterface {
  void addImage(String name, ImageState image);
  ImageState getImage(String name);
}
