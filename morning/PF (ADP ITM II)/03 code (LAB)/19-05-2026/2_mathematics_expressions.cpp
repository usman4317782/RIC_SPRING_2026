#include <iostream>
#include <string>
using namespace std;
int main()
{
	//user name
	int firstNumber, secondNumber, sum, subtract, multiplication;
	float div, mod;
	cout << "Enter first number: ";
	cin >> firstNumber;
	
	cout << "Enter second number: ";
	cin >> secondNumber;
	
	sum = firstNumber + secondNumber;
	subtract = firstNumber - secondNumber;
	multiplication = firstNumber * secondNumber;
	div = firstNumber / secondNumber;
	mod = firstNumber % secondNumber;

	
	cout << "The sum of " << firstNumber << " and " << secondNumber << " is " << sum << endl;
	cout << "The subtraction of " << firstNumber << " and " << secondNumber << " is " << subtract << endl;
	cout << "The multiplication of " << firstNumber << " and " << secondNumber << " is " << multiplication << endl;
		
	
	return 0;
}