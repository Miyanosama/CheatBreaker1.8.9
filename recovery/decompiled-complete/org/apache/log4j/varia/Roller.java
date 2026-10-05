package org.apache.log4j.varia;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import net.minecraft.client.renderer.BlockModelRenderer$Orientation;
import net.minecraft.util.Util;
import net.minecraft.world.gen.ChunkProviderServer;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Roller {
   public static String host;
   public ChunkProviderServer field_0005;
   public Util field_0002;
   public static Logger cat = Logger.getLogger(
      Roller.class$org$apache$log4j$varia$Roller == null
         ? (Roller.class$org$apache$log4j$varia$Roller = class$("org.apache.log4j.varia.Roller"))
         : Roller.class$org$apache$log4j$varia$Roller
   );
   public static int port;
   public BlockModelRenderer$Orientation field_0001;
   public static Class class$org$apache$log4j$varia$Roller;

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public static void roll() {
      try {
         Socket var0 = new Socket(host, port);
         DataOutputStream var1 = new DataOutputStream(var0.getOutputStream());
         DataInputStream var2 = new DataInputStream(var0.getInputStream());
         var1.writeUTF("RollOver");
         String var3 = var2.readUTF();
         if ("OK".equals(var3)) {
            cat.info("Roll over signal acknowledged by remote appender.");
         } else {
            cat.warn("Unexpected return code " + var3 + " from remote entity.");
            System.exit(2);
         }
      } catch (IOException var4) {
         cat.error("Could not send roll signal on host " + host + " port " + port + " .", var4);
         System.exit(2);
      }

      System.exit(0);
   }

   public static void usage(String var0) {
      System.err.println(var0);
      System.err
         .println(
            "Usage: java "
               + (class$org$apache$log4j$varia$Roller == null
                     ? (class$org$apache$log4j$varia$Roller = class$("org.apache.log4j.varia.Roller"))
                     : class$org$apache$log4j$varia$Roller)
                  .getName()
               + "host_name port_number"
         );
      System.exit(1);
   }

   public static void init(String var0, String var1) {
      host = var0;

      try {
         port = Integer.parseInt(var1);
      } catch (NumberFormatException var3) {
         usage("Second argument " + var1 + " is not a valid integer.");
      }
   }

   public static void main(String[] var0) {
      BasicConfigurator.configure();
      if (var0.length == 2) {
         init(var0[0], var0[1]);
      } else {
         usage("Wrong number of arguments.");
      }

      roll();
   }
}
