package com.example.demo;

import com.example.demo.configration.diskIO.TaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class POOL {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TaskExecutor taskExecutor = new TaskExecutor();
        IOTask ioTask = new IOTask();
        Future<String> future = taskExecutor.add(ioTask, ioTask.path);
        System.out.println(future.get());
    }
}
class IOTask implements Runnable {
    String path = "q";
    @Override
    public void run() {
        System.out.println("e");
        path = "ppppppppppppppppppp";
    }
}
