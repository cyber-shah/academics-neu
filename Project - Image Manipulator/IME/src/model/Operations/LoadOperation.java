package model.Operations;

import model.Image.Image;
import model.Image.ImageState;
import model.ImageDatabaseInterface;

public class LoadOperation implements OperationInterface {

  private ImageDatabaseInterface imageDatabase;
  private ImageState image;
  private String imageName;

  public LoadOperation(ImageDatabaseInterface imageDatabase, ImageState image, String imageName) {
    this.imageDatabase = imageDatabase;
    this.image = image;
    this.imageName = imageName;
  }
  @Override
  public Image applyOperation() {
    return null;
  }
}
