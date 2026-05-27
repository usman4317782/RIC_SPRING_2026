// Week 10-12: Loops and Files
// Topic 5: Keeping a Running Total and Sentinels
// Explains how to sum up a list of numbers using an accumulator (running total)
// and how to use a special "sentinel" value to stop user input.

#include <iostream>
using namespace std;

int main() {
    // 1. Accumulator Variable (Running Total)
    // An accumulator must be initialized to 0!
    int totalPoints = 0; 
    
    // 2. Sentinel Value
    // A sentinel is a special value that marks the end of a list. 
    // Here, we'll use -1 as a sentinel value since points scored cannot be negative.
    const int SENTINEL = -1;

    int points;
    cout << "Enter points earned for each assignment." << endl;
    cout << "Enter " << SENTINEL << " when you are finished." << endl << endl;

    cout << "Enter points: ";
    cin >> points;

    // Loop until the user enters the sentinel value
    while (points != SENTINEL) {
        // Accumulate points into running total
        totalPoints += points; 
        
        // Get the next input from the user
        cout << "Enter points (or " << SENTINEL << " to stop): ";
        cin >> points;
    }

    cout << "\n======================================" << endl;
    cout << "Total Points earned: " << totalPoints << endl;
    cout << "======================================" << endl;

    return 0;
}
