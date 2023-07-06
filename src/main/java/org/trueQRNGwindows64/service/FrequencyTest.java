package org.trueQRNGwindows64.service;

import org.apache.commons.math3.special.Erf;

public class FrequencyTest {
    public double frequencyTest(int[] sequence) {
        int sum = 0;
        int n = sequence.length;

        for (int i = 0; i < n; i++) {
            sum += sequence[i] < 0 ? -1 : 1;
        }

        double sobs = Math.abs(sum) / Math.sqrt(n);
        double pValue = Erf.erfc(sobs / Math.sqrt(2));

        return pValue;
    }

}
