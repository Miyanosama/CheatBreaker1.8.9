package net.minecraft.client.particle;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.java_websocket.server.CustomSSLWebSocketServerFactory;
import recovered.unidentified.UnidentifiedClass0433;

public class EntityBubbleFX extends EntityFX {
   public UnidentifiedClass0433 field_0000;
   public CustomSSLWebSocketServerFactory field_0001;

   public EntityBubbleFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.ar = 1.0F;
      this.as = 1.0F;
      this.at = 1.0F;
      this.k(32);
      this.setSize(0.02F, 0.02F);
      this.h = this.h * (this.V.nextFloat() * 0.6F + 0.2F);
      this.v = var8 * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
      this.w = var10 * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
      this.x = var12 * 0.2F + (Math.random() * 2.0 - 1.0) * 0.02F;
      this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2));
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.w += 0.002;
      this.d(this.v, this.w, this.x);
      this.v *= 0.85F;
      this.w *= 0.85F;
      this.x *= 0.85F;
      if (this.o.getBlockState(new BlockPos(this)).getBlock().getMaterial() != Material.water) {
         this.setDead();
      }

      if (this.g-- <= 0) {
         this.setDead();
      }
   }
}
