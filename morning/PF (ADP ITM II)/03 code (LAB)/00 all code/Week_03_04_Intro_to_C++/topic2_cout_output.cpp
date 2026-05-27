// Week 3-4: Introduction to C++
// Topic 2: The cout Object and Escape Sequences
// Demonstrates screen output using cout, the stream insertion operator (<<), and escape characters.

#include <iostream>
using namespace std;

int main() {
    // Basic printing
    cout << "Standard print using cout.";
    cout << " Notice that this prints on the same line since there is no new-line command." << endl;

    // Using 'endl' vs '\n'
    cout << "First line using endl." << endl;
    cout << "Second line using new-line escape character \\n.\n";
    cout << "Third line." << endl;

    // Escape Sequences Demonstration:
    // \n - New line
    // \t - Horizontal tab
    // \' - Single quote
    // \" - Double quote
    // \\ - Backslash
    
    cout << "\n--- Escape Sequences Demonstration ---\n";
    cout << "1. Tabbed output:\tItem A\tItem B\tItem C\n";
    cout << "2. Double quotes:\t\"Programming is fun!\"\n";
    cout << "3. Single quotes:\t\'C\'\n";
    cout << "4. Backslash print:\tC:\\Program Files\\C++\n";
    
    return 0;
}
