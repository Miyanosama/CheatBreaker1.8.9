package net.minecraft.enchantment;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.timeout.IdleStateHandler$WriterIdleTimeoutTask;
import net.minecraft.client.renderer.GlStateManager$TexGenState;
import net.minecraft.entity.EntityTracker;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.xml.DOMConfigurator$1;

public class EnchantmentFishingSpeed extends Enchantment {
   public NioEventLoopGroup field_0000;
   public GlStateManager$TexGenState field_0004;
   public DOMConfigurator$1 field_0002;
   public IdleStateHandler$WriterIdleTimeoutTask field_0003;
   public EntityTracker field_0001;

   @Override
   public int getMaxEnchantability(int var1) {
      return super.getMinEnchantability(var1) + 50;
   }

   @Override
   public int getMinEnchantability(int var1) {
      return 15 + (var1 - 1) * 9;
   }

   @Override
   public int getMaxLevel() {
      return 3;
   }

   public EnchantmentFishingSpeed(int var1, ResourceLocation var2, int var3, EnumEnchantmentType var4) {
      super(var1, var2, var3, var4);
      this.setName("fishingSpeed");
   }
}
