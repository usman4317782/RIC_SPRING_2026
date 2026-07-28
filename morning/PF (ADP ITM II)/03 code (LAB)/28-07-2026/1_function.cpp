#include <iostream>
using namespace std;

/*returnType functionName(parametric){
	//body of the function
}*/
//function define
int addTwoNumber(int firstNumber, int secondNumber){
	return firstNumber + secondNumber;
}

int main()
{
	int num1, num2;
	cout << "Enter First Number: ";
	cin >> num1;
	
	cout << "Enter Second Number: ";
	cin >> num2;
	//define + call
	cout << addTwoNumber(num1, num2);
	return 0;
}