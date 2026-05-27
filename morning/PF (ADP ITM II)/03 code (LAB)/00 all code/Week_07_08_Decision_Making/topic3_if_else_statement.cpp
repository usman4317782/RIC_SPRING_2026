// Week 7-8: Making Decisions
// Topic 3: The if/else Statement
// Demonstrates a double-alternative decision structure. Runs one block if true, and a different block if false.

#include <iostream>
using namespace std;

int main() {
    int number;
    cout << "Enter an integer to check if it is Even or Odd: ";
    cin >> number;

    // We check if the remainder of number divided by 2 is 0.
    if (number % 2 == 0) {
        // Runs if number % 2 == 0 is true.
        cout << number << " is an EVEN number." << endl;
    } 
    else {
        // Runs if number % 2 == 0 is false.
        cout << number << " is an ODD number." << endl;
    }

    return 0;
}
