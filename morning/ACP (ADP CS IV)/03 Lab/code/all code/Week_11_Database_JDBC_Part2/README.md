# ACP Lab - Week 11: Database JDBC Part 2

## Topics
- PreparedStatement for secure/parameterized queries
- SQL Injection demonstration & prevention
- Database Transactions (commit/rollback)
- GUI front-end for database operations
- Login system with database authentication

## Lab Tasks

| Task | File | Description |
|------|------|-------------|
| 1 | `PreparedStatementDemo.java` | SQL injection demo + PreparedStatement protection |
| 2 | `DatabaseTransactionDemo.java` | Bank transfer simulation with commit/rollback |
| 3 | `StudentDatabaseGUI.java` | Full CRUD GUI using JTable + PreparedStatement |
| 4 | `LoginSystemDemo.java` | Login/Register with SHA-256 hashed passwords |

## Setup

1. Ensure MySQL is running on `localhost:3306`
2. Download `mysql-connector-j.jar` from [MySQL Downloads](https://dev.mysql.com/downloads/connector/j/)
3. Place the JAR in this folder

## Compile & Run

```bash
# Compile
javac -cp ".;mysql-connector-j.jar" PreparedStatementDemo.java
javac -cp ".;mysql-connector-j.jar" DatabaseTransactionDemo.java
javac -cp ".;mysql-connector-j.jar" StudentDatabaseGUI.java
javac -cp ".;mysql-connector-j.jar" LoginSystemDemo.java

# Run
java -cp ".;mysql-connector-j.jar" PreparedStatementDemo
java -cp ".;mysql-connector-j.jar" DatabaseTransactionDemo
java -cp ".;mysql-connector-j.jar" StudentDatabaseGUI
java -cp ".;mysql-connector-j.jar" LoginSystemDemo
```

> **Default login credentials for Task 4:**
> - Username: `admin`
> - Password: `admin123`

## Key Concepts

- `PreparedStatement` prevents SQL injection by parameterizing queries
- `conn.setAutoCommit(false)` enables manual transaction control
- `conn.commit()` saves the transaction; `conn.rollback()` undoes it
- SHA-256 hashing (via `MessageDigest`) protects stored passwords
