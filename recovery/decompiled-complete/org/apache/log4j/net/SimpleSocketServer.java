package org.apache.log4j.net;

import io.netty.util.concurrent.ImmediateExecutor;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class SimpleSocketServer {
   public static Logger cat = Logger.getLogger(
      SimpleSocketServer.class$org$apache$log4j$net$SimpleSocketServer == null
         ? (SimpleSocketServer.class$org$apache$log4j$net$SimpleSocketServer = class$("org.apache.log4j.net.SimpleSocketServer"))
         : SimpleSocketServer.class$org$apache$log4j$net$SimpleSocketServer
   );
   public static Class class$org$apache$log4j$net$SimpleSocketServer;
   public ImmediateExecutor field_0000;
   public static int port;

   public static void init(String var0, String var1) {
      try {
         port = Integer.parseInt(var0);
      } catch (NumberFormatException var3) {
         var3.printStackTrace();
         usage("Could not interpret port number [" + var0 + "].");
      }

      if (var1.endsWith(".xml")) {
         DOMConfigurator.configure(var1);
      } else {
         PropertyConfigurator.configure(var1);
      }
   }

   public static void usage(String var0) {
      System.err.println(var0);
      System.err
         .println(
            "Usage: java "
               + (class$org$apache$log4j$net$SimpleSocketServer == null
                     ? (class$org$apache$log4j$net$SimpleSocketServer = class$("org.apache.log4j.net.SimpleSocketServer"))
                     : class$org$apache$log4j$net$SimpleSocketServer)
                  .getName()
               + " port configFile"
         );
      System.exit(1);
   }

   public static void main(String[] var0) {
      if (var0.length == 2) {
         init(var0[0], var0[1]);
      } else {
         usage("Wrong number of arguments.");
      }

      try {
         cat.info("Listening on port " + port);
         ServerSocket var1 = new ServerSocket(port);

         while (true) {
            cat.info("Waiting to accept a new client.");
            Socket var2 = var1.accept();
            cat.info("Connected to client at " + var2.getInetAddress());
            cat.info("Starting new socket node.");
            new Thread(new SocketNode(var2, LogManager.getLoggerRepository()), "SimpleSocketServer-" + port).start();
         }
      } catch (Exception var3) {
         var3.printStackTrace();
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
