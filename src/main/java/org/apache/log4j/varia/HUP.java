package org.apache.log4j.varia;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.helpers.LogLog;

public class HUP extends Thread {
   public int port;
   public ExternallyRolledFileAppender er;

   public HUP(ExternallyRolledFileAppender var1, int var2) {
      this.er = var1;
      this.port = var2;
   }

   public void run() {
      while (!this.isInterrupted()) {
         try {
            ServerSocket var1 = new ServerSocket(this.port);

            while (true) {
               Socket var2 = var1.accept();
               LogLog.debug("Connected to client at " + var2.getInetAddress());
               new Thread(new HUPNode(var2, this.er), "ExternallyRolledFileAppender-HUP").start();
            }
         } catch (InterruptedIOException var3) {
            Thread.currentThread().interrupt();
            var3.printStackTrace();
         } catch (IOException var4) {
            var4.printStackTrace();
         } catch (RuntimeException var5) {
            var5.printStackTrace();
         }
      }
   }
}
