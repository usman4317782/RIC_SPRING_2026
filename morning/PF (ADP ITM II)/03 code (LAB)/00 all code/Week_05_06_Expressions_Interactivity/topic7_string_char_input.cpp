// Week 5-6: Expression and Interactivity
// Topic 7: Working with Characters and String Objects
// Demonstrates reading string lines containing spaces, reading single characters, and managing buffer issues.

#include <iostream>
#include <string>
using namespace std;

int main() {
    // 1. Problem with standard 'cin >>': It stops reading at the first space!
    string shortWord;
    cout << "Enter a single word: ";
    cin >> shortWord;
    cout << "You entered: " << shortWord << endl << endl;

    // 2. Clear input buffer (Very Important!)
    // When cin >> reads data, it leaves the newline '\n' in the buffer.
    // We must ignore the rest of the line before using getline, otherwise getline will read the leftover newline.
    cin.ignore(); 

    // 3. Reading strings with spaces (getline)
    string fullName;
    cout << "Enter your full name (with spaces): ";
    getline(cin, fullName); // Reads the whole line until the user presses Enter.
    cout << "Welcome, " << fullName << endl << endl;

    // 4. Reading a single character (cin.get)
    char nextChar;
    cout << "Press any key followed by Enter: ";
    cin.get(nextChar); // Reads the exact next character in the buffer, including spaces/newlines.
    cout << "The ASCII value of character entered is: " << static_cast<int>(nextChar) << endl;

    return 0;
}
