package com.company.myStatus;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author HTH
 * @description: TODO
 * @date 2020/9/2515:48
 */
public class StatusQueue {

    private Queue<Boolean> queue = new LinkedList<>();

    //在规定的调用次数内的失败次数
    private Integer failCount = 0;

    private final Integer queueSize;

    public StatusQueue(Integer queueSize){
        this.queueSize = queueSize;
    }


    public void addStatus(Boolean status){

        queue.offer(status);

        if(queue.size()<=queueSize){
            if(!status){
                failCount++;
            }
        }else{
            Boolean poll = queue.poll();
            if(!poll){
                failCount--;
            }
        }
    }

    public Integer getFailCount(){
        return failCount;
    }

    public void clear(){
        queue.clear();
        failCount = 0;
    }
}
