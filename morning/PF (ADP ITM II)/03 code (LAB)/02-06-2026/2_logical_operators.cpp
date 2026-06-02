#include <iostream>
using namespace std;
int main()
{
	int a = 4;
	int b = 5;
	int c = 10;
	
	//email -> password -> OTP -> login -> logical + comparison
	
	cout << (a > b) << endl;

	cout << (c > b) << endl;
	
	cout << (!(a > b)); //0 -> 1 || 1 -> 0
	
	return 0;
}