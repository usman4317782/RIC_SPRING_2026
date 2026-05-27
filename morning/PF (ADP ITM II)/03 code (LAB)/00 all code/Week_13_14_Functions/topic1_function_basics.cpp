// Week 13-14: Functions
// Topic 1: Function Basics (Defining, Calling, and Prototyping)
// Demonstrates how to create a basic void function, call it from main,
// and the role of function prototypes.

#include <iostream>
using namespace std;

// 1. Function Prototype (Declaration)
// Tells the compiler about the function name, parameter types, and return type
// before the function is actually defined. This allows us to call the function
// in main even if the definition is written below main.
void displayMessage(); 

int main() {
    cout << "Inside main function. Preparing to call function..." << endl;

    // 2. Calling a Function
    // Runs the code inside displayMessage function.
    displayMessage(); 

    cout << "Back inside main function after function call." << endl;
    return 0;
}

// 3. Function Definition
// The actual code block of the function.
// 'void' means this function does not return any value back to the caller.
void displayMessage() {
    cout << ">>> Hello! This message is printed from inside displayMessage function." << endl;
}
