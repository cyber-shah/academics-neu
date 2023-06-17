cmake_minimum_required(VERSION 3.25)
project(homework_03_cyber_shah C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(homework_03_cyber_shah
        mystack.h
        test_stack.c)
