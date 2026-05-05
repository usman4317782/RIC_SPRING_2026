# C++ Programming — Quiz & Assignment
### Topics: Variables · Data Types · Arithmetic Operators · Scope · I/O · `sizeof`

---

## 📝 QUIZ — Real-Time Debugging Scenario

| | |
|---|---|
| **Topic Coverage** | Variables, Data Types, Arithmetic Operators, Integer Division, Assignment Statements, Comments |
| **Marks** | 10 |
| **Time Allowed** | 15 minutes |

---

### Background

You are developing a **climate control module** for a smart greenhouse. A junior programmer wrote a function to convert a Celsius temperature reading — received from a wireless sensor as an integer — to Fahrenheit for display on a dashboard.

The sensor is currently reporting **25 °C**.

---

### Faulty Code

```cpp
int celsius = 25;
double fahrenheit;
fahrenheit = celsius * (9 / 5) + 32;
cout << "Fahrenheit: " << fahrenheit;
```

When the dashboard renders the output, it shows **57 °F**, but the expected value is **77 °F**.

> ⚠️ **Real-World Stakes:** The greenhouse's fogging system depends on a correct temperature reading. A 20-degree error could **ruin the crop**.

---

### Questions

**Q1. (4 marks)**
Explain **precisely** why the output is `57 °F` instead of `77 °F`.
Your answer must reference what happens inside the parentheses `(9 / 5)` and the C++ rule that causes it.

> _Write your answer here:_
>
> _______________________________________________________________
>
> _______________________________________________________________
>
> _______________________________________________________________

---

**Q2. (4 marks)**
**Without introducing any new variables**, rewrite **only** the faulty assignment statement so that `fahrenheit` correctly receives `77.0` when `celsius = 25`.

```cpp
// Your corrected line:
fahrenheit = _________________________________________;
```

---

**Q3. (2 marks)**
In **one sentence**, state the general C++ rule that this bug demonstrates.

> _Write your answer here:_
>
> _______________________________________________________________
>
> _______________________________________________________________

---
---

## 📋 ASSIGNMENT — Smart Home Energy Monitor System

| | |
|---|---|
| **Topics Covered** | Input/Output · `#include` · `cout` / `cin` · Data Types (`int`, `float`, `double`, `char`, `bool`, `string`) · `sizeof` · Variable Assignment & Initialization · Scope · Arithmetic Operators · Comments · `const` |
| **Total Marks** | 50 |
| **Submission** | `.cpp` source file + console output screenshot |
| **Due Date** | *(as announced by instructor)* |

---

### Scenario

You have been hired by an **IoT startup** to build a **Smart Home Energy Monitor** console application. The program reads sensor data from a single room, processes it, and generates a daily snapshot report.

The hardware sends raw readings once per minute, but for this prototype you will **simulate a single user entry via the keyboard**. The system must be fault-sensitive because the utility company will later link it to **dynamic pricing**.

---

### Functional Requirements

Complete **all** of the following requirements in a single `.cpp` file.

---

#### Requirement 1 — Declare and Initialise Constants *(4 marks)*

Declare the following named constants at the top of `main()`:

| Constant Name | Value | Meaning |
|---|---|---|
| `MAX_SAFE_TEMP_F` | `100.0` | High-temperature alert threshold (°F) |
| `MAX_POWER_WATTS` | `1500.0` | Maximum appliance load limit (W) |

---

#### Requirement 2 — Accept Sensor Inputs *(8 marks)*

Prompt the user to enter the following values and store them in appropriately typed variables:

| Variable | Data Type | Description | Example Input |
|---|---|---|---|
| `deviceName` | `string` | Room identifier | `"LivingRoom"` |
| `temperatureC` | `float` or `double` | Celsius temperature from sensor | `29.5` |
| `humidity` | `int` | Relative humidity (%) | `55` |
| `power` | `float` or `double` | Current power draw (Watts) | `1620.0` |
| `status` | `char` | `'A'` = Active, `'S'` = Standby | `'A'` |

---

#### Requirement 3 — Convert Temperature *(6 marks)*

Compute `temperatureF` (double) from `temperatureC` using the formula:

$$F = C \times \frac{9}{5} + 32$$

> ✅ Your expression **must avoid the integer-division pitfall** demonstrated in the Quiz.
> Use a floating-point literal (e.g., `9.0 / 5.0` or `1.8`) to force correct division.

---

#### Requirement 4 — Compute Heat Index *(8 marks)*

Apply the **simplified heat index model** below:

- **Condition:** Only when `humidity > 40` **AND** `temperatureC > 27.0`
- **Formula:**

$$\text{heatIndexC} = \text{temperatureC} + \frac{(\text{humidity} - 40)}{10.0} \times \frac{(\text{temperatureC} - 27.0)}{5.0} \times 2.0$$

- **Otherwise:** Set `heatIndexC = temperatureC`

Also convert the final `heatIndexC` to Fahrenheit (`heatIndexF`) using the same formula as Requirement 3.

> 💡 You may use a temporary `bool` flag or the ternary operator (`? :`) to handle the conditional — explicit `if` statements are not required for this assignment.

---

#### Requirement 5 — Determine Alert Status *(4 marks)*

Declare a `bool` variable named `alarm`.

Set `alarm` to `true` if **either** of the following conditions is met:
- `temperatureF > MAX_SAFE_TEMP_F`
- `power > MAX_POWER_WATTS`

Otherwise, `alarm` is `false`.

---

#### Requirement 6 — Output a Formatted Report *(8 marks)*

Display a neatly labelled summary. All floating-point numbers must appear to **two decimal places**. Show `alarm` as `"YES"` or `"NO"`.

**Expected output format:**

```
=== Smart Home Energy Report ===
Device Name : LivingRoom
Temperature : 29.50 C / 85.10 F
Humidity    : 55 %
Heat Index  : 33.28 C / 91.90 F
Power Draw  : 1620.00 W
Status      : A
ALARM       : YES
================================
```

---

#### Requirement 7 — Demonstrate Variable Scope *(6 marks)*

Immediately **after** reading `temperatureC`, open a new block using braces `{ }`:

```cpp
// Inside the block — this works fine
{
    double tempKelvin = temperatureC + 273.15;
    cout << "Kelvin (inside block): " << tempKelvin << endl;
}

// Outside the block — scope error demonstrated below:
// cout << tempKelvin << endl;
// ERROR: 'tempKelvin' was declared inside the block above.
// In C++, a variable only exists within the scope (the { }) where it was declared.
// Attempting to use it outside that scope causes a compilation error.
```

Your submission must include:
- [x] A working `cout` of `tempKelvin` **inside** the block
- [x] The **commented-out** `cout` line **outside** the block
- [x] A **comment** above the commented line explaining why it fails, using the word **"scope"**

---

#### Requirement 8 — Investigate Data Type Sizes *(4 marks)*

After the main report, use the `sizeof` operator to print the memory size (in bytes) of each data type you used:

```
=== Data Type Sizes ===
int    : X bytes
float  : X bytes
double : X bytes
char   : X bytes
bool   : X bytes
string : X bytes  // Note: sizeof(string) gives the size of the string object
                  // itself, NOT the length of the stored text.
```

---

#### Requirement 9 — Source Code Quality *(2 marks)*

Your source file must include:

- [ ] **Header comment block** at the top with your name, date, and a brief program description
- [ ] Meaningful variable names and consistent indentation
- [ ] Inline comments explaining each computational step and the purpose of the scope demonstration

---

### Test Case for Submission

Run your program with the following input and include a **screenshot of the console output** in your submission:

```
Device Name  : Kitchen
TemperatureC : 31.2
Humidity     : 62
Power        : 1580.0
Status       : A
```

**Before submitting, verify manually:**
- [ ] Does the alarm trigger? *(Check both temperature and power against the constants.)*
- [ ] Does the heat index formula apply? *(Check: humidity > 40 AND temperatureC > 27.0)*
- [ ] Are all floating-point values shown to exactly two decimal places?

---

### Marks Breakdown

| Requirement | Description | Marks |
|:-----------:|---|:---:|
| 1 | Constants declared correctly | 4 |
| 2 | All five inputs read with correct types | 8 |
| 3 | Correct temperature conversion (no integer division) | 6 |
| 4 | Heat index computed with correct conditional logic | 8 |
| 5 | `alarm` bool set correctly | 4 |
| 6 | Formatted report output | 8 |
| 7 | Scope demonstration (block, working cout, commented line, explanation) | 6 |
| 8 | `sizeof` output for all types | 4 |
| 9 | Code quality, comments, naming | 2 |
| | **Total** | **50** |

---

### Academic Integrity

> All submitted code must be your own work. Copying from classmates or online sources without attribution is a violation of academic policy and will result in a grade of zero for the assignment.

---

*End of Quiz & Assignment — Good Luck!* 🎯