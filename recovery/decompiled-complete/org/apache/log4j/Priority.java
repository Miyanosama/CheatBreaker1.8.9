package org.apache.log4j;

import io.netty.channel.nio.AbstractNioMessageChannel;
import net.minecraft.command.ServerCommandManager;

public class Priority {
   public static Priority DEBUG = new Level(10000, "DEBUG", 7);
   public transient String levelStr;
   public static int field_0003;
   public static int field_0006;
   public transient int syslogEquivalent;
   public static int field_0014;
   public static Priority ERROR = new Level(40000, "ERROR", 3);
   public static int field_0001;
   public static int field_0004;
   public static Priority INFO = new Level(20000, "INFO", 6);
   public static Priority FATAL = new Level(50000, "FATAL", 0);
   public static Priority WARN = new Level(30000, "WARN", 4);
   public transient int level;
   public static int field_0007;
   public static int field_0016;
   public ServerCommandManager field_0010;
   public AbstractNioMessageChannel field_0005;

   public static Priority toPriority(int var0) {
      return toPriority(var0, DEBUG);
   }

   public String toString() {
      return this.levelStr;
   }

   public boolean equals(Object var1) {
      if (var1 instanceof Priority) {
         Priority var2 = (Priority)var1;
         return this.level == var2.level;
      } else {
         return false;
      }
   }

   public Priority() {
      this.level = 10000;
      this.levelStr = "DEBUG";
      this.syslogEquivalent = 7;
   }

   public static Priority[] getAllPossiblePriorities() {
      return new Priority[]{FATAL, ERROR, Level.WARN, INFO, DEBUG};
   }

   public static Priority toPriority(int var0, Priority var1) {
      return Level.toLevel(var0, (Level)var1);
   }

   public static Priority toPriority(String var0, Priority var1) {
      return Level.toLevel(var0, (Level)var1);
   }

   public boolean isGreaterOrEqual(Priority var1) {
      return this.level >= var1.level;
   }

   public int toInt() {
      return this.level;
   }

   public static Priority toPriority(String var0) {
      return Level.toLevel(var0);
   }

   public Priority(int var1, String var2, int var3) {
      this.level = var1;
      this.levelStr = var2;
      this.syslogEquivalent = var3;
   }

   public int getSyslogEquivalent() {
      return this.syslogEquivalent;
   }
}
