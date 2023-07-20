package model;

public interface IImageState {

    int getWidth();

    int getHeight();

    IPixel[][] getPixelsList();

    Pixel getPixel(int x, int y);

}
