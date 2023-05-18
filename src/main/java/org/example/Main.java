package org.example;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.example.Main.QRNG.INSTANCE;

public class Main {
    public interface QRNG extends Library {

        /* QRNG INSTANCE = Native.load("C:\\Projects\\QRNG_\\lib\\libQRNG.dll", QRNG.class);*/

        /* String path = Paths.get("").toAbsolutePath().toString();
           QRNG INSTANCE = Native.load(path + "/lib/libQRNG.dll", QRNG.class);*/

        QRNG INSTANCE = Native.load("lib/libQRNG.dll", QRNG.class);

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
        QRNG lib = INSTANCE;

        Properties prop = new Properties();
        String username = "";
        String password = "";

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            prop.load(input);
            username = prop.getProperty("username");
            password = prop.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int connectionResult = lib.qrng_connect(username, password);

        if (connectionResult != 0) {
            // Connection failed, handle this case
            System.out.println("Connection failed!");
        } else {
            System.out.println("Connection established.");

            // Create an array to hold the integers returned by the QRNG
            int[] intArray = new int[10];  // Change the size of this array based on your needs

            // Create an IntByReference instance to hold the actual number of integers received
            IntByReference actualIntsReceived = new IntByReference();

            // Call the qrng_get_int_array method
            int getArrayResult = lib.qrng_get_int_array(intArray, intArray.length, actualIntsReceived);

            if (getArrayResult != 0) {
                // Failed to get integer array, handle this case
                System.out.println("Failed to get integer array!");
            } else {
                // Successfully got the integer array, print it
                System.out.println("Received " + actualIntsReceived.getValue() + " integers from the QRNG:");
                for (int i = 0; i < actualIntsReceived.getValue(); i++) {
                    System.out.println(intArray[i]);
                }
            }

            // disconnect.
            lib.qrng_disconnect();
            System.out.println("Disconnected from the service.");
        }
    }

}
