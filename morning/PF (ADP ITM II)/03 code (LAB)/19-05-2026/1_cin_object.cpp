#include <iostream>
#include <string>
using namespace std;
int main()
{
	//user name
	string name;
	cout << "Enter your name: ";
	getline(cin, name);
	
	cout << "Your name: " << name;
	
	
	return 0;
}