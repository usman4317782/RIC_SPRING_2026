// Week 5-6: Expression and Interactivity
// Topic 6: Formatting Output
// Demonstrates using the <iomanip> library to control field width, alignment, and decimal precision.

#include <iostream>
#include <iomanip> // Required for stream manipulators like setw, setprecision, fixed
#include <string>
using namespace std;

int main() {
    double price1 = 129.954;
    double price2 = 6.8;
    double price3 = 1999.0;

    // 1. Controlling Decimal Precision (setprecision & fixed)
    // 'fixed' forces C++ to display decimal notation instead of scientific.
    // 'setprecision(2)' limits the numbers after the decimal point to 2.
    cout << "--- Decimal Formatting ---" << endl;
    cout << fixed << setprecision(2);
    cout << "Price 1: $" << price1 << endl; // Prints 129.95
    cout << "Price 2: $" << price2 << endl; // Prints 6.80 (pads with trailing zero)
    cout << "Price 3: $" << price3 << endl; // Prints 1999.00
    cout << endl;

    // 2. Aligning columns (setw, left, right)
    // setw(N) reserves N character spaces for the next printed value (right-aligned by default).
    cout << "--- Table Alignment with setw ---" << endl;
    
    // Print Table Header (Width 15 for Name, Width 10 for GPA)
    cout << left << setw(15) << "Student Name" << right << setw(10) << "GPA" << endl;
    cout << "---------------------------------" << endl;
    
    cout << left << setw(15) << "Ahmed Ali" << right << setw(10) << 3.82 << endl;
    cout << left << setw(15) << "Sara Khan" << right << setw(10) << 3.90 << endl;
    cout << left << setw(15) << "Bilal Ahmed" << right << setw(10) << 2.65 << endl;

    return 0;
}
