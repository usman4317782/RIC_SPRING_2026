// Week 15-17: Arrays and Vectors
// Topic 4: Two-Dimensional Arrays
// Demonstrates working with tabular data (rows and columns) using 2D arrays.

#include <iostream>
#include <iomanip>
using namespace std;

int main() {
    // Declaring a 2D array: 3 rows, 4 columns
    // Organized as: array[row_index][column_index]
    int grid[3][4] = {
        {10, 11, 12, 13}, // Row 0
        {20, 21, 22, 23}, // Row 1
        {30, 31, 32, 33}  // Row 2
    };

    // 1. Accessing specific element
    // To print '22' (Row 1, Column 2):
    cout << "Value at row 1, column 2: " << grid[1][2] << endl << endl;

    // 2. Modifying an element
    grid[2][3] = 99; // Row 2, Col 3 becomes 99

    // 3. Printing the entire table using nested loops
    cout << "--- Printing 2D Array ---" << endl;
    for (int r = 0; r < 3; r++) { // Outer loop matches row index
        
        for (int c = 0; c < 4; c++) { // Inner loop matches column index
            cout << setw(5) << grid[r][c];
        }
        
        cout << endl; // New line after finishing each row
    }

    return 0;
}
