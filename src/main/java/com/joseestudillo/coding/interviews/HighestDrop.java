package com.joseestudillo.coding.interviews;

import static java.util.Objects.nonNull;

/**
 * Given a sequence of numbers, determine the highest drop in value from one maximun to one minimun.
 */
public class HighestDrop {

    /**
     * Finds the highest consecutive drop.
     * @param numbers
     * @return
     */
    public static double highestSingleDrop(double[] numbers) {
        double highest = 0;
        if (nonNull(numbers) && numbers.length > 0) {
            double cv = numbers[0];
            double start = 0;
            boolean dropping = false;
            for (int i = 0; i < numbers.length; i++) {
                double tv = numbers[i];
                if (tv >= cv) {
                    //growth
                    dropping = false;
                } else {
                    //drop
                    if (!dropping) {
                        start = cv;
                        dropping = true;
                    }
                    highest = Math.max(highest, start - tv);

                }
                cv = tv;
            }
        }
        return highest;
    }

    /**
     * Finds the highest global drop
     *
     * @param numbers
     * @return
     */
    public static double highestDrop(double[] numbers) {
        double highest = 0;
        if (nonNull(numbers) && numbers.length > 0) {
            double max = numbers[0];
            double cv = numbers[0];
            for (int i = 0; i < numbers.length; i++) {
                double tv = numbers[i];
                if (tv >= cv) {
                    //growth
                    max = Math.max(max, tv);
                } else {
                    //drop
                    highest = Math.max(highest, max - tv);

                }
                cv = tv;
            }
        }
        return highest;
    }

    public static void main(String[] args) {
        double[] serie0 = new double[]{0, 1, 2, 1, 0, -1, 0, 1, 1};
        double[] serie1 = new double[]{0, -1, -2, -1, 0, 1, 2, 3, 4};
        double[] serie3 = new double[]{0, -1, -2, 0, 1, 4,1,2,-1,0,-3,-2,-5,-4,-7,-6};

        System.out.println(highestSingleDrop(serie0));
        System.out.println(highestSingleDrop(serie1));
        System.out.println(highestSingleDrop(serie3));

        System.out.println(highestDrop(serie0));
        System.out.println(highestDrop(serie1));
        System.out.println(highestDrop(serie3));
    }


}
