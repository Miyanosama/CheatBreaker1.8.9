package org.apache.log4j.varia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import org.apache.log4j.helpers.LogLog;

public class HUPNode implements Runnable {
   public ExternallyRolledFileAppender er;
   public DataInputStream dis;
   public DataOutputStream dos;
   public Socket socket;

   public void run() {
      try {
         String var1 = this.dis.readUTF();
         LogLog.debug("Got external roll over signal.");
         if ("RollOver".equals(var1)) {
            synchronized (this.er) {
               this.er.rollOver();
            }

            this.dos.writeUTF("OK");
         } else {
            this.dos.writeUTF("Expecting [RollOver] string.");
         }

         this.dos.close();
      } catch (InterruptedIOException var5) {
         Thread.currentThread().interrupt();
         LogLog.error("Unexpected exception. Exiting HUPNode.", var5);
      } catch (IOException var6) {
         LogLog.error("Unexpected exception. Exiting HUPNode.", var6);
      } catch (RuntimeException var7) {
         LogLog.error("Unexpected exception. Exiting HUPNode.", var7);
      }
   }

   public HUPNode(Socket var1, ExternallyRolledFileAppender var2) {
      this.socket = var1;
      this.er = var2;

      try {
         this.dis = new DataInputStream(var1.getInputStream());
         this.dos = new DataOutputStream(var1.getOutputStream());
      } catch (InterruptedIOException var4) {
         Thread.currentThread().interrupt();
         var4.printStackTrace();
      } catch (IOException var5) {
         var5.printStackTrace();
      } catch (RuntimeException var6) {
         var6.printStackTrace();
      }
   }
}
