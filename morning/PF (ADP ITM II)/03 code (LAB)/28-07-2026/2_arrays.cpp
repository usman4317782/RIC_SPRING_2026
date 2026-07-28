#include <iostream>
using namespace std;

int main()
{
	//array
//	int num1, num2, num3, num4, num5, num6, num7, num8, num9, num10;
	//in-bound
	int numbers[10] = {20,15,16,18,24,100,12,33,45,45};
//	cout << numbers[3];
//	cout << numbers[7];
	for(int number : numbers){
		cout << number << endl;
	}
	return 0;
}