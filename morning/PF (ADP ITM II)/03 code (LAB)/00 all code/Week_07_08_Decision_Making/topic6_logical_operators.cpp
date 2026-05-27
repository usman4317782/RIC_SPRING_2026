// Week 7-8: Making Decisions
// Topic 6: Logical Operators (&&, ||, !)
// Demonstrates how to combine multiple conditions together.

#include <iostream>
using namespace std;

int main() {
    // 1. Logical AND (&&) - both conditions must be true
    double income;
    int yearsOnJob;
    
    cout << "--- Apply for a Loan ---" << endl;
    cout << "Enter annual income: $";
    cin >> income;
    cout << "Enter years at current job: ";
    cin >> yearsOnJob;

    if (income >= 35000.0 && yearsOnJob >= 2) {
        cout << "Congratulations! You qualify for the loan." << endl;
    } 
    else {
        cout << "Sorry, you do not meet the minimum requirements." << endl;
    }

    // 2. Logical OR (||) - at least one condition must be true
    char hasCollateral; // 'Y' or 'N'
    cout << "\nDo you have collateral? (Y/N): ";
    cin >> hasCollateral;

    // Qualify if high income AND job, OR if they have collateral
    if ((income >= 35000.0 && yearsOnJob >= 2) || (hasCollateral == 'Y' || hasCollateral == 'y')) {
        cout << "Loan status: APPROVED (either by income or collateral)." << endl;
    } 
    else {
        cout << "Loan status: REJECTED." << endl;
    }

    // 3. Logical NOT (!) - reverses a boolean value
    bool isRaining = false;
    if (!isRaining) {
        cout << "\nIt is not raining, let's go for a walk!" << endl;
    }

    return 0;
}
