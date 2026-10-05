package org.java_websocket.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.inventory.ContainerHopper;
import recovered.unidentified.UnidentifiedClass0000;

public class NamedThreadFactory implements ThreadFactory {
   public ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
   public ContainerHopper field_0004;
   public UnidentifiedClass0000 field_0001;
   public AtomicInteger threadNumber = new AtomicInteger(1);
   public String threadPrefix;

   @Override
   public Thread newThread(Runnable var1) {
      Thread var2 = this.defaultThreadFactory.newThread(var1);
      var2.setName(this.threadPrefix + "-" + this.threadNumber);
      return var2;
   }

   public NamedThreadFactory(String var1) {
      this.threadPrefix = var1;
   }
}
