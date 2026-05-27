// Week 5-6: Expression and Interactivity
// Topic 4: Overflow and Underflow
// Demonstrates what happens when a numeric variable exceeds its maximum or minimum limit.

#include <iostream>
#include <climits> // Library containing limits for integer types (e.g. INT_MAX, SHRT_MAX)
using namespace std;

int main() {
    // 1. Integer Overflow
    // A 16-bit short integer max value is 32767. Let's see what happens if we add 1 to it.
    short testShort = SHRT_MAX; // 32767
    cout << "Initial max short value: " << testShort << endl;

    testShort = testShort + 1; // Overflow occurs!
    // The value wraps around to the lowest possible value (-32768).
    cout << "After overflow (+1)    : " << testShort << endl;

    // 2. Integer Underflow
    // Let's take the minimum short value (-32768) and subtract 1 from it.
    testShort = SHRT_MIN; // -32768
    cout << "\nInitial min short value: " << testShort << endl;

    testShort = testShort - 1; // Underflow occurs!
    // The value wraps around to the highest possible value (32767).
    cout << "After underflow (-1)   : " << testShort << endl;

    return 0;
}
