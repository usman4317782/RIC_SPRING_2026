# 🧪 C++ Lab Exercises  
## Topic: Structures, Nested Structures, Variables, and Classes

---

# 🔹 Part A: Structures, Nested Structures, Local & Global Variables

## **Lab 1: Student Record using Structure**
Create a structure `Student` with the following members:
- Name
- Roll Number
- Marks in 3 subjects

### Tasks:
- Input data for 3 students  
- Calculate total and average marks  
- Display student details with results  

---

## **Lab 2: Nested Structure (Employee & Address)**
Create the following structures:


struct Address {
    string city;
    string street;
};

struct Employee {
    int id;
    string name;
    Address addr;
};