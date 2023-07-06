package org.trueQRNGwindows64;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    public static final int INT_AMOUNT = 10;
    private static final String CONFIG_FILE_PATH = "config.properties";
    public static final String CONNECTION_FAILED = "Connection failed!";
    public static final String OPERATION_SUCCESSFUL = "Operation successful.";
    public static final String DISCONNECTED_FROM_THE_SERVICE = "Disconnected from the service.";
    private static final String SORRY_UNABLE_TO_FIND = "Sorry, unable to find ";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    public static final String EMPTYSTRING = "";
    public static final String FAILED_TO_GET_INTEGER_ARRAY = "Failed to get integer array!";
    public static final String RECEIVED = "Received ";
    public static final String INTEGERS_FROM_THE_QRNG = " integers from the QRNG:\n";

    public static void main(String[] args) {
        Properties prop = new Properties();
        String username = EMPTYSTRING, password = EMPTYSTRING;

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

        QRNGConnectionManager qrngConnectionManager = new QRNGConnectionManager(username, password);
        if (qrngConnectionManager.connect()) {
            QRNGNumberFetcher qrngNumberFetcher = new QRNGNumberFetcher();
            qrngNumberFetcher.getAndPrintIntegerArray();
            qrngConnectionManager.disconnect();
        }
    }
}
