package net.minecraft.entity.ai;

import io.netty.buffer.AbstractReferenceCountedByteBuf;
import io.netty.handler.codec.http.multipart.DiskAttribute;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.EnumFacing$1;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Corridor;

public class EntityAIOwnerHurtTarget extends EntityAITarget {
   public StructureNetherBridgePieces$Corridor field_0003;
   public PongWebSocketFrame field_0006;
   public AbstractReferenceCountedByteBuf field_0002;
   public EntityTameable theEntityTameable;
   public int field_142050_e;
   public DiskAttribute field_0001;
   public EntityLivingBase theTarget;
   public EnumFacing$1 field_0004;

   @Override
   public boolean shouldExecute() {
      if (!this.theEntityTameable.isTamed()) {
         return false;
      } else {
         EntityLivingBase var1 = this.theEntityTameable.getOwner();
         if (var1 == null) {
            return false;
         } else {
            this.theTarget = var1.getLastAttacker();
            int var2 = var1.getLastAttackerTime();
            return var2 != this.field_142050_e && this.a(this.theTarget, false) && this.theEntityTameable.shouldAttackEntity(this.theTarget, var1);
         }
      }
   }

   @Override
   public void startExecuting() {
      this.e.setAttackTarget(this.theTarget);
      EntityLivingBase var1 = this.theEntityTameable.getOwner();
      if (var1 != null) {
         this.field_142050_e = var1.getLastAttackerTime();
      }

      super.startExecuting();
   }

   public EntityAIOwnerHurtTarget(EntityTameable var1) {
      super(var1, false);
      this.theEntityTameable = var1;
      this.setMutexBits(1);
   }
}
