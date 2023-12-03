package tw.waterballsa.gaas.citadels.spring.util;

import java.util.Arrays;
import java.util.Random;

public class shuffle {
    static void shuffle(int array[], int a) {
        Random rd = new Random();

        for (int i = a - 1; i > 0; i--) {
            int j = rd.nextInt(i+1);

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] ar = {1,2,3,4,5};

        int b = ar.length;
        shuffle(ar, b);
    }
}

