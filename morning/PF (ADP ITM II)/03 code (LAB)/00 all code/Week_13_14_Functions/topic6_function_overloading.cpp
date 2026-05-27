// Week 13-14: Functions
// Topic 6: Function Overloading
// Demonstrates defining multiple functions with the SAME name, but DIFFERENT parameter lists.
// The compiler automatically decides which function to execute based on arguments passed.

#include <iostream>
using namespace std;

// Overloaded Function Prototypes
// Function signatures must be unique (different number of params or different types).
// Note: Return type ALONE cannot be used to overload functions!
int add(int a, int b);
double add(double a, double b);
int add(int a, int b, int c);

int main() {
    // 1. Calls add(int, int)
    cout << "Calling add with two ints (5 + 10): " << add(5, 10) << endl;

    // 2. Calls add(double, double)
    cout << "Calling add with two doubles (2.5 + 4.7): " << add(2.5, 4.7) << endl;

    // 3. Calls add(int, int, int)
    cout << "Calling add with three ints (1 + 2 + 3): " << add(1, 2, 3) << endl;

    return 0;
}

// Function Definitions
int add(int a, int b) {
    cout << "[Executing: add(int, int)] ";
    return a + b;
}

double add(double a, double b) {
    cout << "[Executing: add(double, double)] ";
    return a + b;
}

int add(int a, int b, int c) {
    cout << "[Executing: add(int, int, int)] ";
    return a + b + c;
}
