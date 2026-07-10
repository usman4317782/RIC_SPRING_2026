# W12 — Late Binding, Abstract Classes, Virtual Destructors, Virtual Base Classes, Friend Functions/Classes

---

## L23.1 Late Binding (Dynamic Binding)

**Definition:**
Late binding (a.k.a. dynamic binding or runtime polymorphism) means the decision about *which function to call* is made **at runtime**, based on the actual object type, not the pointer/reference type. In C++, this is achieved using **virtual functions**. The opposite is **early binding** (compile-time), which happens with normal (non-virtual) function calls.

**Real-world example:**
Think of a universal remote control. Pressing the "power" button sends the same command, but the *actual behavior* depends on which device (TV, AC, Speaker) is currently connected — decided at the moment you press it (runtime), not when the remote was manufactured (compile time).

**Basic C++ Example:**
```cpp
#include <iostream>
using namespace std;

class Animal {
public:
    virtual void sound() { cout << "Animal makes a sound\n"; }
};

class Dog : public Animal {
public:
    void sound() override { cout << "Dog barks\n"; }
};

int main() {
    Animal* a = new Dog();
    a->sound();   // Output: Dog barks (decided at runtime -> late binding)
    delete a;
}
```

---

## L23.2 Abstract Classes and Pure Virtual Functions

**Definition:**
- A **pure virtual function** is a virtual function with no body, declared using `= 0`. It forces derived classes to provide an implementation.
- An **abstract class** is a class that contains **at least one pure virtual function**. It cannot be instantiated directly — it only serves as a base/interface for derived classes.

**Real-world example:**
A "Shape" is an abstract concept — you can't draw "just a shape," you can only draw a specific shape like a Circle or Rectangle. "Shape" defines *what* must be done (calculate area) but not *how*.

**Basic C++ Example:**
```cpp
#include <iostream>
using namespace std;

class Shape {          // Abstract class
public:
    virtual double area() = 0;   // Pure virtual function
};

class Circle : public Shape {
    double radius;
public:
    Circle(double r) : radius(r) {}
    double area() override { return 3.14 * radius * radius; }
};

int main() {
    // Shape s;        // ERROR: cannot instantiate abstract class
    Shape* s = new Circle(5);
    cout << "Area: " << s->area() << endl;
    delete s;
}
```

---

## L23.3 Virtual Destructors

**Definition:**
A **virtual destructor** ensures that when a derived class object is deleted through a **base class pointer**, the derived class's destructor is called first, followed by the base class destructor — preventing **resource/memory leaks**. Without `virtual`, only the base destructor runs.

**Real-world example:**
Imagine a rental car company. If you return a "Vehicle" (base) but it's actually a "Truck" (derived) with a special trailer attached, proper checkout must release *both* the trailer (derived-specific resource) and the vehicle (base resource) — not just the vehicle.

**Basic C++ Example:**
```cpp
#include <iostream>
using namespace std;

class Base {
public:
    virtual ~Base() { cout << "Base Destructor\n"; }
};

class Derived : public Base {
public:
    ~Derived() { cout << "Derived Destructor\n"; }
};

int main() {
    Base* b = new Derived();
    delete b;
    // Output:
    // Derived Destructor
    // Base Destructor
}
```

---

## L24.1 Virtual Base Classes

**Definition:**
Used to solve the **diamond problem** in multiple inheritance. When two classes inherit from the same base class, and a fourth class inherits from both, the base class would normally get duplicated. Declaring the base class as **virtual** ensures only **one shared copy** of it exists in the final derived object.

**Real-world example:**
Imagine "Person" is a base class, and both "Student" and "Employee" inherit from "Person." A "TeachingAssistant" inherits from both Student and Employee. Without virtual inheritance, a TA would have two separate copies of "Person" data (name, age) — causing ambiguity. Virtual inheritance keeps just **one Person** shared by both paths.

**Basic C++ Example:**
```cpp
#include <iostream>
using namespace std;

class Person {
public:
    string name = "Ali";
};

class Student : virtual public Person {};
class Employee : virtual public Person {};

class TeachingAssistant : public Student, public Employee {};

int main() {
    TeachingAssistant ta;
    cout << ta.name << endl;  // No ambiguity, single copy of Person
}
```

---

## L24.2 Friend Function

**Definition:**
A **friend function** is a non-member function that is granted access to a class's **private and protected members**. It's declared inside the class using the `friend` keyword but defined outside, and is **not** part of the class itself.

**Real-world example:**
Think of a bank locker system. Normally only the account owner (class member) can access the locker (private data). But the bank manager (friend function) is given special permission to access it for verification purposes, without being an "owner" themselves.

**Basic C++ Example:**
```cpp
#include <iostream>
using namespace std;

class Account {
    double balance;
public:
    Account(double b) : balance(b) {}
    friend void showBalance(Account a);  // friend function declaration
};

void showBalance(Account a) {   // can access private 'balance'
    cout << "Balance: " << a.balance << endl;
}

int main() {
    Account acc(5000);
    showBalance(acc);
}
```

---

## L24.3 Friend Class

**Definition:**
A **friend class** is a class whose member functions are all granted access to another class's **private and protected members**. Useful when two classes are tightly coupled and need to cooperate closely.

**Real-world example:**
Think of a hospital system where a "Doctor" class needs full access to a "PatientRecord" class's private medical data, while other classes (like "Visitor") should not. Making Doctor a friend class of PatientRecord grants it that special access.

**Basic C++ Example:**
```cpp
#include <iostream>
using namespace std;

class PatientRecord {
    double bloodPressure = 120.5;
    friend class Doctor;   // Doctor class gets full access
};

class Doctor {
public:
    void checkRecord(PatientRecord p) {
        cout << "Blood Pressure: " << p.bloodPressure << endl;
    }
};

int main() {
    PatientRecord record;
    Doctor d;
    d.checkRecord(record);
}
```

---

## Quick Summary Table

| Concept | Keyword Used | Purpose |
|---|---|---|
| Late Binding | `virtual` | Decide function call at runtime |
| Abstract Class | `= 0` (pure virtual) | Define interface, prevent instantiation |
| Virtual Destructor | `virtual ~Class()` | Ensure proper cleanup in inheritance |
| Virtual Base Class | `virtual` (in inheritance list) | Avoid duplicate base in diamond problem |
| Friend Function | `friend` (function) | Grant outside function access to private data |
| Friend Class | `friend class` | Grant another class full access to private data |
