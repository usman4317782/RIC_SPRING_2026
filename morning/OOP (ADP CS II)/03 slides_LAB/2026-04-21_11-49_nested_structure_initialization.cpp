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
    // Nested initialization
    Student s1 = {102, "Ahmed", {"Karachi", "Pakistan"}};

    cout << "Roll No: " << s1.rollNo << endl;
    cout << "Name: " << s1.name << endl;
    cout << "City: " << s1.addr.city << endl;
    cout << "Country: " << s1.addr.country << endl;

    return 0;
}