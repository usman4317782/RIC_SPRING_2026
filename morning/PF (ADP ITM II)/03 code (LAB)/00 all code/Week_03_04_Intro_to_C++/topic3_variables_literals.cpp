// Week 3-4: Introduction to C++
// Topic 3: Variables, Literals, and Assignment Statement
// Explains what variables and literals are, and how assignment (=) works.

#include <iostream>
using namespace std;

int main() {
    // 1. Variable Declaration
    // A variable is a named storage location in computer memory.
    // 'number' is the variable name (identifier), and 'int' is the data type.
    int number;

    // 2. Assignment Statement
    // Stores a value in a variable. The value on the right is assigned to the variable on the left.
    // '12' is an Integer Literal (a hardcoded constant value in code).
    number = 12;

    cout << "The variable 'number' contains: " << number << endl;

    // 3. Re-assignment
    // Variables can change their value during execution.
    number = 25; // 25 is another integer literal.
    cout << "After re-assignment, 'number' contains: " << number << endl;

    // 4. Initialization
    // Assigning a value to a variable at the moment it is declared.
    int age = 20; // Declaration + Initialization
    cout << "Student age initialized to: " << age << endl;

    // 5. Literals Examples
    // Literals are fixed values directly written in the code.
    cout << "Double Literal: " << 3.14159 << endl;
    cout << "Character Literal: " << 'A' << endl;
    cout << "String Literal: " << "Hello, students!" << endl;

    return 0;
}
