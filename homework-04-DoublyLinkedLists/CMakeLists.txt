cmake_minimum_required(VERSION 3.25)
project(homework_04_cyber_shah C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(homework_04_cyber_shah
        dll_test.c
        main.c
        my_dll.h)
