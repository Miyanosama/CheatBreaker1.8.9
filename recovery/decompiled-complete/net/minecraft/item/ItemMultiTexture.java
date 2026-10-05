package net.minecraft.item;

import com.google.common.base.Function;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DefaultSocketChannelConfig;
import javax.vecmath.AxisAngle4f;
import net.minecraft.block.Block;
import net.minecraft.inventory.ContainerHorseInventory$1;

public class ItemMultiTexture extends ItemBlock {
   public ContainerHorseInventory$1 field_0004;
   public DefaultSocketChannelConfig field_0001;
   public SimpleChannelInboundHandler field_0003;
   public AxisAngle4f field_0000;
   public Block theBlock;
   public Function<ItemStack, String> nameFunction;

   @Override
   public int getMetadata(int var1) {
      return var1;
   }

   public ItemMultiTexture(Block var1, Block var2, String[] var3) {
      this(var1, var2, new ItemMultiTexture$1(var3));
   }

   public ItemMultiTexture(Block var1, Block var2, Function<ItemStack, String> var3) {
      super(var1);
      this.theBlock = var2;
      this.nameFunction = var3;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   @Override
   public String getUnlocalizedName(ItemStack var1) {
      return super.getUnlocalizedName() + "." + (String)this.nameFunction.apply(var1);
   }
}
