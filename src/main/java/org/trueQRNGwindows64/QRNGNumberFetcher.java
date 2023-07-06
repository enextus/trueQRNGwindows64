package org.trueQRNGwindows64;

import com.sun.jna.ptr.IntByReference;

public class QRNGNumberFetcher {

    private QuantumRandomNumberGenerator lib = QuantumRandomNumberGenerator.INSTANCE;

    public void getAndPrintIntegerArray() {
        int[] intArray = new int[App.INT_AMOUNT];
        IntByReference actualIntsReceived = new IntByReference();
        int getArrayResult = lib.qrng_get_int_array(intArray, intArray.length, actualIntsReceived);

        if (getArrayResult != 0) {
            System.out.println(App.FAILED_TO_GET_INTEGER_ARRAY);
        } else {
            System.out.println(App.RECEIVED + actualIntsReceived.getValue() + App.INTEGERS_FROM_THE_QRNG);
            for (int i = 0; i < actualIntsReceived.getValue(); i++) {
                System.out.println(intArray[i]);
            }
        }
    }
    
}
