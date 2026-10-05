package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderIronGolem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipesMapCloning;
import net.minecraft.realms.RealmsScreen;

public class BlockPlanks extends Block {
   public static PropertyEnum<BlockPlanks$EnumType> VARIANT = PropertyEnum.create("variant", BlockPlanks$EnumType.class);
   public RenderIronGolem field_0003;
   public RealmsScreen field_0000;
   public RecipesMapCloning field_0001;

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockPlanks$EnumType.byMetadata(var1));
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   public BlockPlanks() {
      super(Material.wood);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockPlanks$EnumType.OAK));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockPlanks$EnumType var7 : BlockPlanks$EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT).getMapColor();
   }
}
