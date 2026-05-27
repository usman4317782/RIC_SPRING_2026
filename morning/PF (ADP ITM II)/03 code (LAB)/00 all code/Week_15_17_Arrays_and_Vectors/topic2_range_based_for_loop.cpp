// Week 15-17: Arrays and Vectors
// Topic 2: The Range-Based for Loop
// Demonstrates modern C++ syntax for cleaner array traversal.

#include <iostream>
#include <string>
using namespace std;

int main() {
    string cars[4] = {"Toyota", "Honda", "Ford", "Tesla"};

    // 1. Read-only traversal
    // Syntax: for (dataType variableName : arrayName)
    // The variable 'car' automatically gets assigned each element of the array in sequence.
    cout << "--- Cars list (read-only) ---" << endl;
    for (string car : cars) {
        cout << car << endl;
    }
    cout << endl;

    // 2. Modifying elements using references (&)
    // If we want to change array elements inside the loop, we must use a reference variable.
    int prices[3] = {100, 200, 300};
    
    cout << "--- Applying discount using references ---" << endl;
    // 'price' acts as an alias to the actual element in memory.
    for (int &price : prices) {
        price = price - 10; // Deduct 10 from each element
    }

    // Print to verify change
    for (int price : prices) {
        cout << price << " ";
    }
    cout << endl;

    return 0;
}
