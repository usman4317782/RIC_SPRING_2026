// Week 15-17: Arrays and Vectors
// Topic 3: Arrays as Function Arguments
// Explains how to pass arrays to functions.
// Note: Arrays are always passed by reference (the memory address is passed, not a copy).

#include <iostream>
using namespace std;

// Function Prototypes
// Array parameters are declared with empty brackets '[]'.
// Since the function does not know the size of the array, we must pass size as a separate parameter!
void showArray(const int nums[], int size); 
void doubleArray(int nums[], int size);

int main() {
    const int SIZE = 5;
    int data[SIZE] = {2, 4, 6, 8, 10};

    cout << "Original array contents:" << endl;
    // We pass the array name 'data' as argument (no brackets here!)
    showArray(data, SIZE); 

    // Modifying array contents inside function
    doubleArray(data, SIZE);

    cout << "\nArray contents after calling doubleArray:" << endl;
    showArray(data, SIZE); // Values are changed!

    return 0;
}

// Function Definitions

// 'const' keyword in parameter prevents the function from modifying the array elements accidentally.
void showArray(const int nums[], int size) {
    for (int i = 0; i < size; i++) {
        cout << nums[i] << " ";
    }
    cout << endl;
}

// Any modifications made here will directly affect the original array in main.
void doubleArray(int nums[], int size) {
    for (int i = 0; i < size; i++) {
        nums[i] *= 2;
    }
}
