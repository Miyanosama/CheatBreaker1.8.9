package com.cheatbreaker.client.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;

public class AsyncExecutor {
   public static ExecutorService recoveredField37 = Executors.newSingleThreadExecutor();

   public static void method_22037(Runnable var0) {
      recoveredField37.execute(var0);
   }

   public static void method_22036() {
      Minecraft.getMinecraft().gameSettings.saveOptions();
      recoveredField37.shutdown();
   }
}
