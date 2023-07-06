package org.trueQRNGwindows64;

import org.trueQRNGwindows64.provider.QRNGConnectionManager;
import org.trueQRNGwindows64.service.QRNGNumberFetcher;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
    private static final String CONFIG_FILE_PATH = "config.properties";
    private static final String SORRY_UNABLE_TO_FIND = "Sorry, unable to find ";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMPTYSTRING = "";

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
            QRNGNumberFetcher.getAndPrintIntegerArray();
            qrngConnectionManager.disconnect();
        }
    }

}
