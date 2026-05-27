// Week 10-12: Loops and Files
// Topic 3: The do-while Loop
// Shows the post-test do-while loop. The code block is guaranteed to execute at least once.
// Ideal for interactive menus.

#include <iostream>
using namespace std;

int main() {
    int choice;

    // A do-while loop executes the body first, then checks the condition at the end.
    do {
        cout << "\n===== STUDENT INFO CENTER =====" << endl;
        cout << "1. View GPA Requirements" << endl;
        cout << "2. View Tuition Fees" << endl;
        cout << "3. View Contact Information" << endl;
        cout << "4. Exit Menu" << endl;
        cout << "Enter your choice (1-4): ";
        cin >> choice;

        // Input validation inside loop
        if (choice < 1 || choice > 4) {
            cout << "Invalid choice! Please select 1, 2, 3, or 4." << endl;
            continue; // Skip the rest of the loop and start next iteration
        }

        switch (choice) {
            case 1:
                cout << "GPA Requirements: Minimum 2.0 GPA needed to graduate." << endl;
                break;
            case 2:
                cout << "Tuition Fees: $1200 per semester." << endl;
                break;
            case 3:
                cout << "Contact: portal@college.edu | Tel: 555-0199" << endl;
                break;
            case 4:
                cout << "Exiting. Thank you!" << endl;
                break;
        }

    } while (choice != 4); // Loop continues as long as choice is not 4

    return 0;
}
