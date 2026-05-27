// Week 3-4: Introduction to C++
// Topic 6: Scope of a Variable
// Demonstrates that variables are only accessible in the region of code where they are defined.

#include <iostream>
using namespace std;

int main() {
    // 1. Definition of a local variable
    // This variable exists from this point onwards until the end of main's block.
    int outerValue = 100;
    cout << "Inside main, outerValue is: " << outerValue << endl;

    // We CANNOT use innerValue here yet because it is not defined yet.
    // Uncommenting the next line will cause a compiler error:
    // cout << innerValue; 

    // 2. Defining a new block using curly braces
    {
        // This is a nested local scope (a block).
        int innerValue = 200;
        
        cout << "\n--- Inside nested block ---" << endl;
        cout << "Can access outerValue here: " << outerValue << endl;
        cout << "Can access innerValue here: " << innerValue << endl;
    } // innerValue is DESTROYED here. It goes "out of scope".

    cout << "\n--- Back in main scope ---" << endl;
    cout << "Can still access outerValue: " << outerValue << endl;
    
    // Attempting to access innerValue here will cause a compilation error:
    // cout << innerValue; 
    cout << "innerValue no longer exists here!" << endl;

    return 0;
}
