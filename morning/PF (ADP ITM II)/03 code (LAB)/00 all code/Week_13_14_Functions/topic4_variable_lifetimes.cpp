// Week 13-14: Functions
// Topic 4: Local, Global, and Static Local Variables
// Explains scopes, differences between local/global variables, and static variable retention.

#include <iostream>
using namespace std;

// 1. Global Variable
// Defined outside of any function. Accessible by ANY function below its definition.
// NOTE: Global variables are generally discouraged because they make debugging harder.
int globalCounter = 0; 

// Function Prototypes
void localDemo();
void staticDemo();

int main() {
    cout << "--- Global Variable Access ---" << endl;
    cout << "globalCounter in main: " << globalCounter << endl;
    globalCounter++; // Update global variable
    
    localDemo();
    cout << "globalCounter after localDemo call: " << globalCounter << endl << endl;

    // 2. Static Local Variable Demonstration
    cout << "--- Static Local Variables vs Regular Locals ---" << endl;
    cout << "Calling staticDemo 3 times:" << endl;
    staticDemo(); // 1st call
    staticDemo(); // 2nd call
    staticDemo(); // 3rd call

    return 0;
}

// Function Definitions
void localDemo() {
    // 3. Local Variable
    // Re-created every time function is called, destroyed when function exits.
    int localVal = 10;
    
    cout << "Inside localDemo. localVal: " << localVal << endl;
    globalCounter++; // Modifying global variable inside function
}

void staticDemo() {
    // 4. Static Local Variable
    // 'static' prevents the variable from being destroyed when function exits.
    // It retains its value between function calls. Initialized ONLY ONCE.
    static int callCount = 1;
    
    int regularCount = 1; // Re-initialized to 1 on every function call

    cout << "Call #" << callCount << " | static callCount: " << callCount 
         << " | regularCount: " << regularCount << endl;

    callCount++;    // Retained for next call
    regularCount++; // Destroyed when function returns
}
