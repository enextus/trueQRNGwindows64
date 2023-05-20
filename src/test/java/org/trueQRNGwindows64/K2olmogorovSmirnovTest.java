package org.trueQRNGwindows64;

import java.util.Arrays;
import java.util.Random;

public class K2olmogorovSmirnovTest {

    public static boolean test(Random random, int sampleSize, double alpha) {
        // Генерируем выборку из случайных чисел
        double[] sample = new double[sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            sample[i] = random.nextDouble();
        }

        // Сортируем выборку по возрастанию
        Arrays.sort(sample);

        // Вычисляем эмпирическую функцию распределения
        double[] empiricalCDF = new double[sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            empiricalCDF[i] = (double) (i + 1) / sampleSize;
        }

        // Вычисляем теоретическую функцию распределения (равномерное распределение)
        double[] theoreticalCDF = new double[sampleSize];
        for (int i = 0; i < sampleSize; i++) {
            theoreticalCDF[i] = (double) (i + 1) / sampleSize;
        }

        // Вычисляем максимальное отклонение между эмпирической и теоретической функциями распределения
        double maxDeviation = 0.0;
        for (int i = 0; i < sampleSize; i++) {
            double deviation = Math.abs(empiricalCDF[i] - theoreticalCDF[i]);
            if (deviation > maxDeviation) {
                maxDeviation = deviation;
            }
        }

        // Вычисляем критическое значение
        double criticalValue = Math.sqrt(-0.5 * Math.log(alpha / 2) / sampleSize);

        // Проверяем условие
        return maxDeviation <= criticalValue;
    }
}
