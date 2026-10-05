package net.minecraft.client;

import net.minecraft.client.particle.EntityAuraFX$Factory;

public class Minecraft$11 extends Thread {
   public EntityAuraFX$Factory field_0000;

   public Minecraft$11(Minecraft var1, String var2) {
      this.field_74536_a = var1;
      super(var2);
   }

   @Override
   public void run() {
      while (this.field_74536_a.running) {
         try {
            Thread.sleep(2445658121129951231L & -2445658118982467585L);
         } catch (InterruptedException var2) {
         }
      }
   }
}
