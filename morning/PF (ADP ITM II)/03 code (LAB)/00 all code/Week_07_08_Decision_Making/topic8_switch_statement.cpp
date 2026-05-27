// Week 7-8: Making Decisions
// Topic 8: The switch Statement
// Demonstrates using 'switch' for multi-value checks. Shows how to implement a basic console menu.

#include <iostream>
using namespace std;

int main() {
    int choice;

    cout << "===== FAST FOOD MENU =====" << endl;
    cout << "1. Beef Burger   ($5.00)" << endl;
    cout << "2. Chicken Wrap  ($4.50)" << endl;
    cout << "3. French Fries  ($2.50)" << endl;
    cout << "4. Soft Drink    ($1.50)" << endl;
    cout << "Enter your choice (1-4): ";
    cin >> choice;

    // switch evaluates an integer or character expression
    switch (choice) {
        case 1:
            cout << "You chose Beef Burger. Total: $5.00" << endl;
            break; // Exits the switch block. Crucial to prevent falling through to next cases!
            
        case 2:
            cout << "You chose Chicken Wrap. Total: $4.40" << endl;
            break;
            
        case 3:
            cout << "You chose French Fries. Total: $2.50" << endl;
            break;
            
        case 4:
            cout << "You chose Soft Drink. Total: $1.50" << endl;
            break;
            
        default:
            // Runs if 'choice' doesn't match any case value
            cout << "Error: Invalid choice entered!" << endl;
    }

    return 0;
}
