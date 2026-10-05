package net.minecraft.item;

import io.netty.util.internal.logging.JdkLoggerFactory;
import net.minecraft.client.renderer.WorldRenderer$State;
import net.minecraft.network.NetworkSystem;

public class ItemBook extends Item {
   public NetworkSystem field_0000;
   public WorldRenderer$State field_0001;
   public JdkLoggerFactory field_0002;

   @Override
   public int getItemEnchantability() {
      return 1;
   }

   @Override
   public boolean isItemTool(ItemStack var1) {
      return var1.stackSize == 1;
   }
}
