/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        // FIXME do move by replacing the following code
         //throw new RuntimeException("Not implemented");
        // below is the fixed code, x and y changes depending upon the values of dx and dy respectively
        x += dx;
        y += dy;
        // END 
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // FIXME
        // below we make a random move for m number of steps through iteration
        for(int i= 0; i<m; i++)
        {
            randomMove();
        }
        //END
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        /*interchanged the below parameters considering the variable ns as north-south, so when ns
         is true take a step in y and if ns is false take a step in x */
        move(ns ? 0 : step,ns ? step : 0);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        // FIXME
        // the formula is based on the distance of a point(x,y) from origin
        float dist= (float) Math.sqrt((x*x) + (y*y));
        return dist;
        //END
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
        /*
            changed the main program such that RandomWalk.java runs as a standalone program by running the
            program for random values of steps and getting the mean distance over 50 experiment
            and also can be run by the RandomWalkTest.java for the unit tests.
         * */
        Random rand = new Random();
        int m = 0;
        int n = 0;
        if (args.length == 0){
            //throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
            for(int i = 0; i < 10; i++){
                m =  rand.nextInt(10000)+1;
                n = 50;
                double meanDistance = randomWalkMulti(m, n);
                System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
            }
        }
        else{
            m = Integer.parseInt(args[0]);
            n = 30;
            if (args.length > 1) n = Integer.parseInt(args[1]);
            double meanDistance = randomWalkMulti(m, n);
            System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
        }



    }

}
