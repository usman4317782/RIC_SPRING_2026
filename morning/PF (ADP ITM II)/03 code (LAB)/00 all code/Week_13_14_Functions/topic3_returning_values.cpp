// Week 13-14: Functions
// Topic 3: Returning a Value from a Function
// Shows how functions return values (double or bool) back to the calling statement.

#include <iostream>
using namespace std;

// Function Prototypes
// Int return: calculates area of a rectangle
double calculateArea(double length, double width);

// Bool return: checks if number is even
bool isEven(int num);

int main() {
    double rectLength = 5.5;
    double rectWidth = 4.0;

    // 1. Calling a value-returning function and saving its result
    double area = calculateArea(rectLength, rectWidth);
    cout << "Area of " << rectLength << "x" << rectWidth << " rectangle is: " << area << endl << endl;

    // 2. Calling a boolean function in a conditional check
    int testNum = 17;
    cout << "Checking if " << testNum << " is even..." << endl;
    
    if (isEven(testNum)) {
        cout << testNum << " is Even!" << endl;
    } 
    else {
        cout << testNum << " is Odd!" << endl;
    }

    return 0;
}

// Function Definitions
double calculateArea(double length, double width) {
    double result = length * width;
    return result; // Sends the value of result back to the caller
}

bool isEven(int num) {
    // If division remainder is 0, return true; else return false.
    if (num % 2 == 0) {
        return true;
    } 
    else {
        return false;
    }
}
