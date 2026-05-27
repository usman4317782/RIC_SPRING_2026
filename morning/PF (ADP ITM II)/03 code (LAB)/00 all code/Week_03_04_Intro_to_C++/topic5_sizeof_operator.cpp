// Week 3-4: Introduction to C++
// Topic 5: Determining the Size of a Data Type
// Demonstrates using the 'sizeof' operator to find out how many bytes a data type occupies in memory.

#include <iostream>
using namespace std;

int main() {
    cout << "============================================" << endl;
    cout << "  MEMORY SIZE OF BASIC DATA TYPES (IN BYTES) " << endl;
    cout << "============================================" << endl;

    // sizeof can be used on data types
    cout << "Size of char        : " << sizeof(char) << " byte(s)" << endl;
    cout << "Size of bool        : " << sizeof(bool) << " byte(s)" << endl;
    cout << "Size of int         : " << sizeof(int) << " bytes" << endl;
    cout << "Size of float       : " << sizeof(float) << " bytes" << endl;
    cout << "Size of double      : " << sizeof(double) << " bytes" << endl;

    // sizeof can also be used on variables
    short smallNumber = 100;
    long long bigNumber = 9999999999LL;
    
    cout << "\nMemory sizes of specific variables:" << endl;
    cout << "Size of short variable 'smallNumber': " << sizeof(smallNumber) << " bytes" << endl;
    cout << "Size of long long variable 'bigNumber': " << sizeof(bigNumber) << " bytes" << endl;

    cout << "============================================" << endl;
    cout << "Note: 1 byte = 8 bits of memory." << endl;
    return 0;
}
