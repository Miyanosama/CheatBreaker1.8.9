package org.apache.log4j.net;

import io.netty.handler.codec.http.multipart.MemoryFileUpload;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceMappingsToIntTask;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Vector;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.CyclicBuffer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class SocketHubAppender extends AppenderSkeleton {
   public int port = 4560;
   public boolean locationInfo;
   public ServerSocket serverSocket;
   public ZeroConfSupport zeroConf;
   public CyclicBuffer buffer;
   public MemoryFileUpload field_0010;
   public Vector oosList = new Vector();
   public ConcurrentHashMapV8$MapReduceMappingsToIntTask field_0002;
   public static String field_0005;
   public boolean advertiseViaMulticastDNS;
   public static int field_0011;
   public SocketHubAppender$ServerMonitor serverMonitor = null;
   public String application;
   public BehaviorDefaultDispenseItem field_0003;

   public void activateOptions() {
      if (this.advertiseViaMulticastDNS) {
         this.zeroConf = new ZeroConfSupport("_log4j_obj_tcpaccept_appender.local.", this.port, this.getName());
         this.zeroConf.advertise();
      }

      this.startServer();
   }

   public void setBufferSize(int var1) {
      this.buffer = new CyclicBuffer(var1);
   }

   public void setAdvertiseViaMulticastDNS(boolean var1) {
      this.advertiseViaMulticastDNS = var1;
   }

   public static CyclicBuffer access$100(SocketHubAppender var0) {
      return var0.buffer;
   }

   public void cleanUp() {
      LogLog.debug("stopping ServerSocket");
      this.serverMonitor.stopMonitor();
      this.serverMonitor = null;
      LogLog.debug("closing client connections");

      while (this.oosList.size() != 0) {
         ObjectOutputStream var1 = (ObjectOutputStream)this.oosList.elementAt(0);
         if (var1 != null) {
            try {
               var1.close();
            } catch (InterruptedIOException var3) {
               Thread.currentThread().interrupt();
               LogLog.error("could not close oos.", var3);
            } catch (IOException var4) {
               LogLog.error("could not close oos.", var4);
            }

            this.oosList.removeElementAt(0);
         }
      }
   }

   public synchronized void close() {
      if (!this.closed) {
         LogLog.debug("closing SocketHubAppender " + this.getName());
         this.closed = true;
         if (this.advertiseViaMulticastDNS) {
            this.zeroConf.unadvertise();
         }

         this.cleanUp();
         LogLog.debug("SocketHubAppender " + this.getName() + " closed");
      }
   }

   public boolean requiresLayout() {
      return false;
   }

   public static ServerSocket access$000(SocketHubAppender var0) {
      return var0.serverSocket;
   }

   public SocketHubAppender() {
      this.locationInfo = false;
      this.buffer = null;
   }

   public String getApplication() {
      return this.application;
   }

   public void startServer() {
      this.serverMonitor = new SocketHubAppender$ServerMonitor(this, this.port, this.oosList);
   }

   public static ServerSocket access$002(SocketHubAppender var0, ServerSocket var1) {
      return var0.serverSocket = var1;
   }

   public void setPort(int var1) {
      this.port = var1;
   }

   public void setApplication(String var1) {
      this.application = var1;
   }

   public void append(LoggingEvent var1) {
      if (var1 != null) {
         if (this.locationInfo) {
            var1.getLocationInformation();
         }

         if (this.application != null) {
            var1.setProperty("application", this.application);
         }

         var1.getNDC();
         var1.getThreadName();
         var1.getMDCCopy();
         var1.getRenderedMessage();
         var1.getThrowableStrRep();
         if (this.buffer != null) {
            this.buffer.add(var1);
         }
      }

      if (var1 != null && this.oosList.size() != 0) {
         for (int var2 = 0; var2 < this.oosList.size(); var2++) {
            ObjectOutputStream var3 = null;

            try {
               var3 = (ObjectOutputStream)this.oosList.elementAt(var2);
            } catch (ArrayIndexOutOfBoundsException var5) {
            }

            if (var3 == null) {
               break;
            }

            try {
               var3.writeObject(var1);
               var3.flush();
               var3.reset();
            } catch (IOException var6) {
               if (var6 instanceof InterruptedIOException) {
                  Thread.currentThread().interrupt();
               }

               this.oosList.removeElementAt(var2);
               LogLog.debug("dropped connection");
               var2--;
            }
         }
      }
   }

   public boolean isAdvertiseViaMulticastDNS() {
      return this.advertiseViaMulticastDNS;
   }

   public int getPort() {
      return this.port;
   }

   public int getBufferSize() {
      return this.buffer == null ? 0 : this.buffer.getMaxSize();
   }

   public SocketHubAppender(int var1) {
      this.locationInfo = false;
      this.buffer = null;
      this.port = var1;
      this.startServer();
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public ServerSocket createServerSocket(int var1) {
      return new ServerSocket(var1);
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }
}
