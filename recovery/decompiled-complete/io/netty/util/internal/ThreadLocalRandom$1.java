package io.netty.util.internal;

import java.security.SecureRandom;
import java.util.concurrent.BlockingQueue;
import net.minecraft.potion.PotionAttackDamage;

public class ThreadLocalRandom$1 extends Thread {
   public PotionAttackDamage __junk4553726323811124320;

   public ThreadLocalRandom$1(String var1, BlockingQueue var2) {
      this.val$queue = var2;
      super(var1);
   }

   @Override
   public void run() {
      SecureRandom var1 = new SecureRandom();
      this.val$queue.add(var1.generateSeed(8));
   }
}
