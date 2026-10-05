package net.minecraft.block;

import com.cheatbreaker.client.util.friend.Status;
import io.netty.channel.ChannelFlushPromiseNotifier;
import io.netty.channel.group.DefaultChannelGroup$1;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager$DepthState;
import net.minecraft.command.CommandExecuteAt;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.EntityAIAvoidEntity$1;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager$3;
import net.minecraft.util.StatCollector;

public class BlockStone extends Block {
   public GlStateManager$DepthState field_0002;
   public static PropertyEnum<BlockStone$EnumType> VARIANT = PropertyEnum.create("variant", BlockStone$EnumType.class);
   public DefaultChannelGroup$1 field_0000;
   public EntityAIAvoidEntity$1 field_0001;
   public NetworkManager$3 field_0006;
   public ChannelFlushPromiseNotifier field_0004;
   public CommandExecuteAt field_0007;
   public Status field_0005;

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return var1.getValue(VARIANT).func_181072_c();
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return var1.getValue(VARIANT) == BlockStone$EnumType.STONE ? Item.getItemFromBlock(Blocks.cobblestone) : Item.getItemFromBlock(Blocks.stone);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockStone$EnumType.byMetadata(var1));
   }

   public BlockStone() {
      super(Material.rock);
      this.setDefaultState(this.M.getBaseState().withProperty(VARIANT, BlockStone$EnumType.STONE));
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockStone$EnumType var7 : BlockStone$EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, VARIANT);
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockStone$EnumType.STONE.getUnlocalizedName() + ".name");
   }
}
