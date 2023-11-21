package com.ibm.fhir.threading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * The Class ThreadPool.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class ThreadPool {

	/** The queue. */
	BlockingQueue<Runnable> queue;

	/**
	 * Instantiates a new thread pool to manage ACD request tasks (the runnables) In
	 * truth the queue could handle any threads, but in our case the tasks will
	 * consist of ACD queries.
	 *
	 * @param queueSize   the queue size
	 * @param threadCount the thread count (number of concurrent threads)
	 */
	public ThreadPool(int queueSize, int threadCount) {
		queue = new ArrayBlockingQueue<Runnable>(queueSize);
		String threadName = null;
		TaskExecutor task = null;
		for (int count = 0; count < threadCount; count++) {
			threadName = "Thread-" + count;
			task = new TaskExecutor(queue);
			Thread thread = new Thread(task, threadName);
			thread.start();
		}
	}

	/**
	 * Submit task (i.e. the thread object that implements Runnable)
	 *
	 * @param task the task
	 * @throws InterruptedException the interrupted exception
	 */
	public void submitTask(Runnable task) throws InterruptedException {
		queue.add(task);
	}
}