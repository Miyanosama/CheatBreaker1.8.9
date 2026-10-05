package net.minecraft.creativetab;

import io.netty.handler.ssl.util.BouncyCastleSelfSignedCertGenerator;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.server.network.NetHandlerLoginServer$1;
import net.minecraft.world.storage.ThreadedFileIOBase;

public class CreativeTabs$2 extends CreativeTabs {
   public ThreadedFileIOBase field_0001;
   public NetHandlerLoginServer$1 field_0000;
   public BouncyCastleSelfSignedCertGenerator field_0002;

   public CreativeTabs$2(int var1, String var2) {
      super(var1, var2);
   }

   @Override
   public Item getTabIconItem() {
      return Items.potionitem;
   }
}
