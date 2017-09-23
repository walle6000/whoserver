package io.swagger.service.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.common.WorkerThread;

/**
 *
 * @param <T>
 */
public abstract class NConsumerBatchService<T> {
	private static final int DEFAULT_BATCH_SIZE = 1000;
	private Logger logger = LoggerFactory.getLogger(NConsumerBatchService.class);
	private BlockingQueue<T> queue = null;
	private ExecutorService workerExecutor;

	public void init() {
		queue = new LinkedBlockingQueue<T>();
		ExecutorService mainExecutor = Executors.newSingleThreadExecutor();
		workerExecutor = Executors.newFixedThreadPool(getWorkerThreadCount());
		mainExecutor.execute(new Runnable() {
			private List<T> tList = new ArrayList<T>();

			public void run() {
				try {
					while (true) {
						T t = queue.poll();
						dataHandle(tList,t);
						tList.add(queue.take());
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					Thread.currentThread().interrupt();
				}
			}
		});
	}

	private void dataHandle(List<T> dataList, T t) {
		if (t == null) {
			lauchWorkerThread(dataList);
		} else {
			dataList.add(t);
		}
		if (dataList.size() >= getBatchSize() || queue.isEmpty()) {
			lauchWorkerThread(dataList);
		}
	}

	private void lauchWorkerThread(List<T> dataList) {
		if (dataList.isEmpty()) {
			return;
		}
		List<T> tOutList = new ArrayList<T>();
		for (T t : dataList) {
			if (t == null) {
				if (logger.isWarnEnabled()) {
					logger.warn("item is null in T list");
				}
				continue;
			}
			tOutList.add(t);
		}
		WorkerThread<T> workerThread = getHandler(tOutList);
		workerExecutor.execute(workerThread.getWorkerProcessor());
		dataList.clear();
	}

	protected boolean preAddToBasketList(T t) {
		return true;
	}

	/**
	 * 发起的最大工作线程数，应小于数据库缓冲池的最大连接数,缺省值为处理器数+2.
	 * @return
	 */
	protected int getWorkerThreadCount() {
		return Runtime.getRuntime().availableProcessors() + 2;
	}

	/**
	 * 处理器类
	 * @param ids
	 * @return
	 */
	protected abstract WorkerThread<T> getHandler(List<T> tList);

	/**
	 * 每次批量处理的数据量,缺省值为1000
	 * @return
	 */
	private int batchSize = DEFAULT_BATCH_SIZE;

	protected int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	/**
	 * 
	 * @param id
	 */
	protected void addToQueue(T obj) {
		try {
			if (preAddToBasketList(obj)) {
				this.queue.put(obj);
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public boolean isQueueEmpty() {
		return this.queue.isEmpty();
	}

}
