//The Scenario
//You have a checkout system. 
//You don’t know if the user will pay via Credit Card or PayPal
//until runtime. Polymorphism lets you handle both with one generic
// pointer.
#include <iostream>
using namespace std;

// Base class (interface)
class Payment {
public:
    virtual void pay(double amount) = 0; // Pure virtual
    virtual ~Payment() {}
};

// Real implementation 1
class CreditCard : public Payment {
public:
    void pay(double amount) override {
        cout << "Processing $" << amount << " via Credit Card network." << endl;
    }
};

// Real implementation 2
class PayPal : public Payment {
public:
    void pay(double amount) override {
        cout << "Processing $" << amount << " via PayPal API." << endl;
    }
};

int main() {
    Payment* transaction; // Generic pointer

    transaction = new CreditCard();
    transaction->pay(99.99); // Output: Processing $99.99 via Credit Card network.

    transaction = new PayPal();
    transaction->pay(49.50); // Output: Processing $49.50 via PayPal API.

    delete transaction;
    return 0;
}