//In a real shop, you have products. 
//When you apply a discount or restock items, 
//you need to modify the actual product in the warehouse—not a copy. 
//Pointers let you do this directly, without copying huge objects.
#include <iostream>
using namespace std;

class Product {
public:
    string name;
    double price;
    int stock;

    void display() {
        cout << name << " | $" << price << " | Stock: " 
		<< stock << endl;
    }
};

// Real-world function: modifies the actual product using a pointer
void applyDiscount(Product* p, double percent) {
	// Updates original object
    p->price -= p->price * (percent / 100); 
}

void restock(Product* p, int qty) {
    p->stock += qty; // Updates original object
}

int main() {
    // Dynamically create products 
	//(like adding items to the warehouse)
    Product* laptop = new Product{"Gaming Laptop", 1500.0, 10};
    Product* mouse  = new Product{"Wireless Mouse", 30.0, 50};

    // Real business actions using pointers
    applyDiscount(laptop, 20); // 20% off sale
    restock(mouse, 100);       // Warehouse just got 100 more mice

    // Check the updated inventory 
	//	(the original objects are changed!)
    laptop->display(); // Gaming Laptop | $1200 | Stock: 10
    mouse->display();  // Wireless Mouse | $30 | Stock: 150

    // Clean up
    delete laptop;
    delete mouse;

    return 0;
}