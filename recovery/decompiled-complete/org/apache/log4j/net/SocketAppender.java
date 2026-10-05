package org.apache.log4j.net;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import net.minecraft.client.model.ModelHorse;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.lf5.viewer.TrackingAdjustmentListener;
import org.apache.log4j.spi.LoggingEvent;

public class SocketAppender extends AppenderSkeleton {
   public String remoteHost;
   public static int field_0005;
   public int counter;
   public TrackingAdjustmentListener field_0011;
   public ModelHorse field_0007;
   public static int field_0012;
   public static int field_0015;
   public ObjectOutputStream oos;
   public String application;
   public int port = 4560;
   public static String field_0014;
   public ZeroConfSupport zeroConf;
   public boolean advertiseViaMulticastDNS;
   public InetAddress address;
   public SocketAppender$Connector connector;
   public int reconnectionDelay = 30000;
   public boolean locationInfo = false;

   public void cleanUp() {
      if (this.oos != null) {
         try {
            this.oos.close();
         } catch (IOException var2) {
            if (var2 instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            LogLog.error("Could not close oos.", var2);
         }

         this.oos = null;
      }

      if (this.connector != null) {
         this.connector.interrupted = true;
         this.connector = null;
      }
   }

   public void fireConnector() {
      if (this.connector == null) {
         LogLog.debug("Starting a new connector thread.");
         this.connector = new SocketAppender$Connector(this);
         this.connector.setDaemon(true);
         this.connector.setPriority(1);
         this.connector.start();
      }
   }

   public void setAdvertiseViaMulticastDNS(boolean var1) {
      this.advertiseViaMulticastDNS = var1;
   }

   public void append(LoggingEvent var1) {
      if (var1 != null) {
         if (this.address == null) {
            this.errorHandler.error("No remote host is set for SocketAppender named \"" + this.name + "\".");
         } else {
            if (this.oos != null) {
               try {
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
                  this.oos.writeObject(var1);
                  this.oos.flush();
                  if (++this.counter >= 1) {
                     this.counter = 0;
                     this.oos.reset();
                  }
               } catch (IOException var3) {
                  if (var3 instanceof InterruptedIOException) {
                     Thread.currentThread().interrupt();
                  }

                  this.oos = null;
                  LogLog.warn("Detected problem with connection: " + var3);
                  if (this.reconnectionDelay > 0) {
                     this.fireConnector();
                  } else {
                     this.errorHandler.error("Detected problem with connection, not reconnecting.", var3, 0);
                  }
               }
            }
         }
      }
   }

   public SocketAppender(String var1, int var2) {
      this.counter = 0;
      this.port = var2;
      this.address = getAddressByName(var1);
      this.remoteHost = var1;
      this.connect(this.address, var2);
   }

   public void connect(InetAddress var1, int var2) {
      if (this.address != null) {
         try {
            this.cleanUp();
            this.oos = new ObjectOutputStream(new Socket(var1, var2).getOutputStream());
         } catch (IOException var5) {
            if (var5 instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            String var4 = "Could not connect to remote log4j server at [" + var1.getHostName() + "].";
            if (this.reconnectionDelay > 0) {
               var4 = var4 + " We will try again later.";
               this.fireConnector();
            } else {
               var4 = var4 + " We are not retrying.";
               this.errorHandler.error(var4, var5, 0);
            }

            LogLog.error(var4);
         }
      }
   }

   public static InetAddress getAddressByName(String var0) {
      try {
         return InetAddress.getByName(var0);
      } catch (Exception var2) {
         if (var2 instanceof InterruptedIOException || var2 instanceof InterruptedException) {
            Thread.currentThread().interrupt();
         }

         LogLog.error("Could not find address of [" + var0 + "].", var2);
         return null;
      }
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public int getPort() {
      return this.port;
   }

   public String getRemoteHost() {
      return this.remoteHost;
   }

   public int getReconnectionDelay() {
      return this.reconnectionDelay;
   }

   public SocketAppender() {
      this.counter = 0;
   }

   public SocketAppender(InetAddress var1, int var2) {
      this.counter = 0;
      this.address = var1;
      this.remoteHost = var1.getHostName();
      this.port = var2;
      this.connect(var1, var2);
   }

   public void setPort(int var1) {
      this.port = var1;
   }

   public void setRemoteHost(String var1) {
      this.address = getAddressByName(var1);
      this.remoteHost = var1;
   }

   public synchronized void close() {
      if (!this.closed) {
         this.closed = true;
         if (this.advertiseViaMulticastDNS) {
            this.zeroConf.unadvertise();
         }

         this.cleanUp();
      }
   }

   public String getApplication() {
      return this.application;
   }

   public void activateOptions() {
      if (this.advertiseViaMulticastDNS) {
         this.zeroConf = new ZeroConfSupport("_log4j_obj_tcpconnect_appender.local.", this.port, this.getName());
         this.zeroConf.advertise();
      }

      this.connect(this.address, this.port);
   }

   public void setApplication(String var1) {
      this.application = var1;
   }

   public boolean requiresLayout() {
      return false;
   }

   public void setReconnectionDelay(int var1) {
      this.reconnectionDelay = var1;
   }

   public boolean isAdvertiseViaMulticastDNS() {
      return this.advertiseViaMulticastDNS;
   }

   public static SocketAppender$Connector access$002(SocketAppender var0, SocketAppender$Connector var1) {
      return var0.connector = var1;
   }
}
