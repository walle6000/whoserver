package io.swagger.common;

public interface WorkerThread<T> {

    Runnable getWorkerProcessor();

}
