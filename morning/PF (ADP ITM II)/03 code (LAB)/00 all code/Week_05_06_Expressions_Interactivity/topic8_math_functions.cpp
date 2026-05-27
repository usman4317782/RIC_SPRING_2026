// Week 5-6: Expression and Interactivity
// Topic 8: Mathematical Library Functions
// Demonstrates using the <cmath> library for complex mathematical functions like power, square root, etc.

#include <iostream>
#include <cmath> // Required for mathematical functions
#include <iomanip>
using namespace std;

int main() {
    double base, exponent;
    
    cout << "--- Power function (pow) ---" << endl;
    cout << "Enter a base number: ";
    cin >> base;
    cout << "Enter exponent: ";
    cin >> exponent;
    
    // pow(x, y) returns x raised to the power of y
    double powerResult = pow(base, exponent);
    cout << base << "^" << exponent << " = " << powerResult << endl << endl;

    cout << "--- Square Root (sqrt) and Absolute Value (abs) ---" << endl;
    double areaValue;
    cout << "Enter the area of a square to find its side length: ";
    cin >> areaValue;
    
    double side = sqrt(areaValue); // Returns the square root of a double
    cout << "Side length: " << side << endl;

    double negativeNum = -45.67;
    // abs() returns the absolute (positive) representation of a number.
    // In C++, std::abs is defined in cmath for floats/doubles, and cstdlib for ints.
    cout << "Absolute value of " << negativeNum << " is " << abs(negativeNum) << endl << endl;

    cout << "--- Rounding Functions ---" << endl;
    double decimalNum = 5.67;
    cout << "Number: " << decimalNum << endl;
    cout << "ceil()  (rounds up)   : " << ceil(decimalNum) << endl; // 6
    cout << "floor() (rounds down) : " << floor(decimalNum) << endl; // 5
    cout << "round() (closest int) : " << round(decimalNum) << endl; // 6

    return 0;
}
