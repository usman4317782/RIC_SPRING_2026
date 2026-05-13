//object as function argument

#include <iostream>
using namespace std;

class Point{
    public:
        int x;
        int y;

};
void display(Point point){
    cout << "Point(" << point.x << ", " << point.y << ")" << endl;
}

int main()
{
    int firstNumber, secondNumber;
    cout << "Enter first and second number";
    cin >> firstNumber >> secondNumber; 

    Point point = {firstNumber, secondNumber};
    return 0;
}