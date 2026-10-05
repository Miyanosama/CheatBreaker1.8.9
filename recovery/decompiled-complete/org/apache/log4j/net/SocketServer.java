package org.apache.log4j.net;

import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.server.management.UserListEntry;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RootLogger;

public class SocketServer {
   public UserListEntry field_0005;
   public static SocketServer server;
   public LoggerRepository genericHierarchy;
   public static int port;
   public Hashtable hierarchyMap;
   public File dir;
   public static String GENERIC = "generic";
   public ModelResourceLocation field_0006;
   public static String CONFIG_FILE_EXT = ".lcf";
   public static Logger cat = Logger.getLogger(
      SocketServer.class$org$apache$log4j$net$SocketServer == null
         ? (SocketServer.class$org$apache$log4j$net$SocketServer = class$("org.apache.log4j.net.SocketServer"))
         : SocketServer.class$org$apache$log4j$net$SocketServer
   );
   public static Class class$org$apache$log4j$net$SocketServer;

   public static void main(String[] var0) {
      if (var0.length == 3) {
         init(var0[0], var0[1], var0[2]);
      } else {
         usage("Wrong number of arguments.");
      }

      try {
         cat.info("Listening on port " + port);
         ServerSocket var1 = new ServerSocket(port);

         while (true) {
            cat.info("Waiting to accept a new client.");
            Socket var2 = var1.accept();
            InetAddress var3 = var2.getInetAddress();
            cat.info("Connected to client at " + var3);
            LoggerRepository var4 = (LoggerRepository)server.hierarchyMap.get(var3);
            if (var4 == null) {
               var4 = server.configureHierarchy(var3);
            }

            cat.info("Starting new socket node.");
            new Thread(new SocketNode(var2, var4)).start();
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }

   public static void usage(String var0) {
      System.err.println(var0);
      System.err
         .println(
            "Usage: java "
               + (class$org$apache$log4j$net$SocketServer == null
                     ? (class$org$apache$log4j$net$SocketServer = class$("org.apache.log4j.net.SocketServer"))
                     : class$org$apache$log4j$net$SocketServer)
                  .getName()
               + " port configFile directory"
         );
      System.exit(1);
   }

   public LoggerRepository genericHierarchy() {
      if (this.genericHierarchy == null) {
         File var1 = new File(this.dir, GENERIC + CONFIG_FILE_EXT);
         if (var1.exists()) {
            this.genericHierarchy = new Hierarchy(new RootLogger(Level.DEBUG));
            new PropertyConfigurator().doConfigure(var1.getAbsolutePath(), this.genericHierarchy);
         } else {
            cat.warn("Could not find config file [" + var1 + "]. Will use the default hierarchy.");
            this.genericHierarchy = LogManager.getLoggerRepository();
         }
      }

      return this.genericHierarchy;
   }

   public SocketServer(File var1) {
      this.dir = var1;
      this.hierarchyMap = new Hashtable(11);
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public LoggerRepository configureHierarchy(InetAddress var1) {
      cat.info("Locating configuration file for " + var1);
      String var2 = var1.toString();
      int var3 = var2.indexOf("/");
      if (var3 == -1) {
         cat.warn("Could not parse the inetAddress [" + var1 + "]. Using default hierarchy.");
         return this.genericHierarchy();
      } else {
         String var4 = var2.substring(0, var3);
         File var5 = new File(this.dir, var4 + CONFIG_FILE_EXT);
         if (var5.exists()) {
            Hierarchy var6 = new Hierarchy(new RootLogger(Level.DEBUG));
            this.hierarchyMap.put(var1, var6);
            new PropertyConfigurator().doConfigure(var5.getAbsolutePath(), var6);
            return var6;
         } else {
            cat.warn("Could not find config file [" + var5 + "].");
            return this.genericHierarchy();
         }
      }
   }

   public static void init(String var0, String var1, String var2) {
      try {
         port = Integer.parseInt(var0);
      } catch (NumberFormatException var4) {
         var4.printStackTrace();
         usage("Could not interpret port number [" + var0 + "].");
      }

      PropertyConfigurator.configure(var1);
      File var3 = new File(var2);
      if (!var3.isDirectory()) {
         usage("[" + var2 + "] is not a directory.");
      }

      server = new SocketServer(var3);
   }
}
