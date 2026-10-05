package org.apache.log4j.net;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
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
   public Vector oosList = new Vector();
   public static final String recoveredField3749 = "_log4j_obj_tcpaccept_appender.local.";
   public boolean advertiseViaMulticastDNS;
   public static final int recoveredField3750 = 4560;
   public SocketHubAppender.ServerMonitor serverMonitor = null;
   public String application;

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

   public SocketHubAppender() {
      this.locationInfo = false;
      this.buffer = null;
   }

   public String getApplication() {
      return this.application;
   }

   public void startServer() {
      this.serverMonitor = new SocketHubAppender.ServerMonitor(this.port, this.oosList);
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

   public ServerSocket createServerSocket(int var1) throws java.io.IOException {
      return new ServerSocket(var1);
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public class ServerMonitor implements Runnable {
      public Vector oosList;
      public int port;
      public boolean keepRunning;
      public Thread monitorThread;

      public ServerMonitor(int var2, Vector var3) {
         this.port = var2;
         this.oosList = var3;
         this.keepRunning = true;
         this.monitorThread = new Thread(this);
         this.monitorThread.setDaemon(true);
         this.monitorThread.setName("SocketHubAppender-Monitor-" + this.port);
         this.monitorThread.start();
      }

      public synchronized void stopMonitor() {
         if (this.keepRunning) {
            LogLog.debug("server monitor thread shutting down");
            this.keepRunning = false;

            try {
               if (SocketHubAppender.this.serverSocket != null) {
                  SocketHubAppender.this.serverSocket.close();
                  SocketHubAppender.this.serverSocket = null;
               }
            } catch (IOException var3) {
            }

            try {
               this.monitorThread.join();
            } catch (InterruptedException var2) {
               Thread.currentThread().interrupt();
            }

            this.monitorThread = null;
            LogLog.debug("server monitor thread shut down");
         }
      }

      public void run() {
         SocketHubAppender.this.serverSocket = null;

         try {
            SocketHubAppender.this.serverSocket = SocketHubAppender.this.createServerSocket(this.port);
            SocketHubAppender.this.serverSocket.setSoTimeout(1000);
         } catch (Exception var24) {
            if (var24 instanceof InterruptedIOException || var24 instanceof InterruptedException) {
               Thread.currentThread().interrupt();
            }

            LogLog.error("exception setting timeout, shutting down server socket.", var24);
            this.keepRunning = false;
            return;
         }

         try {
            try {
               SocketHubAppender.this.serverSocket.setSoTimeout(1000);
            } catch (SocketException var26) {
               LogLog.error("exception setting timeout, shutting down server socket.", var26);
               return;
            }

            while (this.keepRunning) {
               Socket var1 = null;

               try {
                  var1 = SocketHubAppender.this.serverSocket.accept();
               } catch (InterruptedIOException var21) {
               } catch (SocketException var22) {
                  LogLog.error("exception accepting socket, shutting down server socket.", var22);
                  this.keepRunning = false;
               } catch (IOException var23) {
                  LogLog.error("exception accepting socket.", var23);
               }

               if (var1 != null) {
                  try {
                     InetAddress var2 = var1.getInetAddress();
                     LogLog.debug("accepting connection from " + var2.getHostName() + " (" + var2.getHostAddress() + ")");
                     ObjectOutputStream var3 = new ObjectOutputStream(var1.getOutputStream());
                     if (SocketHubAppender.this.buffer != null && SocketHubAppender.this.buffer.length() > 0) {
                        this.sendCachedEvents(var3);
                     }

                     this.oosList.addElement(var3);
                  } catch (IOException var25) {
                     if (var25 instanceof InterruptedIOException) {
                        Thread.currentThread().interrupt();
                     }

                     LogLog.error("exception creating output stream on socket.", var25);
                  }
               }
            }
         } finally {
            try {
               SocketHubAppender.this.serverSocket.close();
            } catch (InterruptedIOException var19) {
               Thread.currentThread().interrupt();
            } catch (IOException var20) {
            }
         }
      }

      public void sendCachedEvents(ObjectOutputStream var1) throws java.io.IOException {
         if (SocketHubAppender.this.buffer != null) {
            for (int var2 = 0; var2 < SocketHubAppender.this.buffer.length(); var2++) {
               var1.writeObject(SocketHubAppender.this.buffer.get(var2));
            }

            var1.flush();
            var1.reset();
         }
      }
   }
}
