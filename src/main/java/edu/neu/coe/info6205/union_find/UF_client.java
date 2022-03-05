package edu.neu.coe.info6205.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class UF_client {
    public static void main(String args[]) throws IOException {
        int n=0;
        while(true) {
            int trials = 250;
            System.out.print("Enter The value of \"n\" to calculate random pairs or -1 to exit:  ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(reader.readLine());
            if (n == -1) {
                System.exit(1);
            }
            int m=0;
            for (int i = 0; i < trials; i++) {
                m += count(n);
            }
            int avg_m = m/trials;
            System.out.println(avg_m + " Random Pairs (m) were generated for the \"n\" value: " + n);
        }
    }

    private static int count(int n) {
        int randomPairs=0;

        UF_HWQUPC uf_hwqupc = new UF_HWQUPC(n,true);
        Random rand = new Random();

        while(uf_hwqupc.components() > 1) {
            int i = rand.nextInt(n);
            int j = rand.nextInt(n);
            if(uf_hwqupc.find(i)!= uf_hwqupc.find(j)) {
                uf_hwqupc.union(i, j);
            }
            randomPairs++;
        }
        return randomPairs;
    }
}
