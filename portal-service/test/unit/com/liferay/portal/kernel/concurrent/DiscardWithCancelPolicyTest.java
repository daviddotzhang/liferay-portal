/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.concurrent;

import com.liferay.portal.kernel.concurrent.test.MarkerBlockingJob;
import com.liferay.portal.kernel.concurrent.test.TestUtil;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class DiscardWithCancelPolicyTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testDiscardWithCancelPolicy1() {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			1, 1, TestUtil.KEEPALIVE_TIME, TimeUnit.MILLISECONDS, true, 1,
			new DiscardWithCancelPolicy(), Executors.defaultThreadFactory(),
			new ThreadPoolHandlerAdapter());

		threadPoolExecutor.shutdown();

		MarkerBlockingJob markerBlockingJob = new MarkerBlockingJob();

		threadPoolExecutor.execute(markerBlockingJob);

		Assert.assertFalse(markerBlockingJob.isEnded());

		markerBlockingJob = new MarkerBlockingJob();

		Future<?> future = threadPoolExecutor.submit(markerBlockingJob);

		Assert.assertFalse(markerBlockingJob.isEnded());
		Assert.assertTrue(future.isCancelled());
	}

	@Test
	public void testDiscardWithCancelPolicy2() throws InterruptedException {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			1, 1, TestUtil.KEEPALIVE_TIME, TimeUnit.MILLISECONDS, true, 1,
			new DiscardWithCancelPolicy(), Executors.defaultThreadFactory(),
			new ThreadPoolHandlerAdapter());

		try {
			MarkerBlockingJob markerBlockingJob1 = new MarkerBlockingJob(true);
			MarkerBlockingJob markerBlockingJob2 = new MarkerBlockingJob(true);
			MarkerBlockingJob markerBlockingJob3 = new MarkerBlockingJob();

			Future<?> future1 = threadPoolExecutor.submit(markerBlockingJob1);

			markerBlockingJob1.waitUntilBlock();

			Future<?> future2 = threadPoolExecutor.submit(markerBlockingJob2);

			Assert.assertEquals(1, threadPoolExecutor.getActiveCount());
			Assert.assertEquals(1, threadPoolExecutor.getPendingTaskCount());

			Future<?> future3 = threadPoolExecutor.submit(markerBlockingJob3);

			Assert.assertFalse(markerBlockingJob3.isStarted());
			Assert.assertTrue(future3.isCancelled());
			Assert.assertEquals(1, threadPoolExecutor.getActiveCount());
			Assert.assertEquals(1, threadPoolExecutor.getPendingTaskCount());

			markerBlockingJob1.unBlock();
			markerBlockingJob2.waitUntilBlock();

			Assert.assertTrue(markerBlockingJob1.isEnded());
			Assert.assertTrue(future1.isDone());
			Assert.assertFalse(future1.isCancelled());
			Assert.assertEquals(1, threadPoolExecutor.getActiveCount());
			Assert.assertEquals(0, threadPoolExecutor.getPendingTaskCount());

			markerBlockingJob2.unBlock();

			TestUtil.waitUntilEnded(markerBlockingJob2);

			Assert.assertTrue(future2.isDone());
			Assert.assertFalse(future2.isCancelled());
			Assert.assertEquals(0, threadPoolExecutor.getActiveCount());
			Assert.assertEquals(0, threadPoolExecutor.getPendingTaskCount());
		}
		finally {
			TestUtil.closePool(threadPoolExecutor);
		}
	}

}