package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager$InboundHandlerTuplePacketListener;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$2;

public class BlockNewLog extends BlockLog {
   public NetworkManager$InboundHandlerTuplePacketListener field_0002;
   public static PropertyEnum<BlockPlanks$EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks$EnumType.class, new BlockNewLog$1());
   public StructureStrongholdPieces$2 field_0001;

   @Override
   public IBlockState getStateFromMeta(int var1) {
      IBlockState var2 = this.getDefaultState().withProperty(VARIANT, BlockPlanks$EnumType.byMetadata((var1 & 3) + 4));
      switch (var1 & 12) {
         case 0:
            var2 = var2.withProperty(a, BlockLog$EnumAxis.Y);
            break;
         case 4:
            var2 = var2.withProperty(a, BlockLog$EnumAxis.X);
            break;
         case 8:
            var2 = var2.withProperty(a, BlockLog$EnumAxis.Z);
            break;
         default:
            var2 = var2.withProperty(a, BlockLog$EnumAxis.NONE);
      }

      return var2;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(VARIANT).getMetadata() - 4;
      switch (BlockNewLog$2.field_180191_a[var1.getValue(a).ordinal()]) {
         case 1:
            var2 |= 4;
            break;
         case 2:
            var2 |= 8;
            break;
         case 3:
            var2 |= 12;
      }

      return var2;
   }

   public BlockNewLog() {
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockPlanks$EnumType.ACACIA).withProperty(a, BlockLog$EnumAxis.Y));
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata() - 4;
   }

   @Override
   public ItemStack createStackedBlock(IBlockState var1) {
      return new ItemStack(Item.getItemFromBlock(this), 1, var1.getValue(VARIANT).getMetadata() - 4);
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      BlockPlanks$EnumType var2 = var1.getValue(VARIANT);
      switch (BlockNewLog$2.field_180191_a[var1.getValue(a).ordinal()]) {
         case 1:
         case 2:
         case 3:
         default:
            switch (BlockNewLog$2.field_181093_a[var2.ordinal()]) {
               case 1:
               default:
                  return MapColor.stoneColor;
               case 2:
                  return BlockPlanks$EnumType.DARK_OAK.getMapColor();
            }
         case 4:
            return var2.getMapColor();
      }
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.ACACIA.getMetadata() - 4));
      var3.add(new ItemStack(var1, 1, BlockPlanks$EnumType.DARK_OAK.getMetadata() - 4));
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT, a);
   }
}
