# Week 16: Project Presentation & Walkthrough Guidelines

This week focuses on demonstrating your semester-long learning through project presentations, code reviews, and walkthroughs.

## Course Presentation Guidelines

When presenting your course project, ensure you address the following key architectural layers of your application:

1. **System Design & Layout**: Explain the component hierarchy in Swing (e.g. `JTabbedPane`, `JTable`, input forms, layouts).
2. **Database Persistence**: Describe your table schemas (e.g. `courses`, `enrollments`) and explain why `PreparedStatements` were utilized (e.g., preventing SQL Injection, performance optimization).
3. **Multithreading Implementation**: Explain how the daemon clock thread works in the background and how it communicates with the GUI thread using `SwingUtilities.invokeLater`.
4. **File I/O**: Showcase your report generation feature which exports database records to text files on disk.
5. **Robust Error Handling**: Show how custom exception blocks and dialog warnings prevent the application from crashing.

---

## Peer Review Checklist

Use the checklist below during project reviews:

- [ ] Does the application compile without errors?
- [ ] Is there proper input validation (e.g., checking for negative credits, empty names, or database constraint violations)?
- [ ] Are SQL queries secure against injections (using parametrized inputs)?
- [ ] Is multi-user or multithreading coordinate handled thread-safely?
- [ ] Is the code clean, containing explanatory comments where needed?

---

## Capstone Project Demo Instructions

The capstone application `CourseManagementSystem.java` is located in this directory. 

### How to Compile and Run:
Make sure your terminal has the SQLite JDBC driver jar inside your ClassPath.
```bash
# Compile
javac -cp ".;../lib/*" DBConnection.java CourseManagementSystem.java

# Run
java -cp ".;../lib/*" CourseManagementSystem
```
*Tip: Once the app starts, add some courses, navigate to enrollments, register students, and use the Menu option **File -> Export Reports...** to check report outputs.*
