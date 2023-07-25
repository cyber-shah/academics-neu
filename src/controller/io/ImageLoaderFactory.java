package controller.io;

public class ImageLoaderFactory {
  public static ImageLoaderInterface getImageLoader(String formatString) {
    SupportedFormats format;
    try {
      format = SupportedFormats.valueOf(formatString);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Format not supported!");
    }

    return switch (format) {
      case PPM -> new PPMImageLoader();
      case PNG -> new PNGImageLoader();
      case JPG -> new JPGImageLoader();
      default -> throw new IllegalArgumentException("Format not supported!");
    };
  }

  private enum SupportedFormats {
    PPM, PNG, JPG
  }
}
