#include <iostream>
using namespace std;

// Enumeration
enum Day {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday
};

int main() {
    Day today;

    today = Wednesday;

    cout << "Enum value (integer): " << today << endl;

    return 0;
}