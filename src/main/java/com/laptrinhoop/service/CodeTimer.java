package com.laptrinhoop.service;

public class CodeTimer {
    private String name;
    private long startTime, elapsedTime = 0,allTime=0;
    private String log;
    public CodeTimer(String name) {
        this.name = name;
        log=log+"start:"+name+"\r\n";
        startTime = System.currentTimeMillis();
    }

    public void reset() {
        startTime = System.currentTimeMillis();
        elapsedTime = 0;
    }
    public void reset(String name) {
        log=log+  name+":";
        startTime = System.currentTimeMillis();
        elapsedTime = 0;
    }
    public void end() {

        elapsedTime = System.currentTimeMillis() - startTime;
        allTime=allTime+elapsedTime;
        log=log+ elapsedTime+",";
    }

    public void pause() {
        elapsedTime += System.currentTimeMillis() - startTime;
    }

    public void resume() {
        startTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {

        return elapsedTime;
    }
    public long getTotalElapsedTime() {
        return allTime;
    }
    public String getLogs()  {
        return log+",total:"+allTime;
    }

    public String getName() {
        return name;
    }

}
