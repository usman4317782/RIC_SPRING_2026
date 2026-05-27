// Week 10-12: Loops and Files
// Topic 8: Using Files for Data Storage (Reading from Files)
// Demonstrates how to open and read data line-by-line from a text file.

#include <iostream>
#include <fstream> // Required for file operations
#include <string>
using namespace std;

int main() {
    // 1. Declare an input file stream variable
    ifstream inputFile;

    // 2. Open the file
    inputFile.open("student_roster.txt");

    // 3. Verify the file exists and opened successfully
    if (!inputFile) {
        cout << "Error: Could not open 'student_roster.txt'. Make sure to run the file writing program first!" << endl;
        return 1;
    }

    cout << "Reading student roster from file:" << endl;
    cout << "---------------------------------" << endl;

    string name;
    
    // 4. Read data using a loop
    // getline(inputFile, name) reads a line from the file and stores it in 'name'.
    // The loop automatically stops when it reaches the end of the file (EOF).
    while (getline(inputFile, name)) {
        cout << "- " << name << endl;
    }

    // 5. Close the file
    inputFile.close();

    cout << "---------------------------------" << endl;
    cout << "File reading complete." << endl;

    return 0;
}
