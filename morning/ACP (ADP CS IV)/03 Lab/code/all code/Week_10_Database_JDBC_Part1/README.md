# Week 10: Database Connectivity (JDBC) - Part 1

This folder contains lab code demonstrating Java Database Connectivity (JDBC) basic connections, query executions, and CRUD (Create, Read, Update, Delete) operations.

## Pre-requisites & Setup

1. **Database Server**: Make sure you have a database server (like MySQL or MariaDB via XAMPP/WAMP or direct installation) running on `localhost:3306`.
2. **Database Schema**: Create a database named `acp_lab_db` in your MySQL database instance:
   ```sql
   CREATE DATABASE acp_lab_db;
   ```
3. **JDBC Driver**: Download the MySQL JDBC Driver (`mysql-connector-j-x.x.x.jar`) or locate it in your environment.
   - Place the JAR file inside this directory (e.g., name it `mysql-connector-j.jar`).

## File Information

* **`StudentCRUDDemo.java`**: Implements standard CRUD operations on the `students` table using basic statements.
* **`DatabaseMetadataDemo.java`**: Demonstrates querying database and result set specifications (column names, types, driver attributes).

## How to Compile and Run (MySQL)

Ensure the MySQL driver is present in your classpath:

### Windows Command Prompt
```cmd
:: Compile the files
javac StudentCRUDDemo.java DatabaseMetadataDemo.java

:: Run StudentCRUDDemo
java -cp ".;mysql-connector-j.jar" StudentCRUDDemo

:: Run DatabaseMetadataDemo
java -cp ".;mysql-connector-j.jar" DatabaseMetadataDemo
```

### PowerShell
```powershell
:: Compile the files
javac StudentCRUDDemo.java DatabaseMetadataDemo.java

:: Run StudentCRUDDemo
java -cp ".;mysql-connector-j.jar" StudentCRUDDemo
```

---

## Alternative Setup (SQLite - Zero Configuration)

If you don't have a MySQL server running, you can switch to **SQLite**:
1. Download `sqlite-jdbc-x.x.x.jar`.
2. Open the `.java` files, uncomment the SQLite JDBC `DB_URL` line, and comment out the MySQL `DB_URL` line.
3. Run the programs using the sqlite jar:
   ```cmd
   java -cp ".;sqlite-jdbc.jar" StudentCRUDDemo
   ```
