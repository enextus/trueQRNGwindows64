package org.trueQRNGwindows64;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

import static org.trueQRNGwindows64.QRNGConnectionManager.LIB_LIB_QRNG_DLL_PATH;

public interface QuantumRandomNumberGenerator extends Library {
    QuantumRandomNumberGenerator INSTANCE = Native.load(LIB_LIB_QRNG_DLL_PATH, QuantumRandomNumberGenerator.class);

    int qrng_connect(String username, String password);

    void qrng_disconnect();

    int qrng_get_int_array(int[] int_array, int int_array_size, IntByReference actual_ints_rcvd);

    int qrng_connect_SSL(String username, String password);

    int qrng_get_byte_array(byte[] byte_array, int byte_array_size, IntByReference actual_bytes_rcvd);

    int qrng_get_double_array(double[] double_array, int double_array_size, IntByReference actual_doubles_rcvd);

    int qrng_generate_password(String tobedeleted_password_chars, String generated_password, int password_length);

    int qrng_connect_and_get_byte_array(String username, String password, byte[] byte_array, int byte_array_size, IntByReference actual_bytes_rcvd);

    int qrng_connect_and_get_double_array(String username, String password, double[] double_array, int double_array_size, IntByReference actual_doubles_rcvd);

    int qrng_connect_and_get_int_array(String username, String password, int[] int_array, int int_array_size, IntByReference actual_ints_rcvd);
}
