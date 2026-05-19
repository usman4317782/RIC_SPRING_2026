-- 1. Selecting all columns of all rows
-- Retrieves every column for every row (caution: returns 1500 rows).
-- Use LIMIT if you only want a preview.

sql
-- All columns, all rows (full table)
SELECT * FROM employees;

-- Preview first 20 rows
SELECT * FROM employees LIMIT 20;
2. Selecting specific columns of all rows
Pick only the columns you need. Results are still 1500 rows, but narrower.

sql
SELECT first_name, last_name, salary FROM employees;
-- or with LIMIT
SELECT first_name, last_name, department FROM employees LIMIT 15;
3. Arithmetic Expressions
Perform calculations on numeric columns. Arithmetic with NULL returns NULL.

sql
-- Basic arithmetic: annual salary (monthly salary * 12)
SELECT 
    first_name,
    salary,
    salary * 12 AS annual_salary
FROM employees
LIMIT 20;

-- Multiple operations: 10% raise, plus a fixed bonus, then 5% tax deduction
SELECT
    first_name,
    salary,
    salary * 1.10 AS after_raise,
    (salary * 1.10) + 5000 AS with_bonus,
    ((salary * 1.10) + 5000) * 0.95 AS after_tax
FROM employees
LIMIT 15;

-- Using modulus: check even/odd IDs
SELECT
    id,
    first_name,
    id % 2 AS remainder
FROM employees
LIMIT 10;
4. Null values
Test NULL, replace it, or filter rows containing NULL.

sql
-- Find rows where salary is missing
SELECT id, first_name, last_name, salary
FROM employees
WHERE salary IS NULL
LIMIT 10;

-- Using IFNULL to replace NULL with a default value (0)
SELECT
    first_name,
    salary,
    IFNULL(salary, 0) AS salary_or_zero
FROM employees
LIMIT 30;

-- Using COALESCE to try multiple columns (only salary can be NULL here)
SELECT
    first_name,
    COALESCE(salary, 50000) AS salary_with_fallback
FROM employees
LIMIT 30;

-- Arithmetic with NULL: salary * 1.1 will be NULL for missing salaries
SELECT
    first_name,
    salary,
    salary * 1.1 AS increase_null_proof
FROM employees
LIMIT 30;
5. Column Aliases
Rename column headings for clarity. Use AS or just a space.
(If alias contains spaces or special characters, enclose it in double quotes or backticks.)

sql
-- Simple alias
SELECT 
    first_name AS "First Name",
    last_name  AS "Last Name",
    salary * 12 AS "Annual Salary"
FROM employees
LIMIT 20;

-- Aliases without AS (still works)
SELECT 
    first_name "First Name",
    last_name  "Last Name",
    department Dept
FROM employees
LIMIT 15;

-- Alias used in ORDER BY (but NOT in WHERE clause)
SELECT 
    first_name,
    salary AS base_salary,
    salary * 1.2 AS target_salary
FROM employees
ORDER BY target_salary DESC
LIMIT 10;
6. Concatenation Operator
MySQL uses the CONCAT() function (the || operator is not standard unless PIPES_AS_CONCAT SQL mode is enabled).
I'll show both methods, but CONCAT() is recommended.

sql
-- Standard MySQL: CONCAT function
SELECT 
    CONCAT(first_name, ' ', last_name) AS full_name
FROM employees
LIMIT 20;

-- If you enable pipes (optional, not needed)
SET sql_mode = 'PIPES_AS_CONCAT';
SELECT first_name || ' ' || last_name AS full_name FROM employees LIMIT 20;
-- Turn it off afterward (optional)
SET sql_mode = DEFAULT;

-- Concatenate with literals and column values
SELECT 
    CONCAT('Employee ', id, ': ', first_name, ' ', last_name) AS description
FROM employees
LIMIT 10;
7. Literal Character Strings
Include fixed text in your result set. String literals are enclosed in single quotes.

sql
-- A literal string as a column
SELECT 
    first_name,
    'works in' AS status,
    department
FROM employees
LIMIT 15;

-- Combining literal with CONCAT
SELECT 
    CONCAT(first_name, ' has a salary of ', IFNULL(salary, 'unknown')) AS info
FROM employees
LIMIT 10;
8. Eliminating Duplicate Rows
Use DISTINCT to remove duplicates. Works on one or multiple columns.

sql
-- All unique department names
SELECT DISTINCT department
FROM employees;

-- Unique combinations of department and last name
SELECT DISTINCT department, last_name
FROM employees
LIMIT 30;

-- Compare with non-distinct version (1500 rows)
SELECT department, last_name
FROM employees
LIMIT 30;

-- Distinct with WHERE: show distinct departments for employees with salary > 80000
SELECT DISTINCT department
FROM employees
WHERE salary > 80000;
