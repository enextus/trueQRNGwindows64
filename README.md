# trueQRNGwindows64
QRNG Java Integration

This project provides an example of how to integrate the Quantum Random Number Generator (QRNG) library in a Java application using the Java Native Access (JNA) library. The QRNG library is used for generating truly random numbers.
Prerequisites

    Java Development Kit (JDK) 8 or later.
    Maven for managing project dependencies.
    QRNG library (libQRNG.dll) correctly placed in the project structure.
    An account to use the QRNG service.

Project Structure

    src/main/java/org/example/Main.java is the main Java file that demonstrates how to connect to the QRNG service and retrieve random integers.

Usage

    Clone the repository to your local machine.
    Open the project in your favorite IDE (IntelliJ IDEA, Eclipse, etc.).
    Place the libQRNG.dll file in the correct location in your project. (This location should be consistent with the path specified in the Native.load() method in your Java code.)
    Update the username and password variables in Main.java with your QRNG service credentials.
    Run the Main.java file. If successful, it will establish a connection with the QRNG service, fetch some random integers, and print them to the console.

Troubleshooting

    If you encounter java.lang.UnsatisfiedLinkError: Unable to load library, this means the libQRNG.dll file cannot be found or accessed. Make sure it is placed in the correct directory as specified in the Native.load() method.
    If you encounter java.lang.UnsatisfiedLinkError: %1 is not a valid Win32 application, this indicates that the .dll file you're trying to use is not compatible with your system's architecture (32 vs 64 bit). Make sure you're using the correct .dll file for your system.

Notes

    The example in Main.java shows how to fetch an array of random integers. You can similarly use other functions provided by the QRNG library as per your needs.