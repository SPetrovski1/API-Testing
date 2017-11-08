package fruz.udemy.model;

import java.util.Random;


import static org.apache.commons.lang3.RandomStringUtils.random;

/**
 * Created by dvelinovska on 9/29/2016.
 */
public  class RandomDataGenerator {

    public static Integer getRandomNumber(int low, int high) {
        Random r = new Random();
        Integer result = r.nextInt(high - low) + low;
        return result;
    }

    public static String randomAlphanumeric(int count) {
        return random(count, true, true);
    }
}


