package com.cheatbreaker.client.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;

public class ClientDiagnosticReportTest extends TestCase {
   public void testSlowReportDoesNotBlockSubmissionOrQueueRepeatedEnter() throws Exception {
      CountDownLatch started = new CountDownLatch(1);
      CountDownLatch release = new CountDownLatch(1);
      CountDownLatch finished = new CountDownLatch(1);
      Thread[] worker = new Thread[1];
      ExecutorService caller = Executors.newSingleThreadExecutor();
      try {
         Future<Boolean> submitted = caller.submit(() -> ClientDiagnosticReport.submitInBackground(() -> {
            worker[0] = Thread.currentThread();
            started.countDown();
            try {
               release.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException error) {
               Thread.currentThread().interrupt();
            } finally {
               finished.countDown();
            }
         }));
         assertTrue(submitted.get(500, TimeUnit.MILLISECONDS));
         assertTrue(started.await(1, TimeUnit.SECONDS));
         assertTrue(worker[0].isDaemon());
         assertEquals("Bug Report Submitter", worker[0].getName());
         assertFalse(ClientDiagnosticReport.submitInBackground(() -> fail("Duplicate submission ran")));
         release.countDown();
         assertTrue(finished.await(1, TimeUnit.SECONDS));
         worker[0].join(1000);
         CountDownLatch next = new CountDownLatch(1);
         assertTrue(ClientDiagnosticReport.submitInBackground(next::countDown));
         assertTrue(next.await(1, TimeUnit.SECONDS));
      } finally {
         release.countDown();
         caller.shutdownNow();
      }
   }
}
