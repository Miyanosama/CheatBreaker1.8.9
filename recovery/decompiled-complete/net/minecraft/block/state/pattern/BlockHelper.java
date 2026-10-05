package net.minecraft.block.state.pattern;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelChest;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Crossing2;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeRenderer;

public class BlockHelper implements Predicate<IBlockState> {
   public ModelChest field_0003;
   public StructureNetherBridgePieces$Crossing2 field_0005;
   public NBTTagString field_0002;
   public CategoryNodeRenderer field_0004;
   public EntitySmallFireball field_0000;
   public Block block;

   public boolean apply(IBlockState var1) {
      return var1 != null && var1.getBlock() == this.block;
   }

   public BlockHelper(Block var1) {
      this.block = var1;
   }

   public static BlockHelper forBlock(Block var0) {
      return new BlockHelper(var0);
   }
}
