package com.company.myStatus;

import java.util.Random;

/**
 * @author HTH
 * @description: TODO
 * @date 2020/9/2517:53
 */
public class Test {


    public static void main(String[] args) throws NoSuchMethodException {
        CircuitBreaker circuitBreaker = new CircuitBreaker(5,50,5);
        Test test = new Test();


        new Thread(()->{
            CircuitBreakerRunner.run(circuitBreaker, test , 200);
        }).start();
        new Thread(()->{
            CircuitBreakerRunner.run(circuitBreaker, test , 200);
        }).start();
    }

    public void randomMethod(){
        Random random = new Random();
        boolean b = random.nextBoolean();
        if(!b){
            throw new RuntimeException("出现异常");
        }
    }
}
