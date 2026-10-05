package org.apache.log4j.net;

import io.netty.channel.DefaultChannelPipeline$HeadContext;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import net.optifine.shaders.uniform.Smoother;
import org.apache.log4j.helpers.LogLog;

public class SocketHubAppender$ServerMonitor implements Runnable {
   public Smoother field_0003;
   public Vector oosList;
   public int port;
   public boolean keepRunning;
   public SocketHubAppender this$0;
   public Thread monitorThread;
   public DefaultChannelPipeline$HeadContext field_0006;

   public SocketHubAppender$ServerMonitor(SocketHubAppender var1, int var2, Vector var3) {
      this.this$0 = var1;
      super();
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
            if (SocketHubAppender.access$000(this.this$0) != null) {
               SocketHubAppender.access$000(this.this$0).close();
               SocketHubAppender.access$002(this.this$0, null);
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
      SocketHubAppender.access$002(this.this$0, null);

      try {
         SocketHubAppender.access$002(this.this$0, this.this$0.createServerSocket(this.port));
         SocketHubAppender.access$000(this.this$0).setSoTimeout(1000);
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
            SocketHubAppender.access$000(this.this$0).setSoTimeout(1000);
         } catch (SocketException var26) {
            LogLog.error("exception setting timeout, shutting down server socket.", var26);
            return;
         }

         while (this.keepRunning) {
            Socket var1 = null;

            try {
               var1 = SocketHubAppender.access$000(this.this$0).accept();
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
                  if (SocketHubAppender.access$100(this.this$0) != null && SocketHubAppender.access$100(this.this$0).length() > 0) {
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
            SocketHubAppender.access$000(this.this$0).close();
         } catch (InterruptedIOException var19) {
            Thread.currentThread().interrupt();
         } catch (IOException var20) {
         }
      }
   }

   public void sendCachedEvents(ObjectOutputStream var1) {
      if (SocketHubAppender.access$100(this.this$0) != null) {
         for (int var2 = 0; var2 < SocketHubAppender.access$100(this.this$0).length(); var2++) {
            var1.writeObject(SocketHubAppender.access$100(this.this$0).get(var2));
         }

         var1.flush();
         var1.reset();
      }
   }
}
