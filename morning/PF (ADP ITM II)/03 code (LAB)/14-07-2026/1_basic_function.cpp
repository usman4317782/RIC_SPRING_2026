#include <iostream>
using namespace std;
//function define
void Greeting()
{
	cout << "Greeting function call" << endl;
}

int AddTwoNumber(int a, int b)
{
	return a + b;
}

bool isEven(int a)
{
	if(a % 2 == 0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

int main()
{
	int number, isEven_output;
	cout << "Enter the Number: ";
	cin >> number;
	
	isEven_output = isEven(number);
	
	if(isEven_output == 0)
	{
		cout << "Number is odd";
	}
	else
	{
		cout << "Number is even";
	}
	
//	int num1, num2;
//	cout << "Enter First Number: ";
//	cin >> num1;
//	cout << "Enter Second Number: ";
//	cin >> num2;
	
	//function call
//	Greeting();
//	cout << AddTwoNumber(num1, num2);
	return 0;
}