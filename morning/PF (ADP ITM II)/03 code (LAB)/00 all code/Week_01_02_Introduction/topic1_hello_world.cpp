// Week 1-2: Introduction to Computers and Programming
// Topic 1: Hello World
// This is the absolute first program a student learns in C++.
// It demonstrates outputting text to the screen and the basic structure of a C++ program.

#include <iostream> // Preprocessor directive to include input/output stream library

// Every C++ program starts execution from the main function.
int main() {
    // std::cout represents the standard output stream (usually the console screen).
    // << is the stream insertion operator, used to send data to the output stream.
    // std::endl moves the cursor to the next line (like pressing Enter) and flushes the buffer.
    std::cout << "Hello, World! Welcome to Programming Fundamentals." << std::endl;

    std::cout << "This is your very first C++ program." << std::endl;

    // returning 0 to the operating system signals that the program executed successfully without errors.
    return 0;
}
