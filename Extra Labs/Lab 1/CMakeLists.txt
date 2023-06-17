cmake_minimum_required(VERSION 3.25)
project(Extra_Labs)

set(CMAKE_CXX_STANDARD 17)

add_library(Extra_Labs library.cpp linkedlist.c linkedlist.h main.c)
