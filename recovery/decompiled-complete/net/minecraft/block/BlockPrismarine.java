package net.minecraft.block;

import com.cheatbreaker.client.network.CustomPayloadSender;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.apache.log4j.pattern.CachedDateFormat;

public class BlockPrismarine extends Block {
   public static int ROUGH_META = BlockPrismarine$EnumType.ROUGH.getMetadata();
   public BlockDeadBush field_0003;
   public static int BRICKS_META = BlockPrismarine$EnumType.BRICKS.getMetadata();
   public CustomPayloadSender field_0001;
   public static int DARK_META = BlockPrismarine$EnumType.DARK.getMetadata();
   public CachedDateFormat field_0004;
   public static PropertyEnum<BlockPrismarine$EnumType> VARIANT = PropertyEnum.create("variant", BlockPrismarine$EnumType.class);

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT) == BlockPrismarine$EnumType.ROUGH ? MapColor.cyanColor : MapColor.diamondColor;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockPrismarine$EnumType.ROUGH.getUnlocalizedName() + ".name");
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockPrismarine$EnumType.byMetadata(var1));
   }

   public BlockPrismarine() {
      super(Material.rock);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockPrismarine$EnumType.ROUGH));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      var3.add(new ItemStack(var1, 1, ROUGH_META));
      var3.add(new ItemStack(var1, 1, BRICKS_META));
      var3.add(new ItemStack(var1, 1, DARK_META));
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }
}
