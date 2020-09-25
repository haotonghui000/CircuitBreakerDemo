package com.company.myStatus;

/**
 * @author HTH
 * @description: TODO
 * @date 2020/9/2515:38
 */
public class CircuitBreaker {

    //判断方法调用失败次数的队列
    private StatusQueue statusQueue;

    //失败次数
    private final Integer FAILTIMES;
    //断线停止时间
    private final Integer STOPTIME;

    //false为断线   true为正常
    private boolean circuitStatus = true;

    /**
     * 初始化 CircuitBreaker
     * @param failTimes 在queueSize 次数下 失败多少次会触发断线
     * @param queueSize 设置运行方法次数
     * @param stopTime  设置断线后多长时间会重新连接
     */
    public CircuitBreaker(Integer failTimes, Integer queueSize, Integer stopTime) {
        if (failTimes > queueSize) {
            throw new RuntimeException("失败次数不能大于队列总长度");
        }
        if (failTimes < 1 || queueSize < 1) {
            throw new RuntimeException("失败次数或者执行次数必须大于1");
        }
        if (stopTime < 1) {
            throw new RuntimeException("停止时间必须大于0");
        }
        statusQueue = new StatusQueue(queueSize);
        this.FAILTIMES = failTimes;
        this.STOPTIME = stopTime*1000;
    }

    /**
     * 将运行成功或失败加入队列中判定 是否满足断线次数
     * @param status   true方法运行成功  false 方法运行失败
     */
    public void  addStatus(Boolean status) {
        //如果失败次数满足断线次数
        statusQueue.addStatus(status);

        if(statusQueue.getFailCount().equals(FAILTIMES)){
            clear();
            try {
                fallback();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 断线用于修复的代码
     * @throws InterruptedException
     */
    public void fallback() throws InterruptedException {
        System.out.println("进入断线，开始断线修复逻辑代码");
        Thread.sleep(STOPTIME);
        System.out.println("修复完成，重新连线");
    }

    public void clear(){
        statusQueue.clear();
    }
}
