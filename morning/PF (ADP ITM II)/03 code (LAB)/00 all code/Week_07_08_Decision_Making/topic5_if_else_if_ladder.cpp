// Week 7-8: Making Decisions
// Topic 5: The if/else if Statement (Ladder)
// Demonstrates checking multiple conditions in order until one is true. Very common for grading ranges.

#include <iostream>
using namespace std;

int main() {
    int testScore;
    cout << "Enter your test score (0 to 100): ";
    cin >> testScore;

    // Checks conditions sequentially from top to bottom.
    // As soon as one matches, its block runs, and the rest of the ladder is skipped.
    if (testScore < 0 || testScore > 100) {
        cout << "Invalid Score! Please enter a value between 0 and 100." << endl;
    }
    else if (testScore >= 90) {
        cout << "Your grade is: A" << endl;
    } 
    else if (testScore >= 80) {
        cout << "Your grade is: B" << endl;
    } 
    else if (testScore >= 70) {
        cout << "Your grade is: C" << endl;
    } 
    else if (testScore >= 60) {
        cout << "Your grade is: D" << endl;
    } 
    else {
        cout << "Your grade is: F (Fail)" << endl;
    }

    return 0;
}
