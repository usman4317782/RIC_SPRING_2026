//constructor

#include <iostream>
using namespace std;

class Point{
    public:
        int x;
        int y;
        Point(int fnum, int snum){
            x = fnum;
            y = snum;
            cout << x+y << endl;
        }
};

int main()
{
    Point point(10, 20);
    
    return 0;
}