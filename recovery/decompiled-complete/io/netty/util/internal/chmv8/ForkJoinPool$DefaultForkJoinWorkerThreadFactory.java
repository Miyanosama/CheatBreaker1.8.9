package io.netty.util.internal.chmv8;

import io.netty.handler.ssl.JdkSslContext;
import net.optifine.entity.model.ModelAdapterSpider;

public class ForkJoinPool$DefaultForkJoinWorkerThreadFactory implements ForkJoinPool$ForkJoinWorkerThreadFactory {
   public ModelAdapterSpider __junk1924408272882500189;
   public JdkSslContext __junk773030791954038067;

   @Override
   public ForkJoinWorkerThread newThread(ForkJoinPool var1) {
      return new ForkJoinWorkerThread(var1);
   }
}
