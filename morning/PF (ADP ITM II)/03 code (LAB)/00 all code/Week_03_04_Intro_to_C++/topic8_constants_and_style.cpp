// Week 3-4: Introduction to C++
// Topic 8: Named Constants and Programming Style
// Demonstrates using the 'const' keyword to define read-only variables.
// Explains the importance of consistent naming and spacing conventions.

#include <iostream>
using namespace std;

int main() {
    // 1. Named Constants
    // Prefixing a variable declaration with 'const' makes its value read-only.
    // By convention, constant names are written in UPPERCASE.
    const double PI = 3.14159265;
    const int DAYS_IN_WEEK = 7;

    cout << "Constant PI value: " << PI << endl;
    cout << "Days in a week   : " << DAYS_IN_WEEK << endl;

    // Trying to change a constant variable will cause a compile-time error:
    // PI = 3.14; // ERROR! Compiler will not allow this line.

    // 2. Programming Style Guidelines (Self-study and reflection)
    // Good code is readable. Follow these practices:
    //
    // A. Use Descriptive Variable Names (Identifiers)
    //    - Bad:  int a = 3600; // what is a?
    //    - Good: int secondsInHour = 3600; // clear!
    //
    // B. Indentation
    //    - Always indent statements inside curly braces (typically 4 spaces) to show scope.
    //
    // C. Blank Lines and Spaces
    //    - Use space around operators: `x = y + z;` instead of `x=y+z;`.
    //    - Group related statements together and separate them with blank lines.
    
    double radius = 5.0;
    double area = PI * radius * radius; // Good spacing around operators

    cout << "Area of circle with radius " << radius << " is: " << area << endl;

    return 0;
}
