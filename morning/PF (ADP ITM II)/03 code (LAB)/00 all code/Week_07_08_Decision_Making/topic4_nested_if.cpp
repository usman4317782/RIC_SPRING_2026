// Week 7-8: Making Decisions
// Topic 4: Nested if Statements
// Shows how decision blocks can be placed inside other decision blocks for multi-layered checks.

#include <iostream>
using namespace std;

int main() {
    char employed;  // 'Y' or 'N'
    char graduated; // 'Y' or 'N'

    cout << "Answer the following questions with Y (yes) or N (no):" << endl;
    cout << "Are you employed? ";
    cin >> employed;
    cout << "Have you graduated from college? ";
    cin >> graduated;

    // Outer check
    if (employed == 'Y' || employed == 'y') {
        
        // Inner check - only evaluated if outer check is true
        if (graduated == 'Y' || graduated == 'y') {
            cout << "You qualify for the premium credit card rate!" << endl;
        } 
        else {
            cout << "You must graduate college first to get the premium rate." << endl;
        }
        
    } 
    else {
        cout << "You must be employed to qualify for a credit card." << endl;
    }

    return 0;
}
