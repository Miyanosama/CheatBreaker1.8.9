package net.minecraft.creativetab;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.server.network.NetHandlerLoginServer;
import net.optifine.player.PlayerConfigurationReceiver;

public class CreativeTabs$6 extends CreativeTabs {
   public PlayerConfigurationReceiver field_0001;
   public NetHandlerLoginServer field_0000;

   @Override
   public Item getTabIconItem() {
      return Items.redstone;
   }

   public CreativeTabs$6(int var1, String var2) {
      super(var1, var2);
   }
}
