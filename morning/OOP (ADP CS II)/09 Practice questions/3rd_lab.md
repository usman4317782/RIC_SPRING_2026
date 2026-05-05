```markdown
# 🧪 C++ Lab Manual  
## Topic: Structures, Nested Structures, Variables, and Classes

---

## 🔹 Part A: Structures, Nested Structures, Local & Global Variables

---

### ✅ Lab 1: Student Record using Structure

**Objective:**  
To understand how to define and use structures.

**Problem Statement:**  
Create a structure `Student` with the following members:
* `name`
* `rollNumber`
* `marks` (for 3 subjects)

**Tasks:**
1. Input data for 3 students.
2. Calculate total marks for each student.
3. Calculate average marks.
4. Display complete student details.

---

### ✅ Lab 2: Nested Structure (Employee & Address)

**Objective:**  
To learn nested structures.

**Problem Statement:**  
Create the following structures:

```cpp
struct Address {
    string city;
    string street;
};

struct Employee {
    int id;
    string name;
    Address addr;
};
```

**Tasks:**
1. Input data for 2 employees.
2. Display employee details.
3. Print full address in one line.

---

### ✅ Lab 3: Structure with Function (Local Variables)

**Objective:**  
To understand passing structures to functions and using local variables.

**Problem Statement:**  
Create a structure `Rectangle` with the following members:
* `length`
* `width`

**Tasks:**
1. Write a function to calculate the area.
2. Use local variables inside the function.
3. Pass the structure to the function.
4. Display the calculated area.

---

### ✅ Lab 4: Global vs Local Variables

**Objective:**  
To understand the scope of variables.

**Problem Statement:**  
Demonstrate the difference between global and local variables.

**Tasks:**
1. Declare a global variable: 
   ```cpp
   int count = 10;
   ```
2. Create a function with a local variable of the same name (`count`).
3. Print both values.
4. Observe and explain the output.

---

### ✅ Lab 5: Structure Array with Search

**Objective:**  
To work with arrays of structures.

**Problem Statement:**  
Create a structure `Book` with the following members:
* `title`
* `author`
* `price`

**Tasks:**
1. Store details of 5 books.
2. Search for a book using its title.
3. Display details if found.
4. Display a "not found" message if it does not exist.

---

## 🔹 Part B: Classes, Objects, Data Members & Methods (Public Only)

---

### ✅ Lab 6: Basic Class - Student

**Objective:**  
To understand basic class and object usage.

**Problem Statement:**  
Create a class `Student`:
* **Data Members:** `name`, `rollNo`, `marks`
* **Methods:** `input()`, `display()`

**Tasks:**
1. Create 2 objects of the `Student` class.
2. Input data using the `input()` method.
3. Display student details using the `display()` method.

---

### ✅ Lab 7: Class - Calculator

**Objective:**  
To implement multiple methods in a class.

**Problem Statement:**  
Create a class `Calculator`:
* **Data Members:** `num1`, `num2`
* **Methods:** `add()`, `subtract()`, `multiply()`, `divide()`

**Tasks:**
1. Input two numbers.
2. Perform all operations calling the respective methods.
3. Display the results.

---

### ✅ Lab 8: Class - Bank Account

**Objective:**  
To simulate a simple banking system.

**Problem Statement:**  
Create a class `BankAccount`:
* **Data Members:** `accountNumber`, `balance`
* **Methods:** `deposit()`, `withdraw()`, `displayBalance()`

**Tasks:**
1. Create an account object.
2. Perform a deposit operation.
3. Perform a withdrawal operation.
4. Display the updated balance.

---

### ✅ Lab 9: Class with Multiple Objects

**Objective:**  
To handle multiple objects using arrays.

**Problem Statement:**  
Create a class `Car`:
* **Data Members:** `brand`, `price`

**Tasks:**
1. Create an array of 3 `Car` objects.
2. Input car details for all objects.
3. Display all car details.
4. Find and display the most expensive car.

---

### ✅ Lab 10: Class - Simple Interest Calculator

**Objective:**  
To implement a class with calculation logic.

**Problem Statement:**  
Create a class `Interest`:
* **Data Members:** `principal`, `rate`, `time`
* **Method:** `calculateSI()`

**Tasks:**
1. Input values using an object.
2. Calculate simple interest using the formula: `(P * R * T) / 100`
3. Display the result.
```