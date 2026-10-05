package io.netty.util;

public interface Timeout {
   boolean isCancelled();

   boolean isExpired();

   boolean cancel();

   TimerTask task();

   Timer timer();
}
