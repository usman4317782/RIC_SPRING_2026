# Python Practice Questions: Input, Output, Typecasting, Loops, and Conditionals

This document contains **20 practice problems** divided into two categories:
1. **10 Intermediate Level Questions** – focused directly on the core concepts.
2. **10 Real‑Life Problem Solving Questions** – applying the same concepts in practical scenarios.

Each problem requires you to use `input()`, `print()`, **explicit typecasting** (`int()`, `float()`, `str()`), loops (`for`, `while`), and `if-else` statements.  
Pay attention to **implicit typecasting** as well (e.g., concatenating strings or mixing numeric types in expressions).

---

## Intermediate Level Questions

### Q1. Sum of First N Natural Numbers
Write a program that:
- Takes an integer `N` as input.
- Computes the sum of the first `N` natural numbers using a `for` loop.
- Prints the result.

**Example**  
Input: `5` → Output: `Sum = 15`

---

### Q2. Count Vowels in a String
Write a program that:
- Asks the user for a sentence.
- Counts the number of vowels (a, e, i, o, u) using a loop and `if` conditions.
- Prints the total count.

---

### Q3. Maximum in a List without max()
Write a program that:
- Reads a line of numbers separated by spaces (e.g., `"12 7 45 3"`).
- Converts them into integers (explicit typecasting).
- Finds the largest number **without** using the built‑in `max()` function.
- Prints the maximum.

---

### Q4. Multiplication Table
Write a program that:
- Takes an integer from the user.
- Prints its multiplication table from 1 to 10 using a loop.

**Example** (for `3`):  
`3 x 1 = 3` … `3 x 10 = 30`

---

### Q5. Prime Number Checker
Write a program that:
- Takes a positive integer.
- Checks whether it is a prime number using a loop and `if-else`.
- Prints `"Prime"` or `"Not Prime"`.

---

### Q6. Reverse a String without Slicing
Write a program that:
- Takes a string as input.
- Reverses it using a loop (not `[::-1]`).
- Prints the reversed string.

---

### Q7. Temperature Converter (Celsius ↔ Fahrenheit)
Write a program that:
- Asks the user for a temperature in Celsius (can be a float).
- Explicitly converts the input to `float`.
- Converts it to Fahrenheit using the formula `F = C * 9/5 + 32`.
- Prints the result formatted to two decimal places.

---

### Q8. Factorial using While Loop
Write a program that:
- Takes a non‑negative integer.
- Calculates its factorial using a `while` loop.
- Prints `"Factorial of x is y"`.

---

### Q9. All Divisors of a Number
Write a program that:
- Takes an integer.
- Prints **all** its positive divisors (numbers that divide it without a remainder) using a loop and `if`.

**Example** Input: `12` → Output: `1 2 3 4 6 12`

---

### Q10. Basic Calculator
Write a program that:
- Asks the user for two numbers and an operator (`+`, `-`, `*`, `/`).
- Explicitly converts the numbers to `float`.
- Uses `if-elif-else` to perform the chosen operation.
- Handles division by zero gracefully.
- Prints the result.

---

## Real‑Life Problem Solving Questions

### Q11. ATM Withdrawal Simulator
Simulate an ATM machine:
- Start with an initial balance (e.g., ₹10,000, stored as `float`).
- Repeatedly display a menu:  
  `1. Check Balance  2. Withdraw  3. Exit`
- Use a `while` loop until the user chooses Exit.
- For withdrawal: ask the amount, check if sufficient balance, update balance and print success/failure.  
- Explicitly convert the withdrawal input to `float`.  
- Print the final balance when exiting.

---

### Q12. Student Grade Calculator
Write a program that:
- Asks the user to enter marks (out of 100) for 5 subjects (use a loop).
- Stores them after explicit conversion to `float`/`int`.
- Calculates the average.
- Assigns a grade based on the average:  
  `≥90 → A, 80-89 → B, 70-79 → C, 60-69 → D, <60 → F`
- Prints the average and the grade.

---

### Q13. Electricity Bill Calculator
Electricity charges are tiered:
- First 100 units: ₹5 per unit
- Next 100 units (101–200): ₹7 per unit
- Above 200 units: ₹10 per unit
- Minimum bill: ₹100 (if calculated bill is less than ₹100, charge ₹100)
Write a program that:
- Takes the number of units consumed (float).
- Calculates the total bill.
- Prints the bill amount.

---

### Q14. Number Guessing Game
Write a simple guessing game:
- The program secretly picks a random number between 1 and 100 (use `random.randint`).
- The user tries to guess the number.
- After each guess, give a hint: `"Too high"`, `"Too low"`, or `"Correct"`.
- Count the number of attempts and display it when the guess is correct.
- Use a `while` loop until the correct guess.

---

### Q15. Password Strength Checker
Write a program that:
- Asks the user to enter a password.
- Checks the password for:
  - Length at least 8 characters
  - Contains at least one uppercase letter
  - Contains at least one lowercase letter
  - Contains at least one digit
  - Contains at least one special character (`!@#$%^&*`)
- Uses loops and `if-else` to test each character.
- Prints `"Strong password"` or `"Weak password"` with the reason(s).

---

### Q16. Shopping Cart Total
Write a program that:
- Repeatedly asks the user to enter the price of an item (or type `0` to finish).
- Stores each price (converted to `float`) and accumulates the total.
- After the loop, if the total is more than ₹5000, give a 10% discount.
- Print the final amount to be paid (after discount, if applicable).

---

### Q17. Fibonacci Sequence Generator
Write a program that:
- Asks the user for a number `N`.
- Prints the first `N` terms of the Fibonacci sequence using a loop.
- The first two terms are 0 and 1.

**Example:** `N=6` → `0 1 1 2 3 5`

---

### Q18. Seconds to Hours:Minutes:Seconds Converter
Write a program that:
- Takes an integer representing total seconds.
- Converts it into the format `HH:MM:SS`.
- Uses integer division and modulus operators.
- Prints the result.

**Example:** Input `3665` → `1:1:5` (or `01:01:05`)

---

### Q19. Student Attendance Tracker
For a 30‑day month:
- Ask the number of days the student was present (input as integer).
- Calculate attendance percentage.
- If attendance ≥75%, print `"Eligible for exam"`, else print `"Not eligible"`.
- (Optional) Use a loop to simulate entering daily attendance P/A.

---

### Q20. Vending Machine Simulator
Create a simple vending machine:
- Display a menu:  
  `1. Water (Rs.20)  2. Juice (Rs.35)  3. Chips (Rs.25)`
- Ask the user to choose an item and then insert money (as float).
- If the money is enough, give the item and return change; if not enough, ask for more or cancel.
- Use a loop for the money‑insertion process.
- Print the final message: item dispensed or transaction cancelled, and the change returned.
