// Week 3-4: Introduction to C++
// Topic 4: Data Types (Declaring and Using Variables)
// Shows the different standard data types in C++.

#include <iostream>
#include <string> // Required to use the string data type
using namespace std;

int main() {
    // 1. Integer Types
    int age = 19;
    cout << "Integer (int) - for whole numbers: " << age << endl;

    // 2. Floating-Point Types
    float temperature = 98.6f; // Note the 'f' suffix for float literals
    double gpa = 3.85;        // double has higher precision than float (default for decimals)
    cout << "Floating Point (float): " << temperature << endl;
    cout << "Double Precision (double): " << gpa << endl;

    // 3. Character Type
    char grade = 'A'; // Single character inside single quotes
    cout << "Character (char) - for single characters: " << grade << endl;

    // 4. Boolean Type
    bool isPassed = true; // Can hold either 'true' or 'false'
    // Note: C++ prints bools as 1 (for true) or 0 (for false).
    cout << "Boolean (bool) - prints as 1/0: " << isPassed << endl;
    
    // To print true/false words, use boolalpha manipulator:
    cout << "Boolean printed as word: " << boolalpha << isPassed << endl;

    // 5. String Type
    string studentName = "Ali Khan"; // Sequence of characters inside double quotes
    cout << "String (string) - for text: " << studentName << endl;

    return 0;
}
