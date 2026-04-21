#include <iostream>
using namespace std;

struct Student {
    int rollNo;
    string name;
    float marks;
};

int main() {
    Student s1;

    // Assign values
    s1.rollNo = 101;
    s1.name = "Ali";
    s1.marks = 88.5;

    // Access and display
    cout << "Roll No: " << s1.rollNo << endl;
    cout << "Name: " << s1.name << endl;
    cout << "Marks: " << s1.marks << endl;

    return 0;
}