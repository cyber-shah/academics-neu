package model.Operations;

import model.Image.Image;
import model.Image.ImageState;

public interface Operation {

  Image applyOperation(ImageState image, int... args);
}
