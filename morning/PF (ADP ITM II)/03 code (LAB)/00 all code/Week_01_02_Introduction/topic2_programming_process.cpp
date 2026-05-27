// Week 1-2: Introduction to Computers and Programming
// Topic 2: The Programming Process
// This program prints a step-by-step interactive explanation of how a C++ program is compiled and executed.

#include <iostream>

int main() {
    std::cout << "==================================================" << std::endl;
    std::cout << "          THE C++ PROGRAMMING PROCESS             " << std::endl;
    std::cout << "==================================================" << std::endl;

    std::cout << "Step 1: Writing Source Code" << std::endl;
    std::cout << "   - Written by the programmer (e.g., this .cpp file)." << std::endl;
    std::cout << "   - Uses high-level human-readable syntax." << std::endl << std::endl;

    std::cout << "Step 2: Preprocessing" << std::endl;
    std::cout << "   - Handles lines starting with '#' (like #include <iostream>)." << std::endl;
    std::cout << "   - Copies and pastes the included files into the source file." << std::endl << std::endl;

    std::cout << "Step 3: Compiling" << std::endl;
    std::cout << "   - The Compiler translates preprocessed code into Machine Language." << std::endl;
    std::cout << "   - Generates an Object file (.obj or .o file) containing binary instructions." << std::endl;
    std::cout << "   - Performs syntax checking (warns/errors on incorrect code)." << std::endl << std::endl;

    std::cout << "Step 4: Linking" << std::endl;
    std::cout << "   - The Linker combines your object file with precompiled library code." << std::endl;
    std::cout << "   - Resolves external references (e.g., where std::cout is defined)." << std::endl;
    std::cout << "   - Produces the final Executable file (.exe on Windows)." << std::endl << std::endl;

    std::cout << "Step 5: Execution" << std::endl;
    std::cout << "   - The Loader loads the executable into RAM, and the CPU executes it." << std::endl;
    std::cout << "==================================================" << std::endl;

    return 0;
}
