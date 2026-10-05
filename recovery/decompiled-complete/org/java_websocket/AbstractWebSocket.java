package org.java_websocket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$20;
import org.java_websocket.util.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWebSocket extends WebSocketAdapter {
   public Object syncConnectionLost;
   public boolean websocketRunning;
   public ScheduledExecutorService connectionLostCheckerService;
   public TileEntityEnchantmentTableRenderer field_0010;
   public BlockGrass field_0007;
   public ScheduledFuture connectionLostCheckerFuture;
   public LogBrokerMonitor$20 field_0011;
   public Logger log = LoggerFactory.getLogger(AbstractWebSocket.class);
   public boolean field_0005;
   public BlockRedstoneTorch field_0006;
   public long connectionLostTimeout = TimeUnit.SECONDS.toNanos(1057340L & -4872712930404857731L);
   public boolean field_0008;

   public void setReuseAddr(boolean var1) {
      this.field_0008 = var1;
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
      return this.field_0008;
   }

   public void setTcpNoDelay(boolean var1) {
      this.field_0005 = var1;
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
         if (this.connectionLostTimeout <= (545424905L & 225513810L)) {
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
      return this.field_0005;
   }

   public void h_() {
      this.e_();
      this.connectionLostCheckerService = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("connectionLostChecker"));
      AbstractWebSocket$1 var1 = new AbstractWebSocket$1(this);
      this.connectionLostCheckerFuture = this.connectionLostCheckerService
         .scheduleAtFixedRate(var1, this.connectionLostTimeout, this.connectionLostTimeout, TimeUnit.NANOSECONDS);
   }

   public void i_() {
      synchronized (this.syncConnectionLost) {
         if (this.connectionLostTimeout <= (3161162620506245219L & -3161162622107561980L)) {
            this.log.trace("Connection lost timer deactivated");
         } else {
            this.log.trace("Connection lost timer started");
            this.websocketRunning = true;
            this.h_();
         }
      }
   }
}
