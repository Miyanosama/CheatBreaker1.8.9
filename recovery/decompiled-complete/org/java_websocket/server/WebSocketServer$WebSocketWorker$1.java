package org.java_websocket.server;

import java.lang.Thread.UncaughtExceptionHandler;
import org.apache.log4j.lf5.util.LogMonitorAdapter;

public class WebSocketServer$WebSocketWorker$1 implements UncaughtExceptionHandler {
   public LogMonitorAdapter field_0001;

   public WebSocketServer$WebSocketWorker$1(WebSocketServer$WebSocketWorker var1, WebSocketServer var2) {
      this.this$1 = var1;
      this.val$this$0 = var2;
      super();
   }

   @Override
   public void uncaughtException(Thread var1, Throwable var2) {
      WebSocketServer.access$000(this.this$1.this$0).error("Uncaught exception in thread {}: {}", var1.getName(), var2);
   }
}
