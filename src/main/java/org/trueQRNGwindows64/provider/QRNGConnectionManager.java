package org.trueQRNGwindows64.provider;

public class QRNGConnectionManager {
    private static final String CONNECTION_FAILED = "Connection failed!";
    private static final String OPERATION_SUCCESSFUL = "Operation successful.";
    private static final String DISCONNECTED_FROM_THE_SERVICE = "Disconnected from the service.";
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
