// Week 10-12: Loops and Files
// Topic 9: Optional Topics: Breaking and Continuing a Loop
// Explains how to break loop execution or skip standard iterations.

#include <iostream>
using namespace std;

int main() {
    // 1. The break statement
    // Immediately exits the loop, jumping to the code directly after the loop block.
    cout << "--- Demonstration of 'break' (stops when i == 5) ---" << endl;
    for (int i = 1; i <= 10; i++) {
        if (i == 5) {
            cout << "[break at i = " << i << "] ";
            break; // Immediately exit loop
        }
        cout << i << " ";
    }
    cout << "\nLoop finished." << endl << endl;

    // 2. The continue statement
    // Skips the remaining code inside the current iteration and jumps to the next loop update/evaluation.
    cout << "--- Demonstration of 'continue' (skips even numbers) ---" << endl;
    for (int i = 1; i <= 10; i++) {
        if (i % 2 == 0) {
            continue; // Skip the rest of the loop block for even numbers
        }
        // This print statement is skipped for even values of 'i'
        cout << i << " ";
    }
    cout << "\nLoop finished." << endl;

    return 0;
}
