package tw.waterballsa.gaas.citadels.spring.util;

import java.util.Random;

public class CitadelsUtils {
    static void shuffle(Object[] List, int cards) {
        Random rd = new Random();

        for (int i = cards - 1; i > 0; i--) {
            int j = rd.nextInt(i+1);

            Object temp = List[i];
            List[i] = List[j];
            List[j] = temp;
        }
    }
}

