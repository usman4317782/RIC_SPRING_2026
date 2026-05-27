// Week 3-4: Introduction to C++
// Topic 7: Arithmetic Operators
// Demonstrates addition (+), subtraction (-), multiplication (*), division (/), and modulus (%).
// Highlights the important concept of integer division vs floating-point division.

#include <iostream>
using namespace std;

int main() {
    int num1 = 17;
    int num2 = 5;

    cout << "num1 = " << num1 << ", num2 = " << num2 << endl << endl;

    // 1. Addition, Subtraction, Multiplication
    cout << "Addition (num1 + num2)       : " << (num1 + num2) << endl;
    cout << "Subtraction (num1 - num2)    : " << (num1 - num2) << endl;
    cout << "Multiplication (num1 * num2) : " << (num1 * num2) << endl;

    // 2. Division (Critical Concept!)
    // When dividing two integers, C++ performs "Integer Division" and drops the fractional part.
    cout << "Integer Division (num1 / num2): " << (num1 / num2) << "  (Note: 17/5 is 3.4, but decimal part is dropped!)" << endl;

    // To get the exact decimal result, at least one operand must be a floating-point type:
    cout << "Floating Division (17.0 / 5)  : " << (17.0 / num2) << endl;
    cout << "Floating Division (double cast): " << (static_cast<double>(num1) / num2) << endl;

    // 3. Modulus (%)
    // Returns the REMAINDER of the division. Modulus ONLY works with integers.
    // 17 % 5 -> 17 divided by 5 is 3 with a remainder of 2.
    cout << "Modulus / Remainder (num1 % num2): " << (num1 % num2) << endl;

    return 0;
}
