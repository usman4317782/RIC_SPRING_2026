// Week 7-8: Making Decisions
// Topic 2: The if Statement
// Demonstrates a single-alternative decision structure. Code block executes only if condition is true.

#include <iostream>
using namespace std;

int main() {
    double score;
    cout << "Enter your test score (0-100): ";
    cin >> score;

    // The if statement checks a condition inside parentheses.
    // If true, the code inside the curly braces runs.
    if (score >= 50) {
        cout << "Congratulations!" << endl;
        cout << "You passed the test." << endl;
    }

    // This statement is outside the if statement block, so it always runs.
    cout << "Thank you for taking the test." << endl;

    return 0;
}
