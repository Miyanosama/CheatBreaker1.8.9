package net.minecraft.item;

import com.google.common.base.Function;
import javax.vecmath.Quat4d;
import net.minecraft.block.BlockPlanks$EnumType;
import net.minecraft.block.BlockPrismarine$EnumType;
import net.minecraft.client.particle.EffectRenderer$3;
import net.minecraft.world.gen.ChunkProviderSettings;

public class Item$8 implements Function<ItemStack, String> {
   public BlockPlanks$EnumType field_0001;
   public EffectRenderer$3 field_0003;
   public Quat4d field_0000;
   public ChunkProviderSettings field_0002;

   public String apply(ItemStack var1) {
      return BlockPrismarine$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
