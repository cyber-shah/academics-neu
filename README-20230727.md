## Complete Features

- Support for Conventional File Formats
    - The application now supports importing and exporting images in popular formats, including JPEG, PNG, and BMP, in addition to the existing support for ASCII PPM files. This makes it easier for users to work with images in a variety of formats, regardless of their preferred file format.

- Filtering Operations
  - The application provides basic filtering operations using a 2D kernel. Users can apply image blurring and sharpening to enhance their images. These filtering operations can be used to improve the visual quality of images, or to achieve specific effects, such as a vintage look.

- Color Transformations (Bonus)
  - As a bonus feature, the application supports color transformations. Users can convert images to grayscale and apply a sepia tone to achieve a vintage effect. These color transformations can be used to change the overall look and feel of an image, or to highlight specific features.

- Command-Line and Script Support
  - Users can interact with the application through the command-line interface (CLI) and execute image processing operations by providing script commands as input. This allows users to automate image processing tasks and to integrate the application with other software.

- Retained Support for PPM File Format
  - The application still retains support for the PPM file format, allowing users to work with both conventional formats and PPM interchangeably. This ensures that users can continue to use the application with their existing image files.

Please see the [readme](../README.md) for supported commands and their usage.

## Design changes and justifications

The design of the application has been updated to support the new features. The following changes have been made to the design:
- Factory Methods introduced to create relevant command pattern strategies.
    - for example, `FilterCommandStrategyFactory` creates `FilterOperation` transformation objects.
    - This allows for easy extensibility of the program.
    - New operations can be added by creating a new class that implements the `OperationInterface`. And then adding a new factory method to the relevant factory class.
  > This follows the Open-Closed Principle and Single Responsibility Principle.
  > This means that whenever we would need to add more operations we can simply add more classes which implement the `OperationInterface` and then add them into the factory class to be used.
  > Let's say we need to add more filters, we simply create the `ABCFilter` class and then add it to the `FilterFactory` class.

- Abstract classes for filters and color transformations.
    - `AbstractFilter` and `AbstractColor` are abstract classes that implement the `OperationInterface`.
    - This reduced the amount of code duplication and made the code more readable.
    - Whenever we need to add a different convolution kernel, we only change the kernel the relevant class or add a new class.
    - The application logic in both the abstract classes can be closed for modification but open for extension.

- Introduction of the `BufferedImageWrapper` class.
    - This class is a wrapper around the `BufferedImage` class and implements the `CustomImageState` interface.
    - This means that the `BufferedImageWrapper` class can be used in place of the `CustomImage` class.
    - Hence no code changes were required in the any other classes to support the new features.
