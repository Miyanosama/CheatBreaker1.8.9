package net.minecraft.client.gui;

import com.google.common.base.Predicate;
import io.netty.util.concurrent.DefaultPromise$3;
import java.net.IDN;
import net.minecraft.crash.CrashReport$6;
import org.java_websocket.AbstractWebSocket;

public class GuiScreenAddServer$1 implements Predicate<String> {
   public CrashReport$6 field_0001;
   public DefaultPromise$3 field_0003;
   public AbstractWebSocket field_0002;

   public boolean apply(String var1) {
      if (var1.length() == 0) {
         return true;
      } else {
         String[] var2 = var1.split(":");
         if (var2.length == 0) {
            return true;
         } else {
            try {
               String var3 = IDN.toASCII(var2[0]);
               return true;
            } catch (IllegalArgumentException var4) {
               return false;
            }
         }
      }
   }

   public GuiScreenAddServer$1(GuiScreenAddServer var1) {
      this.field_181167_a = var1;
      super();
   }
}
