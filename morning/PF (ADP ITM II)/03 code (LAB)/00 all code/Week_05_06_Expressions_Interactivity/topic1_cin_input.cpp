// Week 5-6: Expression and Interactivity
// Topic 1: The cin Object
// Demonstrates reading user input from the console using std::cin.

#include <iostream>
using namespace std;

int main() {
    // 1. Basic numeric input
    int age;
    cout << "Enter your age: ";
    cin >> age; // Extraction operator (>>). Waits for user input and assigns it to 'age'.
    cout << "You are " << age << " years old." << endl;

    // 2. Reading multiple inputs in one line
    double length, width;
    cout << "Enter the length and width of a rectangle (separated by space or Enter): ";
    cin >> length >> width; // Chains extraction operators to read multiple values

    double area = length * width;
    cout << "Rectangle area: " << area << endl;

    return 0;
}
