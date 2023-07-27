# IME - Image Manipulation Program

## Description

IME is a command-line custom image manipulation program that allows you to load, save, and modify images. Below are the supported commands:

- `load {format} {image-path} {custom-Image-name}`
  Loads the image from the specified path and stores it with the given name.
   1. `ppm`: PPM format
   2. `jpg`: JPG format
   3. `png`: PNG format
   4. `bmp`: BMP format
- `save {format} {output-file-path} {customImage-name}`
  Saves the image with the specified name to the provided path in the given format.
   1. `ppm`: PPM format
   2. `jpg`: JPG format
   3. `png`: PNG format
   4. `bmp`: BMP format
- `greyscale {component} {customImage-name} {new-customImage-name}`
  Converts the custom image to greyscale using the chosen component:
   1. `red`: Red component
   2. `green`: Green component
   3. `blue`: Blue component
   4. `luma`: Luma component
   5. `value`: Value component
   6. `intensity`: Intensity component

- `brighten {value} {image-name} {new-customImage-name}`
  Brightens or darkens the custom image by the specified value.

- `filter {filter-type} {customImage-name} {new-customImage-name}`
  Applies the specified filter to the custom image:
   1. `blur`: Blurs the image
   2. `sharpen`: Sharpens the image

- `color {filter-type} {customImage-name} {new-customImage-name}`
  Applies the specified color filter to the custom image:
   1. `sepia`: Sepia filter
   2. `greyscale`: Greyscale filter

- `exit`
  Exits the program.

- `list-all-commands`
  Displays all available commands.

- `list-all-images`
  Displays a list of all loaded images.

## Design
The design follows Model View Controller (MVC) architecture. The model is the ImageDatabase, which stores all the images. The view is the ViewImplementation, which is responsible for displaying messages to the user. The controller is the ControllerImplementation, which is responsible for parsing user input and calling the appropriate methods in the model and view. The controller also contains the main method, which is responsible for initializing the model, view, and controller, and starting the program.

>The system follows a Command pattern for executing customImage processing commands.
>It uses the Strategy pattern to execute the operations.

The usage of command pattern and strategy pattern allows for easy extensibility of the program. 
New commands can be added by creating a new class that implements the CommandStrategyInterface. 
New operations can be added by creating a new class that implements the OperationInterface. 
These new command and operation can then be added to the controller and used in the program.

The design can be seen in the following UML diagram:
```mermaid
classDiagram
direction BT
class BrightenCommandStrategy {
  + BrightenCommandStrategy() 
  + run(String[], ImageDatabaseInterface) void
  - validateArguments(String[]) String[]
}
class BrightenOperation {
  + BrightenOperation(ImageState, int) 
  + applyOperation() ImageState
}
class ColorComponentOperation {
  + ColorComponentOperation(ImageState, String) 
  + applyOperation() ImageState
}
class CommandStrategyInterface {
<<Interface>>
  + run(String[], ImageDatabaseInterface) void
}
class CommandsManager {
  + CommandsManager() 
  + registerAllCommands() void
  + getCommandStrategy(String) CommandStrategyInterface
  + registerCommand(String, CommandStrategyInterface) void
  + listAllCommands() String
}
class CommandsManagerInterface {
<<Interface>>
  + getCommandStrategy(String) CommandStrategyInterface
  + registerCommand(String, CommandStrategyInterface) void
  + listAllCommands() String
  + registerAllCommands() void
}
class ComponentCommandStrategy {
  + ComponentCommandStrategy() 
  + run(String[], ImageDatabaseInterface) void
  - validateArguments(String[]) String[]
}
class ControllerImplementation {
  + ControllerImplementation(ImageDatabaseInterface, ViewInterface, Readable, CommandsManagerInterface) 
  + runProgram() void
}
class ControllerInterface {
<<Interface>>
  + runProgram() void
}
class IME {
  + IME() 
  + main(String[]) void
}
class IPixel {
<<Interface>>
   int red
   int green
   int blue
}
class IPixelState {
<<Interface>>
   int red
   int maxValue
   int green
   int blue
}
class Image {
  + Image(int, int, int) 
  - int width
  - IPixel[][] pixelsList
  - int height
  - int maxValue
  + getPixel(int, int) Pixel
  + setPixel(int, int, Pixel) void
   int height
   int width
   IPixel[][] pixelsList
   int maxValue
}
class ImageDatabase {
  + ImageDatabase() 
  + removeImage(String) void
  + addImage(String, ImageState) void
  + getImage(String) ImageState
   String allImageNames
   int numImages
}
class ImageDatabaseInterface {
<<Interface>>
  + addImage(String, ImageState) void
  + getImage(String) ImageState
  + removeImage(String) void
   String allImageNames
   int numImages
}
class ImageLoaderInterface {
<<Interface>>
  + load(String) Image
}
class ImageSaverInterface {
<<Interface>>
  + save(ImageState, String) void
}
class ImageState {
<<Interface>>
  + getPixel(int, int) Pixel
   int height
   int width
   IPixel[][] pixelsList
   int maxValue
}
class IntensityCommandStrategy {
  + IntensityCommandStrategy() 
  - validateArguments(String[]) String[]
  + run(String[], ImageDatabaseInterface) void
}
class IntensityOperation {
  + IntensityOperation(ImageState) 
  + applyOperation() ImageState
}
class LoadCommandStrategy {
  + LoadCommandStrategy() 
  + run(String[], ImageDatabaseInterface) void
  - validateArguments(String[]) String[]
}
class LumaCommandStrategy {
  + LumaCommandStrategy() 
  + run(String[], ImageDatabaseInterface) void
  - validateArguments(String[]) String[]
}
class LumaOperation {
  + LumaOperation(ImageState) 
  + applyOperation() ImageState
}
class OperationInterface {
<<Interface>>
  + applyOperation() ImageState
}
class PPMImageLoader {
  + PPMImageLoader() 
  + load(String) Image
  - readImageParameters(Scanner) int[]
}
class PPMImageSaver {
  + PPMImageSaver() 
  + save(ImageState, String) void
}
class Pixel {
  + Pixel(int, int, int) 
  + Pixel(int, int, int, int) 
  - int green
  - int maxValue
  - int red
  - int blue
  + setAll(int, int, int) void
  + toString() String
   int red
   int channel
   int maxValue
   int green
   int blue
}
class SaveCommandStrategy {
  + SaveCommandStrategy() 
  + run(String[], ImageDatabaseInterface) void
  - validateArguments(String[]) String[]
}
class ValueCommandStrategy {
  + ValueCommandStrategy() 
  + run(String[], ImageDatabaseInterface) void
  - validateArguments(String[]) String[]
}
class ValueComponentOperation {
  + ValueComponentOperation(ImageState) 
  + applyOperation() ImageState
}
class ViewImplementation {
  + ViewImplementation(Appendable) 
  + renderMessage(String) void
}
class ViewInterface {
<<Interface>>
  + renderMessage(String) void
}

BrightenCommandStrategy  ..>  CommandStrategyInterface 
BrightenOperation  ..>  OperationInterface 
ColorComponentOperation  ..>  OperationInterface 
CommandsManager  ..>  CommandsManagerInterface 
ComponentCommandStrategy  ..>  CommandStrategyInterface 
ControllerImplementation  ..>  ControllerInterface 
IPixel  -->  IPixelState 
Image  ..>  ImageState 
ImageDatabase  ..>  ImageDatabaseInterface 
IntensityCommandStrategy  ..>  CommandStrategyInterface 
IntensityOperation  ..>  OperationInterface 
LoadCommandStrategy  ..>  CommandStrategyInterface 
LumaCommandStrategy  ..>  CommandStrategyInterface 
LumaOperation  ..>  OperationInterface 
PPMImageLoader  ..>  ImageLoaderInterface 
PPMImageSaver  ..>  ImageSaverInterface 
Pixel  ..>  IPixel 
SaveCommandStrategy  ..>  CommandStrategyInterface 
ValueCommandStrategy  ..>  CommandStrategyInterface 
ValueComponentOperation  ..>  OperationInterface 
ViewImplementation  ..>  ViewInterface
```

![Class Diagram](./UML.png)

### Model 
The model follows a Strategy pattern, where the ControllerImplementation class is the context, and the CommandStrategyInterface is the strategy.
The model contains the following packages:
1. Image package - Contains mainly two components: Image and Pixel. 
   * The Image class contains a 2D array of IPixel objects, which represent the pixels in the customImage. 
   The Image class also contains the width, height, and maxValue of the customImage. 
   The Image class also contains methods for getting and setting pixels in the customImage.  
   * It also contains the IPixel interface, which represents a pixel. 
   It contains the red, green, and blue values of the pixel. It also contains the IPixelState interface, which represents a pixel that cannot be modified. 
   It contains the red, green, and blue values of the pixel, as well as the maxValue of the customImage.
   > This follows the Liskov Substitution Principle because Image can be substituted for ImageState to make it unmodifiable.
Similarly, IPixel can be substituted for IPixelState to make it unmodifiable, wherever needed.
2. Operations package - contains the OperationInterface interface, which represents an operation on an customImage. 
It contains the applyOperation method, which applies the operation to the customImage and returns the resulting customImage. 
It also contains the IntensityOperation, BrightenOperation, LumaOperation, and ValueComponentOperation classes, which implement the OperationInterface interface. 
These classes represent the intensity, brighten, luma, and value operations, respectively.
    > This follows the Open-Closed Principle because new operations can be added by creating a new class that implements the OperationInterface interface.
3. ImageDatabase package - contains the ImageDatabase class, which implements the ImageDatabaseInterface interface.
It contains a map of customImage names to ImageState objects. 
It also contains the getImage, addImage, and removeImage methods, which get, add, and remove images from the database, respectively. 

### View
The view contains a ViewInterface interface and a ViewImplementation class. The ViewInterface interface contains a renderMessage function which renders Strings to the user. This method is used to render errors and sucessful command messages.

### Controller
The controller is primarily responsible for the following things:
1. Running the program
2. Parsing command line arguments
3. Calling the appropriate methods in the model
4. Calling the appropriate methods in the view
5. Catching exceptions and rendering error messages to the user.

The ControllerImplementation class contains a go method, which runs the program. The ControllerImplementation is responsible for parsing the command line arguments and calling the appropriate methods in the model. The ControllerInterface interface contains a go method, which runs the program. It also contains a listAllCommands method, which returns a string representation of all the commands in the program. 

Broadly there are two main packages in the controller:
1. CommandStrategy package - contains the CommandStrategyInterface interface, which represents a command strategy.
   * It contains all the methods that are accepted as command operations. These classes act as a intermediate between the controller and the model.
   They are responsible to parse the commands and pass them into the MODEL operations in whatever format needed.
   > This follows the Single Responsibility Principle as each class is responsible for only one type of command.
2. IO package - contains the two interfaces - ImageLoaderInterface and ImageSaverInterface.
   * The ImageLoaderInterface is then implemented by PPMImageLoader class which is responsible for loading the customImage from a PPM file.
   * The ImageSaverInterface is then implemented by PPMImageSaver class which is responsible for saving the customImage to a PPM file.
   > This follows the open closed principle as the controller is open for extension but closed for modification.
   > Which means that whenever we would need to add more customImage formats we can simply add more classes which implement the ImageLoaderInterface and ImageSaverInterface 
   > and the existing code would not need to be modified.
3. CommandManager package - this is responsible for managing the supported commands and their corresponding operations.
   * It contains the CommandManager class which is responsible for registering the commands and their corresponding operations.
   * It also contains the CommandManagerInterface which is responsible for registering the commands and their corresponding operations.
   > This follows the open closed principle as the controller is open for extension but closed for modification.
   > Which means that whenever we would need to add more commands:
   > 1. we create a class which implements the CommandStrategyInterface
   > 2. we create a class that implements the OperationInterface
   > 3. we register the command and operation in the CommandManager class

A sample run can be found in this file: [Sample-Output](SampleOutput.txt)

# Citations
1. [StackOverflow](https://stackoverflow.com/questions/74620396/how-to-convert-images-from-the-jpg-format-to-ppmp3) The customImage used for testing purposes is from StackOverflow and can be found here.
2. [GeeksforGeeks](https://www.geeksforgeeks.org/ood-principles-solid/) Principles of Object Oriented Design