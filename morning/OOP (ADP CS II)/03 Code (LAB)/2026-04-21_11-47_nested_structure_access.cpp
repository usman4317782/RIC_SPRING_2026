#include <iostream>
using namespace std;

struct Address {
    string city;
    string country;
};

struct Student {
    int rollNo;
    string name;
    Address addr;
};

int main() {
    Student s1;

    // Assign values
    s1.rollNo = 101;
    s1.name = "Ali";
    s1.addr.city = "Lahore";
    s1.addr.country = "Pakistan";

    // Access values
    cout << "Roll No: " << s1.rollNo << endl;
    cout << "Name: " << s1.name << endl;
    cout << "City: " << s1.addr.city << endl;
    cout << "Country: " << s1.addr.country << endl;

    return 0;
}