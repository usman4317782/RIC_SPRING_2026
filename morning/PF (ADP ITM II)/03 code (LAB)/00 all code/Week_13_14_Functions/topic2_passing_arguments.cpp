// Week 13-14: Functions
// Topic 2: Passing Arguments (Value vs Reference parameters)
// Compares Pass-by-Value (makes a copy) with Pass-by-Reference (modifies original variable using '&').

#include <iostream>
using namespace std;

// Function Prototypes
// Pass by Value: takes a normal variable copy
void passByValue(int num); 

// Pass by Reference: uses '&' in parameter type. Takes original variable address
void passByReference(int &num); 

int main() {
    int value1 = 100;
    int value2 = 100;

    cout << "Initial values: value1 = " << value1 << ", value2 = " << value2 << endl << endl;

    // 1. Pass by Value
    cout << "--- Calling passByValue ---" << endl;
    passByValue(value1);
    cout << "After call, value1 is: " << value1 << " (Unchanged! Only the copy was modified.)" << endl << endl;

    // 2. Pass by Reference
    cout << "--- Calling passByReference ---" << endl;
    passByReference(value2);
    cout << "After call, value2 is: " << value2 << " (Changed! The original memory was updated.)" << endl;

    return 0;
}

// Function Definitions
void passByValue(int num) {
    num = 999; // Modifies the parameter copy
    cout << "Inside passByValue, num modified to: " << num << endl;
}

void passByReference(int &num) {
    num = 999; // Modifies the actual variable passed in
    cout << "Inside passByReference, num modified to: " << num << endl;
}
