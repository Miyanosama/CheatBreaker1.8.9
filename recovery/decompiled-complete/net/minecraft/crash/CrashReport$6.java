package net.minecraft.crash;

import com.cheatbreaker.client.config.SettingsDetailLevel;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.concurrent.Callable;
import net.minecraft.client.renderer.entity.RenderGhast;
import recovered.unidentified.UnidentifiedClass1163;

public class CrashReport$6 implements Callable<String> {
   public UnidentifiedClass1163 field_0002;
   public SettingsDetailLevel field_0001;
   public InsecureTrustManagerFactory field_0003;
   public RenderGhast field_0000;

   public CrashReport$6(CrashReport var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      RuntimeMXBean var1 = ManagementFactory.getRuntimeMXBean();
      List var2 = var1.getInputArguments();
      int var3 = 0;
      StringBuilder var4 = new StringBuilder();

      for (String var6 : var2) {
         if (var6.startsWith("-X")) {
            if (var3++ > 0) {
               var4.append(" ");
            }

            var4.append(var6);
         }
      }

      return String.format("%d total; %s", var3, var4.toString());
   }
}
