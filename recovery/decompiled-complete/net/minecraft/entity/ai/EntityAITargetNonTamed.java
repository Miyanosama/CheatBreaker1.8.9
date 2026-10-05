package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import net.minecraft.client.network.NetHandlerHandshakeMemory;
import net.minecraft.client.particle.MobAppearance;
import net.minecraft.client.renderer.block.model.BlockPart$1;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;

public class EntityAITargetNonTamed<T extends EntityLivingBase> extends EntityAINearestAttackableTarget {
   public EntityTameable theTameable;
   public RenderWolf field_0001;
   public NetHandlerHandshakeMemory field_0003;
   public BlockPart$1 field_0000;
   public S19PacketEntityHeadLook field_0005;
   public MobAppearance field_0004;

   public EntityAITargetNonTamed(EntityTameable var1, Class<T> var2, boolean var3, Predicate<? super T> var4) {
      super(var1, var2, 10, var3, false, var4);
      this.theTameable = var1;
   }

   @Override
   public boolean shouldExecute() {
      return !this.theTameable.isTamed() && super.shouldExecute();
   }
}
