```plantuml
classDiagram
direction BT
class BrightenCommandStrategy {
+ BrightenCommandStrategy()
+ run(String[], ImageDatabaseInterface) void
- validateArguments(String[]) String[]
  }
  class BrightenOperation {
+ BrightenOperation(ImageState, int)
- int value
- ImageState sourceImage
+ applyOperation() ImageState
  }
  class ColorComponentOperation {
+ ColorComponentOperation(ImageState, String)
- String component
- ImageState sourceImage
+ applyOperation() ImageState
  }
  class CommandStrategyInterface {
  <<Interface>>
+ run(String[], ImageDatabaseInterface) void
  }
  class ComponentCommandStrategy {
+ ComponentCommandStrategy()
+ run(String[], ImageDatabaseInterface) void
- validateArguments(String[]) String[]
  }
  class ControllerImplementation {
+ ControllerImplementation(ImageDatabaseInterface, ViewImplementation, Readable)
- Map~String, CommandStrategyInterface~ commandRegistry
- ViewImplementation view
- ImageDatabaseInterface model
- Readable inReadable
- listAllCommands() String
+ go() void
- registerCommands() void
  }
  class ControllerInterface {
  <<Interface>>
+ go() void
  }
  class IME {
+ IME()
+ main(String[]) void
  }
  class IPixel {
  <<Interface>>
+ setGreen(int) void
+ setRed(int) void
+ setBlue(int) void
  }
  class IPixelState {
  <<Interface>>
+ getRed() int
+ getBlue() int
+ getMaxValue() int
+ getGreen() int
  }
  class Image {
+ Image(int, int, int)
- IPixel[][] pixelsList
- int width
- int height
- int maxValue
+ getWidth() int
+ getPixel(int, int) Pixel
+ setPixel(int, int, Pixel) void
+ getMaxValue() int
+ getPixelsList() IPixel[][]
+ getHeight() int
  }
  class ImageDatabase {
+ ImageDatabase()
- Map~String, ImageState~ imagesMap
+ addImage(String, ImageState) void
+ getImage(String) ImageState
+ getAllImageNames() String
+ getNumImages() int
+ removeImage(String) void
  }
  class ImageDatabaseInterface {
  <<Interface>>
+ addImage(String, ImageState) void
+ getImage(String) ImageState
+ getAllImageNames() String
+ getNumImages() int
+ removeImage(String) void
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
+ getWidth() int
+ getHeight() int
+ getPixelsList() IPixel[][]
+ getPixel(int, int) Pixel
+ getMaxValue() int
  }
  class IntensityCommandStrategy {
+ IntensityCommandStrategy()
+ run(String[], ImageDatabaseInterface) void
- validateArguments(String[]) String[]
  }
  class IntensityOperation {
+ IntensityOperation(ImageState)
- ImageState sourceImage
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
- ImageState sourceImage
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
+ Pixel(int, int, int, int)
+ Pixel(int, int, int)
- int Green
- int Blue
- int Red
- int maxValue
+ getBlue() int
+ setGreen(int) void
+ toString() String
+ getRed() int
+ setAll(int, int, int) void
+ setBlue(int) void
+ getMaxValue() int
+ setRed(int) void
+ getGreen() int
+ setChannel(int) void
  }
  class SaveCommandStrategy {
+ SaveCommandStrategy()
- validateArguments(String[]) String[]
+ run(String[], ImageDatabaseInterface) void
  }
  class ValueCommandStrategy {
+ ValueCommandStrategy()
+ run(String[], ImageDatabaseInterface) void
- validateArguments(String[]) String[]
  }
  class ValueComponentOperation {
+ ValueComponentOperation(ImageState)
- ImageState sourceImage
+ applyOperation() ImageState
  }
  class ViewImplementation {
+ ViewImplementation(Appendable)
- Appendable outAppendable
+ renderMessage(String) void
  }
  class ViewInterface {
  <<Interface>>
+ renderMessage(String) void
  }

BrightenCommandStrategy  ..>  CommandStrategyInterface
BrightenOperation  ..>  OperationInterface
ColorComponentOperation  ..>  OperationInterface
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
@enduml
```