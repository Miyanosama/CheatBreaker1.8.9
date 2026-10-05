package net.minecraft.item;

import io.netty.handler.codec.socks.SocksMessageEncoder;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$BulkTask;
import javax.vecmath.Point3f;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab$EnumBlockHalf;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiBeacon$ConfirmButton;
import net.minecraft.enchantment.EnchantmentLootBonus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetworkManager$InboundHandlerTuplePacketListener;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSlab extends ItemBlock {
   public NetworkManager$InboundHandlerTuplePacketListener field_0000;
   public EnchantmentLootBonus field_0001;
   public BlockSlab doubleSlab;
   public ConcurrentHashMapV8$BulkTask field_0006;
   public GuiBeacon$ConfirmButton field_0004;
   public Point3f field_0005;
   public BlockSlab singleSlab;
   public SocksMessageEncoder field_0007;

   @Override
   public boolean canPlaceBlockOnSide(World var1, BlockPos var2, EnumFacing var3, EntityPlayer var4, ItemStack var5) {
      IProperty var7 = this.singleSlab.getVariantProperty();
      Object var8 = this.singleSlab.getVariant(var5);
      IBlockState var9 = var1.getBlockState(var2);
      if (var9.getBlock() == this.singleSlab) {
         boolean var10 = var9.getValue(BlockSlab.a) == BlockSlab$EnumBlockHalf.TOP;
         if ((var3 == EnumFacing.UP && !var10 || var3 == EnumFacing.DOWN && var10) && var8 == var9.getValue(var7)) {
            return true;
         }
      }

      BlockPos var11 = var2.a(var3);
      IBlockState var12 = var1.getBlockState(var11);
      return var12.getBlock() == this.singleSlab && var8 == var12.getValue(var7) ? true : super.canPlaceBlockOnSide(var1, var2, var3, var4, var5);
   }

   @Override
   public String getUnlocalizedName(ItemStack var1) {
      return this.singleSlab.getUnlocalizedName(var1.getMetadata());
   }

   @Override
   public int getMetadata(int var1) {
      return var1;
   }

   public boolean tryPlace(ItemStack var1, World var2, BlockPos var3, Object var4) {
      IBlockState var5 = var2.getBlockState(var3);
      if (var5.getBlock() == this.singleSlab) {
         Comparable var6 = var5.getValue((IProperty<Comparable>)this.singleSlab.getVariantProperty());
         if (var6 == var4) {
            IBlockState var7 = this.doubleSlab.getDefaultState().withProperty(this.singleSlab.getVariantProperty(), var6);
            if (var2.checkNoEntityCollision(this.doubleSlab.getCollisionBoundingBox(var2, var3, var7)) && var2.a(var3, var7, 3)) {
               var2.playSoundEffect(
                  var3.getX() + 0.5F,
                  var3.getY() + 0.5F,
                  var3.getZ() + 0.5F,
                  this.doubleSlab.stepSound.getPlaceSound(),
                  (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F,
                  this.doubleSlab.stepSound.getFrequency() * 0.8F
               );
               var1.stackSize--;
            }

            return true;
         }
      }

      return false;
   }

   public ItemSlab(Block var1, BlockSlab var2, BlockSlab var3) {
      super(var1);
      this.singleSlab = var2;
      this.doubleSlab = var3;
      this.setMaxDamage(0);
      this.setHasSubtypes(true);
   }

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var1.stackSize == 0) {
         return false;
      } else if (!var2.canPlayerEdit(var4.a(var5), var5, var1)) {
         return false;
      } else {
         Object var9 = this.singleSlab.getVariant(var1);
         IBlockState var10 = var3.getBlockState(var4);
         if (var10.getBlock() == this.singleSlab) {
            IProperty var11 = this.singleSlab.getVariantProperty();
            Comparable var12 = var10.getValue(var11);
            BlockSlab$EnumBlockHalf var13 = var10.getValue(BlockSlab.a);
            if ((var5 == EnumFacing.UP && var13 == BlockSlab$EnumBlockHalf.BOTTOM || var5 == EnumFacing.DOWN && var13 == BlockSlab$EnumBlockHalf.TOP)
               && var12 == var9) {
               IBlockState var14 = this.doubleSlab.getDefaultState().withProperty(var11, var12);
               if (var3.checkNoEntityCollision(this.doubleSlab.getCollisionBoundingBox(var3, var4, var14)) && var3.a(var4, var14, 3)) {
                  var3.playSoundEffect(
                     var4.getX() + 0.5F,
                     var4.getY() + 0.5F,
                     var4.getZ() + 0.5F,
                     this.doubleSlab.stepSound.getPlaceSound(),
                     (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F,
                     this.doubleSlab.stepSound.getFrequency() * 0.8F
                  );
                  var1.stackSize--;
               }

               return true;
            }
         }

         return this.tryPlace(var1, var3, var4.a(var5), var9) ? true : super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8);
      }
   }
}
