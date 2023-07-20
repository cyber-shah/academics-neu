package model;

import model.Image.Image;

public interface ImageDatabaseInterface {
  void addImage(String name, Image image);
  Image getImage(String name);
}
