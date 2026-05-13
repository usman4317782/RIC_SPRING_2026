#include <iostream>
using namespace std;

class Car{
    public:
        string brand;
        string model;
        int year;

        void start(){
            cout << "Car started" << endl;
        }

        void stop(){
            cout << "Car stopped" << endl;
        }

};

int main()
{
    Car car1;
    car1.brand = "Toyota";
    return 0;
}