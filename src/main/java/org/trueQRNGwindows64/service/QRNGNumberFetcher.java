package org.trueQRNGwindows64.service;

import com.sun.jna.ptr.IntByReference;
import org.trueQRNGwindows64.provider.QuantumRandomNumberGenerator;
import org.trueQRNGwindows64.service.FrequencyTest;
import org.trueQRNGwindows64.service.KolmogorovSmirnov;

public class QRNGNumberFetcher {
    public static final int INT_AMOUNT = 100;
    public static final String FAILED_TO_GET_INTEGER_ARRAY = "Failed to get integer array!";
    public static final String RECEIVED = "Received ";
    public static final String INTEGERS_FROM_THE_QRNG = " integers from the QRNG:\n";
    private static final QuantumRandomNumberGenerator lib = QuantumRandomNumberGenerator.INSTANCE;

    public static void getAndPrintIntegerArray() {
        // Create an array to hold the integers returned by the QRNG
        int[] intArray = new int[INT_AMOUNT];  // Change the size of this array based on your needs

        // Create an IntByReference instance to hold the actual number of integers received
        IntByReference actualIntsReceived = new IntByReference();

        // Call the qrng_get_int_array method
        int getArrayResult = lib.qrng_get_int_array(intArray, intArray.length, actualIntsReceived);

        if (getArrayResult != 0) {
            // Failed to get integer array, handle this case
            System.out.println(FAILED_TO_GET_INTEGER_ARRAY);
        } else {
            FrequencyTest test = new FrequencyTest();
            // Successfully got the integer array, print it
            System.out.println(RECEIVED + actualIntsReceived.getValue() + INTEGERS_FROM_THE_QRNG);
            for (int i = 0; i < actualIntsReceived.getValue(); i++) {
                System.out.println(i + 1 + ". " + intArray[i]);
            }

            double pValue = test.frequencyTest(intArray);
            System.out.println("p-value: " + pValue);

            // Conduct Kolmogorov-Smirnov test on the received integers
            double significanceLevel = 0.01;  // Adjust this based on your needs
            boolean testResult = KolmogorovSmirnov.test(intArray, significanceLevel);

            if (testResult)
                System.out.println("Kolmogorov-Smirnov test passed, the integers appear to be uniformly distributed.");
            else
                System.out.println("Kolmogorov-Smirnov test failed, the integers do not appear to be uniformly distributed.");
        }
    }

}
