package org.apache.log4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.world.ChunkCoordIntPair;
import org.apache.log4j.pattern.PatternParser$ReadOnlyMap;

public class Level extends Priority implements Serializable {
   public static Level FATAL = new Level(50000, "FATAL", 0);
   public static Level DEBUG = new Level(10000, "DEBUG", 7);
   public static int field_0005;
   public static Level ERROR = new Level(40000, "ERROR", 3);
   public static Class class$org$apache$log4j$Level;
   public static Level INFO = new Level(20000, "INFO", 6);
   public static Level OFF = new Level(Integer.MAX_VALUE, "OFF", 0);
   public static long field_0009;
   public PatternParser$ReadOnlyMap field_0003;
   public ChunkCoordIntPair field_0013;
   public static Level ALL = new Level(Integer.MIN_VALUE, "ALL", 7);
   public static Level TRACE = new Level(5000, "TRACE", 7);
   public static Level WARN = new Level(30000, "WARN", 4);
   public S2EPacketCloseWindow field_0004;

   public void writeObject(ObjectOutputStream var1) {
      var1.defaultWriteObject();
      var1.writeInt(this.level);
      var1.writeInt(this.syslogEquivalent);
      var1.writeUTF(this.levelStr);
   }

   public static Level toLevel(String var0) {
      return toLevel(var0, DEBUG);
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public Level(int var1, String var2, int var3) {
      super(var1, var2, var3);
   }

   public static Level toLevel(int var0) {
      return toLevel(var0, DEBUG);
   }

   public Object readResolve() {
      return this.getClass()
            == (class$org$apache$log4j$Level == null ? (class$org$apache$log4j$Level = class$("org.apache.log4j.Level")) : class$org$apache$log4j$Level)
         ? toLevel(this.level)
         : this;
   }

   public static Level toLevel(int var0, Level var1) {
      switch (var0) {
         case Integer.MIN_VALUE:
            return ALL;
         case 5000:
            return TRACE;
         case 10000:
            return DEBUG;
         case 20000:
            return INFO;
         case 30000:
            return WARN;
         case 40000:
            return ERROR;
         case 50000:
            return FATAL;
         case Integer.MAX_VALUE:
            return OFF;
         default:
            return var1;
      }
   }

   public void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      this.level = var1.readInt();
      this.syslogEquivalent = var1.readInt();
      this.levelStr = var1.readUTF();
      if (this.levelStr == null) {
         this.levelStr = "";
      }
   }

   public static Level toLevel(String var0, Level var1) {
      if (var0 == null) {
         return var1;
      } else {
         String var2 = var0.toUpperCase();
         if (var2.equals("ALL")) {
            return ALL;
         } else if (var2.equals("DEBUG")) {
            return DEBUG;
         } else if (var2.equals("INFO")) {
            return INFO;
         } else if (var2.equals("WARN")) {
            return WARN;
         } else if (var2.equals("ERROR")) {
            return ERROR;
         } else if (var2.equals("FATAL")) {
            return FATAL;
         } else if (var2.equals("OFF")) {
            return OFF;
         } else if (var2.equals("TRACE")) {
            return TRACE;
         } else {
            return var2.equals("İNFO") ? INFO : var1;
         }
      }
   }
}
