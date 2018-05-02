package com.example.countrycapital;

import java.util.Random;

/**
 * Created by andyk on 5/2/18.
 */

public class Test {

    public void random() {
        String s = "";
        String u = "";
        Random r = new Random();
        int bound = 3;
        for (int i = 0; i < 10; i++) {
            int n = r.nextInt(bound);
            s += n + ", ";
            double d = Math.random();
            u += d + ", ";
        }
        System.out.println("result: " + s);
        System.out.println("u: " + u);
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.random();
    }
}