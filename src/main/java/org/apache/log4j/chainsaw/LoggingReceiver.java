package org.apache.log4j.chainsaw;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

public class LoggingReceiver extends Thread {
   public static Logger LOG = Logger.getLogger(
      LoggingReceiver.class$org$apache$log4j$chainsaw$LoggingReceiver == null
         ? (LoggingReceiver.class$org$apache$log4j$chainsaw$LoggingReceiver = class$("org.apache.log4j.chainsaw.LoggingReceiver"))
         : LoggingReceiver.class$org$apache$log4j$chainsaw$LoggingReceiver
   );
   public ServerSocket mSvrSock;
   public MyTableModel mModel;
   public static Class class$org$apache$log4j$chainsaw$LoggingReceiver;

   public LoggingReceiver(MyTableModel var1, int var2) throws java.io.IOException {
      this.setDaemon(true);
      this.mModel = var1;
      this.mSvrSock = new ServerSocket(var2);
   }

   public void run() {
      LOG.info("Thread started");

      try {
         while (true) {
            LOG.debug("Waiting for a connection");
            Socket var1 = this.mSvrSock.accept();
            LOG.debug("Got a connection from " + var1.getInetAddress().getHostName());
            Thread var2 = new Thread(new LoggingReceiver.Slurper(var1));
            var2.setDaemon(true);
            var2.start();
         }
      } catch (IOException var3) {
         LOG.error("Error in accepting connections, stopping.", var3);
      }
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public class Slurper implements Runnable {
      public Socket mClient;

      public Slurper(Socket var2) {
         this.mClient = var2;
      }

      public void run() {
         LoggingReceiver.LOG.debug("Starting to get data");

         try {
            ObjectInputStream var1 = new ObjectInputStream(this.mClient.getInputStream());

            while (true) {
               LoggingEvent var2 = (LoggingEvent)var1.readObject();
               LoggingReceiver.this.mModel.addEvent(new EventDetails(var2));
            }
         } catch (EOFException var4) {
            LoggingReceiver.LOG.info("Reached EOF, closing connection");
         } catch (SocketException var5) {
            LoggingReceiver.LOG.info("Caught SocketException, closing connection");
         } catch (IOException var6) {
            LoggingReceiver.LOG.warn("Got IOException, closing connection", var6);
         } catch (ClassNotFoundException var7) {
            LoggingReceiver.LOG.warn("Got ClassNotFoundException, closing connection", var7);
         }

         try {
            this.mClient.close();
         } catch (IOException var3) {
            LoggingReceiver.LOG.warn("Error closing connection", var3);
         }
      }
   }
}
