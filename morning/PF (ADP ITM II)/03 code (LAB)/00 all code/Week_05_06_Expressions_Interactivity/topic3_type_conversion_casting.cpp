// Week 5-6: Expression and Interactivity
// Topic 3: Type Conversion and Casting
// Explains implicit promotion/demotion and how to use static_cast to convert types manually.

#include <iostream>
using namespace std;

int main() {
    // 1. Implicit Conversion (Promotion)
    // C++ automatically promotes smaller numeric types to larger ones to prevent data loss.
    int numInt = 5;
    double numDouble = 2.5;
    
    double result = numInt + numDouble; // numInt is promoted to double (5.0) before calculation.
    cout << "Implicit promotion (int + double): " << result << endl;

    // 2. Implicit Conversion (Demotion)
    // When assigning a double to an int, the fractional part is truncated (thrown away).
    int truncated = 9.99; // Value becomes 9.
    cout << "Implicit demotion/truncation (9.99 to int): " << truncated << endl;

    // 3. Explicit Type Casting using static_cast
    // Manually forces a variable to temporarily act as another type.
    int pointsScored = 17;
    int gamesPlayed = 5;
    
    // Without casting: Integer division results in 3
    double averageScoreWrong = pointsScored / gamesPlayed;
    
    // With casting: converts pointsScored to double (17.0), enabling floating-point division
    double averageScoreCorrect = static_cast<double>(pointsScored) / gamesPlayed;

    cout << "\nWrong Average (integer division): " << averageScoreWrong << endl;
    cout << "Correct Average (with static_cast) : " << averageScoreCorrect << endl;

    return 0;
}
