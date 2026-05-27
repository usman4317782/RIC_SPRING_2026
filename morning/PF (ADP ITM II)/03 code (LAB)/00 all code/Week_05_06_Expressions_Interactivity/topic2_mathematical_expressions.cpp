// Week 5-6: Expression and Interactivity
// Topic 2: Mathematical Expressions (Precedence & Associativity)
// Shows how algebraic formulas are written in C++ and how order of operations works.

#include <iostream>
using namespace std;

int main() {
    // C++ Order of Operations (Precedence):
    // 1. Parentheses ()
    // 2. Multiplication (*), Division (/), Modulus (%) - evaluated left to right
    // 3. Addition (+), Subtraction (-) - evaluated left to right

    double result1 = 5 + 2 * 4;   // 2*4 happens first = 8. Then 5 + 8 = 13.
    double result2 = (5 + 2) * 4; // Parentheses happen first = 7. Then 7 * 4 = 28.

    cout << "5 + 2 * 4   = " << result1 << endl;
    cout << "(5 + 2) * 4 = " << result2 << endl << endl;

    // Algebraic expression translation:
    // Translate:  y = (x + 2) / (z * 3)
    double x = 4.0;
    double z = 2.0;
    double y = (x + 2.0) / (z * 3.0); // Correct grouping
    
    cout << "For x = 4.0 and z = 2.0:" << endl;
    cout << "Algebraic translation: (x + 2) / (z * 3) = " << y << endl;

    return 0;
}
