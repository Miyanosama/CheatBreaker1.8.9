package io.netty.util.concurrent;

import recovered.unidentified.UnidentifiedClass1099;
import recovered.unidentified.UnidentifiedClass1182;

public class DefaultThreadFactory$DefaultRunnableDecorator implements Runnable {
   public Runnable r;
   public UnidentifiedClass1099 __junk81703487247876572;
   public UnidentifiedClass1182 __junk3708454833941751433;

   @Override
   public void run() {
      try {
         this.r.run();
      } finally {
         FastThreadLocal.removeAll();
      }
   }

   public DefaultThreadFactory$DefaultRunnableDecorator(Runnable var1) {
      this.r = var1;
   }
}
