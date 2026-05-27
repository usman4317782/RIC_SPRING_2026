// Week 13-14: Functions
// Topic 7: The exit() Function
// Shows how to instantly stop a program at any location in code using the exit() function.

#include <iostream>
#include <cstdlib> // Required to use exit()
using namespace std;

// Function Prototype
void checkDivision(int numerator, int denominator);

int main() {
    int num, den;
    cout << "Enter a numerator: ";
    cin >> num;
    cout << "Enter denominator (0 to trigger exit): ";
    cin >> den;

    checkDivision(num, den);

    // This statement will only run if denominator is NOT 0.
    int result = num / den;
    cout << num << " / " << den << " = " << result << endl;
    cout << "Program finished normally." << endl;

    return 0;
}

// Function Definition
void checkDivision(int numerator, int denominator) {
    if (denominator == 0) {
        cout << "\n[CRITICAL ERROR: Division by zero is undefined!]" << endl;
        cout << "Terminating program instantly using exit()." << endl;
        
        // exit() takes an integer code returned to OS.
        // EXIT_FAILURE is a standard constant for error termination.
        // Unlike return, exit() terminates program immediately, even from deep inside functions!
        exit(EXIT_FAILURE); 
    }
}
