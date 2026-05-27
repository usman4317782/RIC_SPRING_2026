// Week 10-12: Loops and Files
// Topic 7: Using Files for Data Storage (Writing to Files)
// Demonstrates how to create and write text to a external file using <fstream>'s ofstream.

#include <iostream>
#include <fstream> // Required for file operations (ifstream, ofstream)
#include <string>
using namespace std;

int main() {
    // 1. Declare an output file stream variable
    ofstream outputFile;

    // 2. Open the file
    // If the file does not exist, C++ will create it. If it exists, it will overwrite it.
    outputFile.open("student_roster.txt");

    // 3. Check if the file opened successfully
    if (!outputFile) {
        cout << "Error: Could not create/open the file!" << endl;
        return 1; // Exit with error code
    }

    cout << "Writing student records to 'student_roster.txt'..." << endl;

    // 4. Write data to the file
    // We use the stream insertion operator (<<) with outputFile just like we do with cout!
    outputFile << "Ali Khan" << endl;
    outputFile << "Sara Ahmed" << endl;
    outputFile << "John Doe" << endl;

    // 5. Close the file
    // Always close files when finished to save data and release system resources.
    outputFile.close();

    cout << "Done! Open student_roster.txt to see the saved names." << endl;

    return 0;
}
