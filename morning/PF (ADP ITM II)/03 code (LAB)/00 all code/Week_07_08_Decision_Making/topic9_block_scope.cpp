// Week 7-8: Making Decisions
// Topic 9: More about Blocks and Variable Scope
// Highlights scope rules specifically within decision blocks and loops.

#include <iostream>
using namespace std;

int main() {
    int score = 85;

    cout << "Initial score: " << score << endl;

    if (score >= 50) {
        // Variable declared inside the 'if' block
        // It is local to this specific block, and will be destroyed when the block ends.
        int bonusPoints = 5;
        
        score += bonusPoints;
        cout << "Passed! You received " << bonusPoints << " bonus points." << endl;
        cout << "Updated score: " << score << endl;
    }

    // Trying to access 'bonusPoints' outside the curly brace block where it was declared
    // will cause a compiler error:
    // cout << "Your bonus points were: " << bonusPoints << endl; // ERROR!

    cout << "Final score: " << score << endl;

    return 0;
}
