// Week 10-12: Loops and Files
// Topic 2: Introduction to Loops: The while Loop
// Shows the pre-test while loop structure and how it is used for input validation.

#include <iostream>
using namespace std;

int main() {
    // 1. Simple count loop
    int count = 1;
    cout << "--- Count from 1 to 5 ---" << endl;
    
    // while loop checks the condition first. If true, it runs the block.
    // Make sure the loop control variable changes inside to prevent an infinite loop!
    while (count <= 5) {
        cout << "Number: " << count << endl;
        count++; // Increment loop control variable
    }
    cout << endl;

    // 2. Input Validation (Using while loop to reject bad values)
    int score;
    cout << "Enter a test score (0 - 100): ";
    cin >> score;

    // Keep asking as long as the user inputs an invalid score
    while (score < 0 || score > 100) {
        cout << "Error: Score must be between 0 and 100." << endl;
        cout << "Please enter a valid score: ";
        cin >> score;
    }

    cout << "Valid score recorded: " << score << endl;

    return 0;
}
