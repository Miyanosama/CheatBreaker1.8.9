package org.apache.log4j.helpers;

import io.netty.util.HashedWheelTimer$HashedWheelTimeout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.Set;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.realms.RealmsServerStatusPinger$1;
import org.apache.log4j.pattern.LogEvent;
import org.apache.log4j.spi.LoggingEvent;

public class MDCKeySetExtractor {
   public static Class class$org$apache$log4j$pattern$LogEvent;
   public Method getKeySetMethod;
   public RealmsServerStatusPinger$1 field_0002;
   public GuiScreenRealmsProxy field_0004;
   public static MDCKeySetExtractor INSTANCE = new MDCKeySetExtractor();
   public HashedWheelTimer$HashedWheelTimeout field_0001;
   public static Class class$org$apache$log4j$spi$LoggingEvent;

   public MDCKeySetExtractor() {
      Object var1 = null;

      try {
         var1 = (class$org$apache$log4j$spi$LoggingEvent == null
               ? (class$org$apache$log4j$spi$LoggingEvent = class$("org.apache.log4j.spi.LoggingEvent"))
               : class$org$apache$log4j$spi$LoggingEvent)
            .getMethod("getPropertyKeySet", null);
      } catch (Exception var3) {
         var1 = null;
      }

      this.getKeySetMethod = (Method)var1;
   }

   public Set getPropertyKeySet(LoggingEvent var1) {
      Set var2 = null;
      if (this.getKeySetMethod != null) {
         var2 = (Set)this.getKeySetMethod.invoke(var1, null);
      } else {
         ByteArrayOutputStream var3 = new ByteArrayOutputStream();
         ObjectOutputStream var4 = new ObjectOutputStream(var3);
         var4.writeObject(var1);
         var4.close();
         byte[] var5 = var3.toByteArray();
         String var6 = (class$org$apache$log4j$pattern$LogEvent == null
               ? (class$org$apache$log4j$pattern$LogEvent = class$("org.apache.log4j.pattern.LogEvent"))
               : class$org$apache$log4j$pattern$LogEvent)
            .getName();
         if (var5[6] == 0 || var5[7] == var6.length()) {
            for (int var7 = 0; var7 < var6.length(); var7++) {
               var5[8 + var7] = (byte)var6.charAt(var7);
            }

            ByteArrayInputStream var10 = new ByteArrayInputStream(var5);
            ObjectInputStream var8 = new ObjectInputStream(var10);
            Object var9 = var8.readObject();
            if (var9 instanceof LogEvent) {
               var2 = ((LogEvent)var9).getPropertyKeySet();
            }

            var8.close();
         }
      }

      return var2;
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }
}
