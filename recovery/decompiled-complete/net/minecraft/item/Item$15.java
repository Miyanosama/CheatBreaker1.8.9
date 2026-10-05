package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.BlockPlanks$EnumType;
import net.minecraft.entity.passive.EntityHorse$GroupData;
import net.minecraft.entity.passive.EntitySheep$1;
import net.minecraft.network.NettyEncryptingEncoder;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Crossing;
import net.optifine.gui.GuiScreenCapeOF;

public class Item$15 implements Function<ItemStack, String> {
   public EntityHorse$GroupData field_0002;
   public NettyEncryptingEncoder field_0004;
   public StructureNetherBridgePieces$Crossing field_0001;
   public GuiScreenCapeOF field_0003;
   public EntitySheep$1 field_0000;

   public String apply(ItemStack var1) {
      return BlockPlanks$EnumType.byMetadata(var1.getMetadata() + 4).getUnlocalizedName();
   }
}
