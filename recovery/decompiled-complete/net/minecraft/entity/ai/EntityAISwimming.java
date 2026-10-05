package net.minecraft.entity.ai;

import com.cheatbreaker.client.nethandler.server.PacketWorldBorderUpdate;
import io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder$1;
import net.minecraft.entity.EntityLiving;
import net.minecraft.network.play.client.C0BPacketEntityAction$Action;
import net.minecraft.pathfinding.PathNavigateGround;
import recovered.unidentified.UnidentifiedClass1464;

public class EntityAISwimming extends EntityAIBase {
   public PacketWorldBorderUpdate field_0002;
   public EntityLiving theEntity;
   public SpdyHeaderBlockRawDecoder$1 field_0001;
   public C0BPacketEntityAction$Action field_0003;
   public UnidentifiedClass1464 field_0000;

   @Override
   public void updateTask() {
      if (this.theEntity.getRNG().nextFloat() < 0.8F) {
         this.theEntity.r().setJumping();
      }
   }

   public EntityAISwimming(EntityLiving var1) {
      this.theEntity = var1;
      this.setMutexBits(4);
      ((PathNavigateGround)var1.s()).setCanSwim(true);
   }

   @Override
   public boolean shouldExecute() {
      return this.theEntity.V() || this.theEntity.ab();
   }
}
