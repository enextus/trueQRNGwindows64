package org.trueQRNGwindows64;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    private static final String CONFIG_FILE_PATH = "config.properties";
    private static final String CONNECTION_FAILED = "Connection failed!";
    private static final String OPERATION_SUCCESSFUL = "Operation successful.";
    private static final String DISCONNECTED_FROM_THE_SERVICE = "Disconnected from the service.";
    private static final String SORRY_UNABLE_TO_FIND = "Sorry, unable to find ";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    public static final int INT_AMOUNT = 7;

    public interface QuantumRandomNumberGenerator extends Library {
        QuantumRandomNumberGenerator INSTANCE = Native.load("lib/libQRNG.dll", QuantumRandomNumberGenerator.class);

        int qrng_connect(String username, String password);

        void qrng_disconnect();

        int qrng_connect_SSL(String username, String password);

        int qrng_get_byte_array(byte[] byte_array, int byte_array_size, IntByReference actual_bytes_rcvd);

        int qrng_get_double_array(double[] double_array, int double_array_size, IntByReference actual_doubles_rcvd);

        int qrng_get_int_array(int[] int_array, int int_array_size, IntByReference actual_ints_rcvd);

        int qrng_generate_password(String tobeused_password_chars, String generated_password, int password_length);

        int qrng_connect_and_get_byte_array(String username, String password, byte[] byte_array, int byte_array_size, IntByReference actual_bytes_rcvd);

        int qrng_connect_and_get_double_array(String username, String password, double[] double_array, int double_array_size, IntByReference actual_doubles_rcvd);

        int qrng_connect_and_get_int_array(String username, String password, int[] int_array, int int_array_size, IntByReference actual_ints_rcvd);

    }

    public static void main(String[] args) {

        QuantumRandomNumberGenerator lib = QuantumRandomNumberGenerator.INSTANCE;

        Properties prop = new Properties();
        String username = "";
        String password = "";

        try (InputStream input = App.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)) {

            if (input == null) {
                System.out.println(SORRY_UNABLE_TO_FIND + CONFIG_FILE_PATH);
                System.exit(-1);
            }

            prop.load(input);
            username = prop.getProperty(USERNAME);
            password = prop.getProperty(PASSWORD);

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        if (checkResult(lib.qrng_connect(username, password))) {

            // get some QRNG-integers
            getAndPrintIntegerArray(lib);

            // disconnect
            lib.qrng_disconnect();
            System.out.println("\n" + DISCONNECTED_FROM_THE_SERVICE);
        }
    }

    private static void getAndPrintIntegerArray(QuantumRandomNumberGenerator lib) {
        // Create an array to hold the integers returned by the QRNG
        int[] intArray = new int[INT_AMOUNT];  // Change the size of this array based on your needs

        // Create an IntByReference instance to hold the actual number of integers received
        IntByReference actualIntsReceived = new IntByReference();

        // Call the qrng_get_int_array method
        int getArrayResult = lib.qrng_get_int_array(intArray, intArray.length, actualIntsReceived);

        if (getArrayResult != 0) {
            // Failed to get integer array, handle this case
            System.out.println("Failed to get integer array!");
        } else {
            // Successfully got the integer array, print it
            System.out.println("Received " + actualIntsReceived.getValue() + " integers from the QRNG:\n");
            for (int i = 0; i < actualIntsReceived.getValue(); i++) {
                System.out.println(intArray[i]);
            }
        }
    }

    static boolean checkResult(int result) {
        if (result != 0) {
            System.out.println(App.CONNECTION_FAILED);
            return false;
        }
        System.out.println(OPERATION_SUCCESSFUL);
        return true;
    }

}
