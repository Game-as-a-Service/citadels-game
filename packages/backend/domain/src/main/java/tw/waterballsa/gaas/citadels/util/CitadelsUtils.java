package tw.waterballsa.gaas.citadels.util;

import java.util.List;
import java.util.Random;

public class CitadelsUtils {

    public static <T> void shuffle(List<T> cards) {
        Random rd = new Random();

        for (int i = cards.size() - 1; i > 0; i--) {
            int j = rd.nextInt(i + 1);

            T temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }
}

