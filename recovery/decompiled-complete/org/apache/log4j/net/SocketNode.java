package org.apache.log4j.net;

import io.netty.handler.codec.compression.JdkZlibEncoder$3;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import net.minecraft.client.gui.GuiScreenDemo;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

public class SocketNode implements Runnable {
   public ObjectInputStream ois;
   public JdkZlibEncoder$3 field_0006;
   public static Logger logger = Logger.getLogger(
      SocketNode.class$org$apache$log4j$net$SocketNode == null
         ? (SocketNode.class$org$apache$log4j$net$SocketNode = class$("org.apache.log4j.net.SocketNode"))
         : SocketNode.class$org$apache$log4j$net$SocketNode
   );
   public Socket socket;
   public static Class class$org$apache$log4j$net$SocketNode;
   public GuiScreenDemo field_0001;
   public LoggerRepository hierarchy;
   public InternalLoggerFactory field_0004;

   public void run() {
      try {
         if (this.ois != null) {
            while (true) {
               LoggingEvent var1 = (LoggingEvent)this.ois.readObject();
               Logger var2 = this.hierarchy.getLogger(var1.getLoggerName());
               if (var1.getLevel().isGreaterOrEqual(var2.getEffectiveLevel())) {
                  var2.callAppenders(var1);
               }
            }
         }
      } catch (EOFException var36) {
         logger.info("Caught java.io.EOFException closing conneciton.");
      } catch (SocketException var37) {
         logger.info("Caught java.net.SocketException closing conneciton.");
      } catch (InterruptedIOException var38) {
         Thread.currentThread().interrupt();
         logger.info("Caught java.io.InterruptedIOException: " + var38);
         logger.info("Closing connection.");
      } catch (IOException var39) {
         logger.info("Caught java.io.IOException: " + var39);
         logger.info("Closing connection.");
      } catch (Exception var40) {
         logger.error("Unexpected exception. Closing conneciton.", var40);
      } finally {
         if (this.ois != null) {
            try {
               this.ois.close();
            } catch (Exception var35) {
               logger.info("Could not close connection.", var35);
            }
         }

         if (this.socket != null) {
            try {
               this.socket.close();
            } catch (InterruptedIOException var33) {
               Thread.currentThread().interrupt();
            } catch (IOException var34) {
            }
         }
      }
   }

   public SocketNode(Socket var1, LoggerRepository var2) {
      this.socket = var1;
      this.hierarchy = var2;

      try {
         this.ois = new ObjectInputStream(new BufferedInputStream(var1.getInputStream()));
      } catch (InterruptedIOException var4) {
         Thread.currentThread().interrupt();
         logger.error("Could not open ObjectInputStream to " + var1, var4);
      } catch (IOException var5) {
         logger.error("Could not open ObjectInputStream to " + var1, var5);
      } catch (RuntimeException var6) {
         logger.error("Could not open ObjectInputStream to " + var1, var6);
      }
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }
}
