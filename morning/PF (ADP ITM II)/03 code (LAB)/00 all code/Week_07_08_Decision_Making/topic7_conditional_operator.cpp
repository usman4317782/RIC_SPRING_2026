// Week 7-8: Making Decisions
// Topic 7: The Conditional Operator (Ternary Operator)
// Demonstrates a shorthand method of writing simple if-else statements.
// Format: expression ? value_if_true : value_if_false;

#include <iostream>
#include <string>
using namespace std;

int main() {
    int age;
    cout << "Enter your age: ";
    cin >> age;

    // 1. Shorthand evaluation inside assignment
    // If age >= 18 is true, "Can vote" is assigned. Otherwise, "Too young" is assigned.
    string status = (age >= 18) ? "Can vote" : "Too young to vote";
    cout << "Status: " << status << endl << endl;

    // 2. Formatting values on the fly
    double score;
    cout << "Enter score: ";
    cin >> score;

    // Conditional operator inside cout
    cout << "Result: " << ((score >= 50) ? "PASS" : "FAIL") << endl;

    return 0;
}
