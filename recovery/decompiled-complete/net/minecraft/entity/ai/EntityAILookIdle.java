package net.minecraft.entity.ai;

import com.cheatbreaker.client.ui.overlay.element.ConsoleElement;
import io.netty.buffer.ByteBufProcessor$9;
import io.netty.handler.codec.serialization.CompactObjectInputStream;
import net.minecraft.client.renderer.ThreadDownloadImageData$1;
import net.minecraft.client.renderer.chunk.ListedRenderChunk;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze$AIFireballAttack;
import recovered.unidentified.UnidentifiedClass3734;

public class EntityAILookIdle extends EntityAIBase {
   public ByteBufProcessor$9 field_0005;
   public EntityBlaze$AIFireballAttack field_0008;
   public int idleTime;
   public ConsoleElement field_0007;
   public double lookX;
   public ThreadDownloadImageData$1 field_0002;
   public double lookZ;
   public UnidentifiedClass3734 field_0006;
   public ListedRenderChunk field_0003;
   public CompactObjectInputStream field_0010;
   public EntityLiving idleEntity;

   public EntityAILookIdle(EntityLiving var1) {
      this.idleEntity = var1;
      this.setMutexBits(3);
   }

   @Override
   public boolean continueExecuting() {
      return this.idleTime >= 0;
   }

   @Override
   public void startExecuting() {
      double var1 = (Math.PI * 2) * this.idleEntity.getRNG().nextDouble();
      this.lookX = Math.cos(var1);
      this.lookZ = Math.sin(var1);
      this.idleTime = 20 + this.idleEntity.getRNG().nextInt(20);
   }

   @Override
   public boolean shouldExecute() {
      return this.idleEntity.getRNG().nextFloat() < 0.02F;
   }

   @Override
   public void updateTask() {
      this.idleTime--;
      this.idleEntity
         .getLookHelper()
         .setLookPosition(
            this.idleEntity.s + this.lookX,
            this.idleEntity.t + this.idleEntity.getEyeHeight(),
            this.idleEntity.u + this.lookZ,
            10.0F,
            this.idleEntity.getVerticalFaceSpeed()
         );
   }
}
