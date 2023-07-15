cmake_minimum_required(VERSION 3.0)
project(homework_02_cyber_shah)

# Set C standard
set(CMAKE_C_STANDARD 11)

# Add executables
add_executable(homework_02_cyber_shah main.c)
add_executable(tests tests.c)
#


# Include directories
target_include_directories(homework_02_cyber_shah PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
target_include_directories(tests PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

# Set compilation flags
target_compile_options(homework_02_cyber_shah PRIVATE -Wall -Wextra -pedantic)
target_compile_options(tests PRIVATE -Wall -Wextra -pedantic)