package org.trueQRNGwindows64;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    private static final String CONFIG_FILE_PATH = "config.properties";
    private static final String CONNECTION_FAILED = "Connection failed!";
    private static final String OPERATION_SUCCESSFUL = "Operation successful.";
    private static final String DISCONNECTED_FROM_THE_SERVICE = "Disconnected from the service.";
    private static final String SORRY_UNABLE_TO_FIND = "Sorry, unable to find ";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public interface QuantumRandomNumberGenerator extends Library {
        QuantumRandomNumberGenerator INSTANCE = Native.load("lib/libQRNG.dll", QuantumRandomNumberGenerator.class);

        int qrng_connect(String username, String password);
        void qrng_disconnect();
        // other methods...
    }

    public static void main(String[] args) {
        QuantumRandomNumberGenerator lib = QuantumRandomNumberGenerator.INSTANCE;

        Properties prop = new Properties();
        String username = "";
        String password = "";

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)) {
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

        if (checkResult(lib.qrng_connect(username, password), CONNECTION_FAILED)) {
            // disconnect.
            lib.qrng_disconnect();
            System.out.println(DISCONNECTED_FROM_THE_SERVICE);
        }
    }

    private static boolean checkResult(int result, String errorMessage) {
        if (result != 0) {
            System.out.println(errorMessage);
            return false;
        }
        System.out.println(OPERATION_SUCCESSFUL);
        return true;
    }

}
