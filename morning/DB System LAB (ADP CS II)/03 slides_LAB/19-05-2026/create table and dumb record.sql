-- Use (and create if necessary) the practice database
CREATE DATABASE IF NOT EXISTS practice_db;
USE practice_db;

-- Drop the old table so we start fresh
DROP TABLE IF EXISTS employees;

-- Create the table (same structure as before)
CREATE TABLE employees (
    id         INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    salary     DECIMAL(10,2),   -- some will be NULL
    department VARCHAR(50),
    hire_date  DATE
);

-- Insert 1500 rows using a recursive CTE and some randomisation
INSERT INTO employees (id, first_name, last_name, salary, department, hire_date)
WITH RECURSIVE seq(n) AS (
    SELECT 1
    UNION ALL
    SELECT n + 1 FROM seq WHERE n < 1500
)
SELECT
    n AS id,
    -- Pick a random first name from a small list
    ELT(FLOOR(1 + RAND(1000+n) * 10),
        'Alice','Bob','Carol','Dave','Eve','Frank','Grace','Hank','Ivy','Jack') AS first_name,
    -- Pick a random last name from another list
    ELT(FLOOR(1 + RAND(2000+n) * 10),
        'Smith','Jones','Taylor','Brown','Wilson','Davies','Evans','Thomas','Johnson','Roberts') AS last_name,
    -- Salary: around 5% chance of NULL, otherwise a random value between 30000 and 150000
    CASE WHEN RAND(3000+n) < 0.05 THEN NULL
         ELSE ROUND(30000 + RAND(4000+n) * 120000, 2)
    END AS salary,
    -- Pick a random department
    ELT(FLOOR(1 + RAND(5000+n) * 5),
        'Engineering','Marketing','Sales','Human Resources','Finance') AS department,
    -- Random hire date between 2015-01-01 and 2023-12-31
    DATE_ADD('2015-01-01', INTERVAL FLOOR(RAND(6000+n) * 3287) DAY) AS hire_date
FROM seq;