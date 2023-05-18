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

        //        QRNG INSTANCE = Native.load("C:\\Projects\\QRNG_\\lib\\libQRNG.dll", QRNG.class);

        /*        String path = Paths.get("").toAbsolutePath().toString();
                QRNG INSTANCE = Native.load(path + "/lib/libQRNG.dll", QRNG.class);*/
        QRNG INSTANCE = Native.load("lib/libQRNG.dll", QRNG.class);

        int qrng_connect(String username, String password);

        int qrng_connect_SSL(String username, String password);

        int qrng_get_byte_array(byte[] byte_array, int byte_array_size, IntByReference actual_bytes_rcvd);

        int qrng_get_double_array(double[] double_array, int double_array_size, IntByReference actual_doubles_rcvd);

        int qrng_get_int_array(int[] int_array, int int_array_size, IntByReference actual_ints_rcvd);

        int qrng_generate_password(StringBuilder tobeused_password_chars, StringBuilder generated_password, int password_length);

        int qrng_connect_and_get_byte_array(StringBuilder username, StringBuilder password, byte[] byte_array, int byte_array_size, IntByReference actual_bytes_rcvd);

        int qrng_connect_and_get_double_array(StringBuilder username, StringBuilder password, double[] double_array, int double_array_size, IntByReference actual_doubles_rcvd);

        int qrng_connect_and_get_int_array(StringBuilder username, StringBuilder password, int[] int_array, int int_array_size, IntByReference actual_ints_rcvd);

        int qrng_disconnect();
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

            // load a properties file
            prop.load(input);

            // get the property value and print it out
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
        }
    }

}
