#include <iostream>
using namespace std;

// Inner structure
struct Address {
    string city;
    string country;
};

// Outer structure (nested)
struct Student {
    int rollNo;
    string name;
    Address addr;   // Nested structure
};

int main() {
    Student s1; // definition

    cout << "Nested structure declared and variable defined." << endl;
    return 0;
}