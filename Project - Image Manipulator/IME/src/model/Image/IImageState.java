package model.Image;

public interface IImageState {

    int getWidth();

    int getHeight();

    IPixel[][] getPixelsList();

    Pixel getPixel(int x, int y);

    void setPixel(int x, int y, Pixel pixel);
}
