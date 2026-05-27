// Week 13-14: Functions
// Topic 5: Default Arguments
// Explains how to provide default values in function declarations.
// Caller can choose to omit parameters, using defaults instead.

#include <iostream>
using namespace std;

// Function Prototype with Default Arguments
// Default values must be declared in the prototype (or definition, but not both).
// Default arguments must be placed at the END of the parameter list!
void showVolume(double length, double width = 1.0, double height = 1.0);

int main() {
    cout << "--- Calling with all arguments (5.0, 4.0, 3.0) ---" << endl;
    showVolume(5.0, 4.0, 3.0); // No default values used

    cout << "\n--- Calling with two arguments (5.0, 4.0) ---" << endl;
    showVolume(5.0, 4.0); // Omitted height -> default height = 1.0 is used

    cout << "\n--- Calling with one argument (5.0) ---" << endl;
    showVolume(5.0); // Omitted width & height -> defaults are used

    return 0;
}

// Function Definition
// Do not repeat default values here if they are already in the prototype!
void showVolume(double length, double width, double height) {
    double volume = length * width * height;
    cout << "Dimensions: " << length << " x " << width << " x " << height << endl;
    cout << "Volume    : " << volume << endl;
}
