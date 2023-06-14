cmake_minimum_required(VERSION 3.25)
project(HW5 C)

set(CMAKE_C_STANDARD 11)

include_directories(.)

add_executable(HW5
        insertion_test.c
        merge_test.c
        selection_test.c
        sort_helper.h
        sorts.h
        tester.c)
