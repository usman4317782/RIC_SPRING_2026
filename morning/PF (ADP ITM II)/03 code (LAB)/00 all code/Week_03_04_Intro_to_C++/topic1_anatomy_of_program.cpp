// Week 3-4: Introduction to C++
// Topic 1: Anatomy of a C++ Program
// This program details each component of a basic C++ source file.

// 1. Preprocessor Directive
// Includes the standard Input/Output library. Without this, cout/cin will not work.
#include <iostream> 

// 2. Using namespace std
// A namespace groups identifiers. 'std' is the standard namespace.
// Using this statement allows us to write 'cout' instead of 'std::cout'.
using namespace std; 

// 3. The Main Function Header
// Every C++ program must have exactly one main function. Execution starts here.
// 'int' means this function returns an integer value to the Operating System.
int main() {
    
    // 4. Statements and Curly Braces
    // The opening '{' marks the start of the main function body.
    // Inside, we write executable statements terminated by a semicolon ';'.
    
    cout << "Welcome to the Anatomy of a C++ Program!" << endl; // Standard output statement
    
    // 5. Return Statement
    // Returns 0 to indicate standard, successful termination.
    return 0; 
    
    // The closing '}' marks the end of the main function body.
}
