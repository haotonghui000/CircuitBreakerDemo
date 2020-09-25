package com.company.myStatus;

/**
 * @author HTH
 * @description: TODO
 * @date 2020/9/2517:02
 */
public class CircuitBreakerRunner {



    public static void run(CircuitBreaker circuitBreaker, Test test ,Integer runTimes) {
        for (int i = 0; i < runTimes; i++) {
            try {
                test.randomMethod();
                circuitBreaker.addStatus(true);
            } catch (Exception e) {
                synchronized (circuitBreaker) {
                    System.out.println(e.getMessage());
                    circuitBreaker.addStatus(false);
                }
            }
        }

    }

}
