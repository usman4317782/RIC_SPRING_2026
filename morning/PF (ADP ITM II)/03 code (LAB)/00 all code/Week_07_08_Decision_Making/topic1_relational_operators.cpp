// Week 7-8: Making Decisions
// Topic 1: Relational Operators
// Demonstrates how relational operators compare values and return boolean true (1) or false (0).

#include <iostream>
using namespace std;

int main() {
    int x = 10;
    int y = 20;

    cout << boolalpha; // Instructs cout to print true or false as words instead of 1 or 0
    cout << "x = " << x << ", y = " << y << endl << endl;

    // Relational Operators:
    // Operator  Meaning
    // ==        Equal to
    // !=        Not equal to
    // >         Greater than
    // <         Less than
    // >=        Greater than or equal to
    // <=        Less than or equal to

    cout << "x < y  : " << (x < y) << endl;  // true
    cout << "x > y  : " << (x > y) << endl;  // false
    cout << "x == y : " << (x == y) << endl; // false
    cout << "x != y : " << (x != y) << endl; // true
    
    // Using comparisons in assignments
    bool comparisonResult = (x <= y);
    cout << "Is x <= y? " << comparisonResult << endl;

    return 0;
}
