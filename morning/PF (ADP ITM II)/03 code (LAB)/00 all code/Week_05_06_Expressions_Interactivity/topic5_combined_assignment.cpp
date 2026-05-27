// Week 5-6: Expression and Interactivity
// Topic 5: Combined Assignment and Multiple Assignment
// Explains shorthand assignment operators and multiple assignment on a single line.

#include <iostream>
using namespace std;

int main() {
    // 1. Multiple Assignment
    // C++ evaluates assignment from right to left.
    int a, b, c;
    a = b = c = 10; // First c=10, then b=c, then a=b. All variables get 10.
    
    cout << "Multiple Assignment: a = " << a << ", b = " << b << ", c = " << c << endl << endl;

    // 2. Combined Assignment Operators (Shorthands)
    // Operator  Equivalent to:
    // x += y     x = x + y
    // x -= y     x = x - y
    // x *= y     x = x * y
    // x /= y     x = x / y
    // x %= y     x = x % y

    int value = 20;
    cout << "Initial value: " << value << endl;

    value += 10; // value = value + 10 (30)
    cout << "After value += 10: " << value << endl;

    value -= 5;  // value = value - 5 (25)
    cout << "After value -= 5 : " << value << endl;

    value *= 2;  // value = value * 2 (50)
    cout << "After value *= 2 : " << value << endl;

    value /= 10; // value = value / 10 (5)
    cout << "After value /= 10: " << value << endl;

    value %= 3;  // value = value % 3 (2)
    cout << "After value %= 3 : " << value << endl;

    return 0;
}
