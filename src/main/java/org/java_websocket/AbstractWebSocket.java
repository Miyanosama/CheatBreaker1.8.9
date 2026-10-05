package org.java_websocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.java_websocket.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWebSocket extends WebSocketAdapter {
   public Object syncConnectionLost;
   public boolean websocketRunning;
   public ScheduledExecutorService connectionLostCheckerService;
   public ScheduledFuture connectionLostCheckerFuture;
   public Logger log = LoggerFactory.getLogger(AbstractWebSocket.class);
   public boolean recoveredField994;
   public long connectionLostTimeout = TimeUnit.SECONDS.toNanos(60L);
   public boolean recoveredField995;

   public void setReuseAddr(boolean var1) {
      this.recoveredField995 = var1;
   }

   public void c_() {
      synchronized (this.syncConnectionLost) {
         if (this.connectionLostCheckerService != null || this.connectionLostCheckerFuture != null) {
            this.websocketRunning = false;
            this.log.trace("Connection lost timer stopped");
            this.e_();
         }
      }
   }

   public boolean d_() {
      return this.recoveredField995;
   }

   public void setTcpNoDelay(boolean var1) {
      this.recoveredField994 = var1;
   }

   public AbstractWebSocket() {
      this.websocketRunning = false;
      this.syncConnectionLost = new Object();
   }

   public void executeConnectionLostDetection(WebSocket var1, long var2) {
      if (var1 instanceof WebSocketImpl) {
         WebSocketImpl var4 = (WebSocketImpl)var1;
         if (var4.getLastPong() < var2) {
            this.log.trace("Closing connection due to no pong received: {}", var4);
            var4.closeConnection(
               1006,
               "The connection was closed because the other endpoint did not respond with a pong in time. For more information check: https://github.com/TooTallNate/Java-WebSocket/wiki/Lost-connection-detection"
            );
         } else if (var4.isOpen()) {
            var4.sendPing();
         } else {
            this.log.trace("Trying to ping a non open connection: {}", var4);
         }
      }
   }

   public abstract Collection<WebSocket> getConnections();

   public void a_(int var1) {
      synchronized (this.syncConnectionLost) {
         this.connectionLostTimeout = TimeUnit.SECONDS.toNanos(var1);
         if (this.connectionLostTimeout <= 0L) {
            this.log.trace("Connection lost timer stopped");
            this.e_();
         } else {
            if (this.websocketRunning) {
               this.log.trace("Connection lost timer restarted");

               try {
                  for (WebSocket var6 : new ArrayList<>(this.getConnections())) {
                     if (var6 instanceof WebSocketImpl) {
                        WebSocketImpl var4 = (WebSocketImpl)var6;
                        var4.updateLastPong();
                     }
                  }
               } catch (Exception var8) {
                  this.log.error("Exception during connection lost restart", (Throwable)var8);
               }

               this.h_();
            }
         }
      }
   }

   public void e_() {
      if (this.connectionLostCheckerService != null) {
         this.connectionLostCheckerService.shutdownNow();
         this.connectionLostCheckerService = null;
      }

      if (this.connectionLostCheckerFuture != null) {
         this.connectionLostCheckerFuture.cancel(false);
         this.connectionLostCheckerFuture = null;
      }
   }

   public int f_() {
      synchronized (this.syncConnectionLost) {
         return (int)TimeUnit.NANOSECONDS.toSeconds(this.connectionLostTimeout);
      }
   }

   public boolean g_() {
      return this.recoveredField994;
   }

   public void h_() {
      this.e_();
      this.connectionLostCheckerService = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("connectionLostChecker"));
      Runnable var1 = new Runnable() {
         public ArrayList<WebSocket> connections = new ArrayList<>();

         @Override
         public void run() {
            this.connections.clear();

            try {
               this.connections.addAll(AbstractWebSocket.this.getConnections());
               long var1x = (long)(System.nanoTime() - AbstractWebSocket.this.connectionLostTimeout * 1.5);

               for (WebSocket var4 : this.connections) {
                  AbstractWebSocket.this.executeConnectionLostDetection(var4, var1x);
               }
            } catch (Exception var5) {
            }

            this.connections.clear();
         }
      };
      this.connectionLostCheckerFuture = this.connectionLostCheckerService
         .scheduleAtFixedRate(var1, this.connectionLostTimeout, this.connectionLostTimeout, TimeUnit.NANOSECONDS);
   }

   public void i_() {
      synchronized (this.syncConnectionLost) {
         if (this.connectionLostTimeout <= 0L) {
            this.log.trace("Connection lost timer deactivated");
         } else {
            this.log.trace("Connection lost timer started");
            this.websocketRunning = true;
            this.h_();
         }
      }
   }
}
