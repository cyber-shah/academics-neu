package model.Image;

public interface ImageState {

    int getWidth();

    int getHeight();

    IPixel[][] getPixelsList();

    Pixel getPixel(int x, int y);

    int getMaxValue();
}
