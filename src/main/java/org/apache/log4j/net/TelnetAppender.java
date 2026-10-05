package org.apache.log4j.net;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class TelnetAppender extends AppenderSkeleton {
   public int port = 23;
   public TelnetAppender.SocketHandler sh;

   public void close() {
      if (this.sh != null) {
         this.sh.close();

         try {
            this.sh.join();
         } catch (InterruptedException var2) {
            Thread.currentThread().interrupt();
         }
      }
   }

   public void activateOptions() {
      try {
         this.sh = new TelnetAppender.SocketHandler(this.port);
         this.sh.start();
      } catch (InterruptedIOException var2) {
         Thread.currentThread().interrupt();
         var2.printStackTrace();
      } catch (IOException var3) {
         var3.printStackTrace();
      } catch (RuntimeException var4) {
         var4.printStackTrace();
      }

      super.activateOptions();
   }

   public void append(LoggingEvent var1) {
      if (this.sh != null) {
         this.sh.send(this.layout.format(var1));
         if (this.layout.ignoresThrowable()) {
            String[] var2 = var1.getThrowableStrRep();
            if (var2 != null) {
               StringBuffer var3 = new StringBuffer();

               for (int var4 = 0; var4 < var2.length; var4++) {
                  var3.append(var2[var4]);
                  var3.append("\r\n");
               }

               this.sh.send(var3.toString());
            }
         }
      }
   }

   public int getPort() {
      return this.port;
   }

   public boolean requiresLayout() {
      return true;
   }

   public void setPort(int var1) {
      this.port = var1;
   }

   public class SocketHandler extends Thread {
      public Vector writers = new Vector();
      public ServerSocket serverSocket;
      public Vector connections = new Vector();
      public int MAX_CONNECTIONS = 20;

      public SocketHandler(int var2) throws java.io.IOException {
         this.serverSocket = new ServerSocket(var2);
         this.setName("TelnetAppender-" + this.getName() + "-" + var2);
      }

      public void run() {
         while (!this.serverSocket.isClosed()) {
            try {
               Socket var1 = this.serverSocket.accept();
               PrintWriter var2 = new PrintWriter(var1.getOutputStream());
               if (this.connections.size() < this.MAX_CONNECTIONS) {
                  synchronized (this) {
                     this.connections.addElement(var1);
                     this.writers.addElement(var2);
                     var2.print("TelnetAppender v1.0 (" + this.connections.size() + " active connections)\r\n\r\n");
                     var2.flush();
                  }
               } else {
                  var2.print("Too many connections.\r\n");
                  var2.flush();
                  var1.close();
               }
            } catch (Exception var8) {
               if (var8 instanceof InterruptedIOException || var8 instanceof InterruptedException) {
                  Thread.currentThread().interrupt();
               }

               if (!this.serverSocket.isClosed()) {
                  LogLog.error("Encountered error while in SocketHandler loop.", var8);
               }
               break;
            }
         }

         try {
            this.serverSocket.close();
         } catch (InterruptedIOException var5) {
            Thread.currentThread().interrupt();
         } catch (IOException var6) {
         }
      }

      public synchronized void send(String var1) {
         Iterator var2 = this.connections.iterator();
         Iterator var3 = this.writers.iterator();

         while (var3.hasNext()) {
            var2.next();
            PrintWriter var4 = (PrintWriter)var3.next();
            var4.print(var1);
            if (var4.checkError()) {
               var2.remove();
               var3.remove();
            }
         }
      }

      public void finalize() {
         this.close();
      }

      public void close() {
         synchronized (this) {
            Enumeration var2 = this.connections.elements();

            while (var2.hasMoreElements()) {
               try {
                  ((Socket)var2.nextElement()).close();
               } catch (InterruptedIOException var8) {
                  Thread.currentThread().interrupt();
               } catch (IOException var9) {
               } catch (RuntimeException var10) {
               }
            }
         }

         try {
            this.serverSocket.close();
         } catch (InterruptedIOException var5) {
            Thread.currentThread().interrupt();
         } catch (IOException var6) {
         } catch (RuntimeException var7) {
         }
      }
   }
}
