GrimeMain is a command-line custom image manipulation program that allows you to load, save, and modify images. Below are the supported commands:

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