// Week 15-17: Arrays and Vectors
// Topic 5: Introduction to the STL vector
// Demonstrates using 'std::vector' (from standard template library) which behaves like a dynamic array.
// Unlike standard arrays, vectors can automatically resize when elements are added or deleted.

#include <iostream>
#include <vector> // Required to use vectors
using namespace std;

int main() {
    // 1. Declaring a vector of integers
    // Initially empty. Size is 0.
    vector<int> numbers; 

    // 2. Adding elements (push_back)
    // Appends values to the end of the vector
    numbers.push_back(10);
    numbers.push_back(20);
    numbers.push_back(30);

    // 3. Accessing size (size)
    cout << "Vector size after push_back: " << numbers.size() << endl; // Prints 3

    // 4. Accessing elements
    // Can use normal array bracket syntax
    cout << "Element at index 1: " << numbers[1] << endl << endl; // Prints 20

    // 5. Removing the last element (pop_back)
    numbers.pop_back(); // Removes 30. Size becomes 2.
    cout << "Size after pop_back: " << numbers.size() << endl;

    // 6. Clearing the entire vector (clear)
    numbers.clear();
    cout << "Size after clear   : " << numbers.size() << endl;

    // Check if empty
    if (numbers.empty()) {
        cout << "The vector is now empty!" << endl;
    }

    return 0;
}
