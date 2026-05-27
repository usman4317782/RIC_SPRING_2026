// Week 10-12: Loops and Files
// Topic 6: Nested Loops
// Shows how a loop inside another loop behaves. Used for multi-dimensional operations or grids.

#include <iostream>
using namespace std;

int main() {
    // A nested loop has an outer loop and an inner loop.
    // For every single iteration of the outer loop, the inner loop completes all of its iterations.

    int rows, cols;
    cout << "Enter number of rows to print: ";
    cin >> rows;
    cout << "Enter number of columns to print: ";
    cin >> cols;

    cout << "\nPrinting grid of asterisks (*):" << endl;

    // Outer loop controls the rows
    for (int r = 1; r <= rows; r++) {
        
        // Inner loop controls the columns (characters in a single row)
        for (int c = 1; c <= cols; c++) {
            cout << "* ";
        }
        
        // Move to the next line after finishing the current row
        cout << endl;
    }

    cout << "\nPrinting a right triangle pattern:" << endl;
    for (int r = 1; r <= rows; r++) {
        
        // Inner loop counts up to the current row number 'r'
        for (int c = 1; c <= r; c++) {
            cout << "* ";
        }
        cout << endl;
    }

    return 0;
}
