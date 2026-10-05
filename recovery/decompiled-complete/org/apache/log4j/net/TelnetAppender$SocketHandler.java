package org.apache.log4j.net;

import io.netty.handler.codec.serialization.ReferenceMap;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Deserializer;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.optifine.shaders.uniform.ShaderParameterFloat;
import org.apache.log4j.helpers.LogLog;

public class TelnetAppender$SocketHandler extends Thread {
   public ReferenceMap field_0004;
   public TelnetAppender this$0;
   public Vector writers;
   public DemoWorldServer field_0006;
   public ModelBlockDefinition$Deserializer field_0000;
   public ServerSocket serverSocket;
   public Vector connections;
   public GenLayerHills field_0005;
   public int MAX_CONNECTIONS;
   public ShaderParameterFloat field_0009;

   public TelnetAppender$SocketHandler(TelnetAppender var1, int var2) {
      this.this$0 = var1;
      super();
      this.writers = new Vector();
      this.connections = new Vector();
      this.MAX_CONNECTIONS = 20;
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
