// Week 15-17: Arrays and Vectors
// Topic 1: Arrays Hold Multiple Values (Accessing Array Elements)
// Shows how to declare, initialize, and traverse a basic single-dimensional array.

#include <iostream>
using namespace std;

int main() {
    // 1. Declare and Initialize Array
    // An array is a collection of variables of the same data type stored in contiguous memory.
    // '5' is the size of the array. Elements are initialized inside curly braces.
    int testScores[5] = {85, 90, 78, 92, 88};

    // Array elements are accessed using 0-based indices:
    // testScores[0] -> 85
    // testScores[1] -> 90
    // ...
    // testScores[4] -> 88
    
    cout << "First score (Index 0): " << testScores[0] << endl;
    cout << "Last score  (Index 4): " << testScores[4] << endl << endl;

    // 2. Modifying array elements
    testScores[2] = 95; // Changes 78 to 95
    cout << "Updated Third score (Index 2): " << testScores[2] << endl << endl;

    // 3. Traversing array using a standard 'for' loop
    cout << "--- Printing all elements using a loop ---" << endl;
    for (int index = 0; index < 5; index++) {
        cout << "Score at index " << index << ": " << testScores[index] << endl;
    }

    // 4. Critical Warning: No bounds checking in C++!
    // Accessing an index outside [0 to size-1] is a logical error and will access garbage memory.
    // e.g., testScores[5] is out of bounds!
    cout << "\nWarning: C++ does not check array boundaries. Accessing testScores[5] retrieves garbage: " 
         << testScores[5] << endl;

    return 0;
}
