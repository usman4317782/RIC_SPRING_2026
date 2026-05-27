// Week 10-12: Loops and Files
// Topic 1: The Increment and Decrement Operators
// Shows the difference between Prefix (++var, --var) and Postfix (var++, var--) expressions.

#include <iostream>
using namespace std;

int main() {
    int value = 10;

    cout << "Initial value: " << value << endl << endl;

    // 1. Basic usage
    value++; // Increment value by 1. Identical to value = value + 1.
    cout << "After value++: " << value << endl;

    value--; // Decrement value by 1. Identical to value = value - 1.
    cout << "After value--: " << value << endl << endl;

    // 2. Prefix vs Postfix in statements
    int x = 5;
    int y;
    
    // Postfix: y gets the current value of x, THEN x increases.
    y = x++; 
    cout << "--- Postfix (y = x++) ---" << endl;
    cout << "y gets value first: y = " << y << endl; // y is 5
    cout << "x increases after : x = " << x << endl; // x is 6
    cout << endl;

    x = 5; // Reset x
    
    // Prefix: x increases first, THEN y gets the new value of x.
    y = ++x; 
    cout << "--- Prefix (y = ++x) ---" << endl;
    cout << "x increases first: x = " << x << endl; // x is 6
    cout << "y gets new value : y = " << y << endl; // y is 6

    return 0;
}
