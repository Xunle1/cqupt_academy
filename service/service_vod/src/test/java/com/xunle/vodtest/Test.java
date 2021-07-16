package com.xunle.vodtest;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author xunle
 */
public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.useDelimiter("[^1234567890.]");
        double total = 0;
        while (in.hasNext()){
            try {
                double score = in.nextDouble();
                total += score;
                System.out.println(score);
                System.out.println(total);
            } catch (InputMismatchException e) {
                System.out.println(e.toString());
            }
        }
    }
}
