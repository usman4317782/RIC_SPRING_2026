// Week 10-12: Loops and Files
// Topic 4: The for Loop
// Demonstrates a count-controlled loop. Best used when you know exactly how many iterations are needed.

#include <iostream>
using namespace std;

int main() {
    // A 'for' loop has three parts in its header:
    // for (initialization; test; update)
    // 1. initialization: runs once at start (e.g., int i = 1)
    // 2. test: evaluated before each iteration. Loop continues if true.
    // 3. update: runs at the end of each iteration (e.g., i++)
    
    cout << "--- Count from 1 to 10 ---" << endl;
    for (int i = 1; i <= 10; i++) {
        cout << i << " ";
    }
    cout << endl << endl;

    cout << "--- Count down from 5 to 1 ---" << endl;
    for (int i = 5; i >= 1; i--) {
        cout << i << " ";
    }
    cout << endl << endl;

    cout << "--- Display even numbers from 2 to 20 ---" << endl;
    for (int i = 2; i <= 20; i += 2) {
        cout << i << " ";
    }
    cout << endl;

    return 0;
}
