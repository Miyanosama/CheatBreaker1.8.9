package net.minecraft.client.particle;

import io.netty.handler.timeout.TimeoutException;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase$1;
import net.minecraft.world.World;

public class EntityCritFX extends EntitySmokeFX {
   public TimeoutException field_0000;
   public LayerArmorBase$1 field_0001;

   public EntityCritFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12, 2.5F);
   }
}
