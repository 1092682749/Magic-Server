package com.example.demo.configration.diskIO;

import java.util.concurrent.*;

// 该类用于执行磁盘IO任务，所有的磁盘文件读写都需要通过该类完成
public class TaskExecutor<T> {
    public BlockingDeque<Runnable> taskQueue;
    public ThreadPoolExecutor executor ;
    public TaskExecutor() {
        taskQueue = new LinkedBlockingDeque<Runnable>();
        executor = new ThreadPoolExecutor(1, 2, 300, TimeUnit.SECONDS, taskQueue);
    }
    public Future<T> add(Runnable runnable, T path) {
        Future<T> future = executor.submit(runnable, path);
        return future;
    }

}
