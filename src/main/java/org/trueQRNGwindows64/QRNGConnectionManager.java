package org.trueQRNGwindows64;

public class QRNGConnectionManager {

    public static final int INT_AMOUNT = 100;
    private static final String CONFIG_FILE_PATH = "config.properties";
    private static final String CONNECTION_FAILED = "Connection failed!";
    private static final String OPERATION_SUCCESSFUL = "Operation successful.";
    private static final String DISCONNECTED_FROM_THE_SERVICE = "Disconnected from the service.";
    private static final String SORRY_UNABLE_TO_FIND = "Sorry, unable to find ";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    public static final String EMPTYSTRING = "";
    public static final String FAILED_TO_GET_INTEGER_ARRAY = "Failed to get integer array!";
    public static final String RECEIVED = "Received ";
    public static final String INTEGERS_FROM_THE_QRNG = " integers from the QRNG:\n";
    public static final String LIB_QRNG_DLL_NAME = "libQRNG.dll";
    public static final String LIB_LIB_QRNG_DLL_PATH = "lib/" + LIB_QRNG_DLL_NAME;

    private QuantumRandomNumberGenerator lib = QuantumRandomNumberGenerator.INSTANCE;
    private String username;
    private String password;

    public QRNGConnectionManager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean connect() {
        return checkResult(lib.qrng_connect(username, password));
    }

    public void disconnect() {
        lib.qrng_disconnect();
        System.out.println("\n" + DISCONNECTED_FROM_THE_SERVICE);
    }

    private boolean checkResult(int result) {
        if (result != 0) {
            System.out.println(CONNECTION_FAILED);
            return false;
        }
        System.out.println(OPERATION_SUCCESSFUL);
        return true;
    }
}
