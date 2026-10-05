package net.minecraft.entity.ai;

import io.netty.buffer.PooledByteBufAllocator;
import io.netty.handler.codec.spdy.SpdySession$StreamComparator;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchValuesTask;
import net.minecraft.client.renderer.GlStateManager$BooleanState;
import net.minecraft.client.shader.ShaderDefault;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAILookAtTradePlayer extends EntityAIWatchClosest {
   public EntityVillager theMerchant;
   public ConcurrentHashMapV8$SearchValuesTask field_0005;
   public GlStateManager$BooleanState field_0002;
   public SpdySession$StreamComparator field_0004;
   public ShaderDefault field_0000;
   public PooledByteBufAllocator field_0001;

   public EntityAILookAtTradePlayer(EntityVillager var1) {
      super(var1, EntityPlayer.class, 8.0F);
      this.theMerchant = var1;
   }

   @Override
   public boolean shouldExecute() {
      if (this.theMerchant.isTrading()) {
         this.b = this.theMerchant.getCustomer();
         return true;
      } else {
         return false;
      }
   }
}
