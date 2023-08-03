# GRIME - Graphical Image Manipulation and Enhancement

## Introduction
Grime is an image processing application with a graphical user interface (GUI) that allows users to interactively load, process, and save images. It also supports batch scripting from the command line for advanced users. The application is written in Java and uses the Swing framework for the GUI. The application supports the following image processing operations:


## Features
Supported image formats: PPM, PNG, JPG, JPEG, BMP

- Load images from file system (see supported image formats).
- Save processed images to file system (see supported image formats).
- Grayscale conversion with options for luma, intensity, value, red, green, or blue components.
- Brightness adjustment for image enhancement.
- Filter operations for blurring and sharpening images.
- Color operations for greyscale and sepia effects.
- Histogram visualization for red, green, blue, and intensity components in real time.
- Scripting mode for automated image processing.
- Command-line arguments support for batch processing.

## How to Use

Refer to the [USEME.md](USEME.md) file for a detailed guide on how to use the Grime GUI to perform various image processing operations.


## Design Changes

### Additions

1. To create a GUI, I created a view class which implements the `ViewGUIInterface`. The `ViewGUIInterface` contains methods for displaying messages to the user. The `ViewGUI` class implements the interface and uses Java Swing to create a GUI. 

2. Added a `CustomEventsListener` interface which is implemented by the `ControllerGUI` class. The `CustomEventsListener` is a subscriber to all the events emmited by the `ViewGUI` class. This follows a emit-subscribe pattern.

3. Added a `CustomEvents` class which is emitted by the `ViewGUI` class in case any action is performed. The `ControllerGUI` class listens to these events and calls the appropriate methods in the model and view.
   > The reason why we created a `CustomEvents` class instead of using the `ActionEvent` class is because we wanted the controller to be completely oblivious of the implementation fo `ViewGUI`. This means that if we were to use `ControllerGUI` with some other framework and some other class, we would not have to change the `ControllerGUI` class. We would only have to change the `ViewGUI` class to emit the appropriate events.



### Modifications

1. Modified the `GrimeMain` class to allow the user to specify the mode of operation (GUI or command line) using command line arguments. If the user does not specify any arguments, the program runs in GUI mode. If the user specifies the `-text` or `-file {file-path}` argument, the program runs in scripting mode or batch mode respectively.

2. Modified the `BufferedImageWrapper` and `PPMImage` classes to add a `getHistogramValues` method which returns the histogram values for the image. The `getHistogramValues` method returns a 2D array of integers, where the first dimension represents the channel (0 for red, 1 for green, 2 for blue), and the second dimension represents the intensity value (0-255).
   > This modification was required to tightly couple the histogram with the image. The histogram is a property of the image, and hence it makes sense to store the histogram values in the image class itself. This also makes it easier to render the histogram in the GUI, as the histogram values can be accessed directly from the image object.

## Citations
The image shown in the GUI is from [Washington University in St.Louis : ALUMNI AND FRIENDS](https://alumni.wustl.edu/networks/boston/). The image is used for educational purposes only and is not intended for commercial use.