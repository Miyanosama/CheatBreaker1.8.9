package org.java_websocket.client;

import java.io.IOException;
import java.nio.ByteBuffer;

public class WebSocketClient$WebsocketWriteThread implements Runnable {
   public WebSocketClient webSocketClient;

   public void closeSocket() {
      try {
         if (WebSocketClient.access$400(this.this$0) != null) {
            WebSocketClient.access$400(this.this$0).close();
         }
      } catch (IOException var2) {
         this.this$0.onWebsocketError(this.webSocketClient, var2);
      }
   }

   @Override
   public void run() {
      Thread.currentThread().setName("WebSocketWriteThread-" + Thread.currentThread().getId());

      try {
         this.runWriteData();
      } catch (IOException var5) {
         WebSocketClient.access$000(this.this$0, var5);
      } finally {
         this.closeSocket();
         WebSocketClient.access$102(this.this$0, null);
      }
   }

   public WebSocketClient$WebsocketWriteThread(WebSocketClient var1, WebSocketClient var2) {
      this.this$0 = var1;
      super();
      this.webSocketClient = var2;
   }

   public void runWriteData() {
      try {
         while (!Thread.interrupted()) {
            ByteBuffer var1 = WebSocketClient.access$200(this.this$0).outQueue.take();
            WebSocketClient.access$300(this.this$0).write(var1.array(), 0, var1.limit());
            WebSocketClient.access$300(this.this$0).flush();
         }
      } catch (InterruptedException var4) {
         for (ByteBuffer var3 : WebSocketClient.access$200(this.this$0).outQueue) {
            WebSocketClient.access$300(this.this$0).write(var3.array(), 0, var3.limit());
            WebSocketClient.access$300(this.this$0).flush();
         }

         Thread.currentThread().interrupt();
      }
   }
}
