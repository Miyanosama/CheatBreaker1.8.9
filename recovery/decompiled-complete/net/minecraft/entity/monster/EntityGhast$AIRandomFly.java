package net.minecraft.entity.monster;

import io.netty.handler.codec.rtsp.RtspResponseEncoder;
import java.util.Random;
import net.minecraft.client.settings.GameSettings$1;
import net.minecraft.client.stream.BroadcastController$3;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import org.slf4j.helpers.BasicMarker;

public class EntityGhast$AIRandomFly extends EntityAIBase {
   public BroadcastController$3 field_0002;
   public GameSettings$1 field_0004;
   public EntityGhast parentEntity;
   public BasicMarker field_0003;
   public RtspResponseEncoder field_0000;

   public EntityGhast$AIRandomFly(EntityGhast var1) {
      this.parentEntity = var1;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      Random var1 = this.parentEntity.getRNG();
      double var2 = this.parentEntity.s + (var1.nextFloat() * 2.0F - 1.0F) * 16.0F;
      double var4 = this.parentEntity.t + (var1.nextFloat() * 2.0F - 1.0F) * 16.0F;
      double var6 = this.parentEntity.u + (var1.nextFloat() * 2.0F - 1.0F) * 16.0F;
      this.parentEntity.q().setMoveTo(var2, var4, var6, 1.0);
   }

   @Override
   public boolean shouldExecute() {
      EntityMoveHelper var1 = this.parentEntity.q();
      if (!var1.isUpdating()) {
         return true;
      } else {
         double var2 = var1.getX() - this.parentEntity.s;
         double var4 = var1.getY() - this.parentEntity.t;
         double var6 = var1.getZ() - this.parentEntity.u;
         double var8 = var2 * var2 + var4 * var4 + var6 * var6;
         return var8 < 1.0 || var8 > 3600.0;
      }
   }

   @Override
   public boolean continueExecuting() {
      return false;
   }
}
