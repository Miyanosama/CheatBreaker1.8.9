package io.netty.channel;

import net.minecraft.block.BlockSilverfish;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.item.crafting.ShapedRecipes;
import org.apache.log4j.spi.NOPLoggerRepository;

public class AbstractChannelHandlerContext$16 implements Runnable {
   public EnchantmentDamage __junk5550860641182118791;
   public BlockSilverfish __junk1077172615493082977;
   public ShapedRecipes __junk2144238223895622342;
   public NOPLoggerRepository __junk5651581872117022097;

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$1500(this.val$next);
   }

   public AbstractChannelHandlerContext$16(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$next = var2;
      super();
   }
}
