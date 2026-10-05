package net.minecraft.entity.passive;

import io.netty.channel.FailedChannelFuture;
import net.minecraft.block.BlockColored;
import net.minecraft.client.particle.EntityCrit2FX;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.MathHelper;
import org.slf4j.event.EventRecodingLogger;

public class EntitySquid$AIMoveRandom extends EntityAIBase {
   public BlockColored field_0003;
   public EntityLargeFireball field_0005;
   public EntitySquid squid;
   public EntityCrit2FX field_0004;
   public FailedChannelFuture field_0000;
   public EventRecodingLogger field_0001;

   @Override
   public void updateTask() {
      int var1 = this.squid.bh();
      if (var1 > 100) {
         this.squid.func_175568_b(0.0F, 0.0F, 0.0F);
      } else if (this.squid.getRNG().nextInt(50) == 0 || !EntitySquid.access$000(this.squid) || !this.squid.func_175567_n()) {
         float var2 = this.squid.getRNG().nextFloat() * (float) Math.PI * 2.0F;
         float var3 = MathHelper.cos(var2) * 0.2F;
         float var4 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
         float var5 = MathHelper.sin(var2) * 0.2F;
         this.squid.func_175568_b(var3, var4, var5);
      }
   }

   public EntitySquid$AIMoveRandom(EntitySquid var1) {
      this.squid = var1;
   }

   @Override
   public boolean shouldExecute() {
      return true;
   }
}
