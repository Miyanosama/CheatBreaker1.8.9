package net.minecraft.block;

import io.netty.handler.codec.marshalling.ChannelBufferByteOutput;
import java.util.List;
import java.util.Random;
import javazoom.jl.converter.WaveFile$WaveFileSample;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.stream.GuiStreamUnavailable$Reason;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor$ArmorMaterial;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCauldron extends Block {
   public static PropertyInteger LEVEL = PropertyInteger.create("level", 0, 3);
   public ChannelBufferByteOutput field_0003;
   public GuiStreamUnavailable$Reason field_0000;
   public WaveFile$WaveFileSample field_0001;

   public BlockCauldron() {
      super(Material.iron, MapColor.stoneColor);
      this.setDefaultState(this.M.getBaseState().withProperty(LEVEL, 0));
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(LEVEL);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(LEVEL, var1);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, LEVEL);
   }

   public void setWaterLevel(World var1, BlockPos var2, IBlockState var3, int var4) {
      var1.a(var2, var3.withProperty(LEVEL, MathHelper.clamp_int(var4, 0, 3)), 2);
      var1.updateComparatorOutputLevel(var2, this);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Items.cauldron;
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.cauldron;
   }

   @Override
   public void addCollisionBoxesToList(World var1, BlockPos var2, IBlockState var3, AxisAlignedBB var4, List<AxisAlignedBB> var5, Entity var6) {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      float var7 = 0.125F;
      this.a(0.0F, 0.0F, 0.0F, var7, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var7);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      this.a(1.0F - var7, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      this.a(0.0F, 0.0F, 1.0F - var7, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      this.setBlockBoundsForItemRender();
   }

   @Override
   public void fillWithRain(World var1, BlockPos var2) {
      if (var1.s.nextInt(20) == 1) {
         IBlockState var3 = var1.getBlockState(var2);
         if (var3.getValue(LEVEL) < 3) {
            var1.a(var2, var3.cycleProperty(LEVEL), 2);
         }
      }
   }

   @Override
   public int getComparatorInputOverride(World var1, BlockPos var2) {
      return var1.getBlockState(var2).getValue(LEVEL);
   }

   @Override
   public void onEntityCollidedWithBlock(World var1, BlockPos var2, IBlockState var3, Entity var4) {
      int var5 = var3.getValue(LEVEL);
      float var6 = var2.getY() + (6.0F + 3 * var5) / 16.0F;
      if (!var1.D && var4.isBurning() && var5 > 0 && var4.getEntityBoundingBox().b <= var6) {
         var4.extinguish();
         this.setWaterLevel(var1, var2, var3, var5 - 1);
      }
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var1.D) {
         return true;
      } else {
         ItemStack var9 = var4.bi.getCurrentItem();
         if (var9 == null) {
            return true;
         } else {
            int var10 = var3.getValue(LEVEL);
            Item var11 = var9.getItem();
            if (var11 == Items.water_bucket) {
               if (var10 < 3) {
                  if (!var4.bA.isCreativeMode) {
                     var4.bi.setInventorySlotContents(var4.bi.currentItem, new ItemStack(Items.bucket));
                  }

                  var4.triggerAchievement(StatList.field_181725_I);
                  this.setWaterLevel(var1, var2, var3, 3);
               }

               return true;
            } else if (var11 == Items.glass_bottle) {
               if (var10 > 0) {
                  if (!var4.bA.isCreativeMode) {
                     ItemStack var14 = new ItemStack(Items.potionitem, 1, 0);
                     if (!var4.bi.addItemStackToInventory(var14)) {
                        var1.spawnEntityInWorld(new EntityItem(var1, var2.getX() + 0.5, var2.getY() + 1.5, var2.getZ() + 0.5, var14));
                     } else if (var4 instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)var4).sendContainerToPlayer(var4.bj);
                     }

                     var4.triggerAchievement(StatList.field_181726_J);
                     var9.stackSize--;
                     if (var9.stackSize <= 0) {
                        var4.bi.setInventorySlotContents(var4.bi.currentItem, (ItemStack)null);
                     }
                  }

                  this.setWaterLevel(var1, var2, var3, var10 - 1);
               }

               return true;
            } else {
               if (var10 > 0 && var11 instanceof ItemArmor) {
                  ItemArmor var12 = (ItemArmor)var11;
                  if (var12.getArmorMaterial() == ItemArmor$ArmorMaterial.LEATHER && var12.hasColor(var9)) {
                     var12.removeColor(var9);
                     this.setWaterLevel(var1, var2, var3, var10 - 1);
                     var4.triggerAchievement(StatList.field_181727_K);
                     return true;
                  }
               }

               if (var10 > 0 && var11 instanceof ItemBanner && TileEntityBanner.getPatterns(var9) > 0) {
                  ItemStack var13 = var9.copy();
                  var13.stackSize = 1;
                  TileEntityBanner.removeBannerData(var13);
                  if (var9.stackSize <= 1 && !var4.bA.isCreativeMode) {
                     var4.bi.setInventorySlotContents(var4.bi.currentItem, var13);
                  } else {
                     if (!var4.bi.addItemStackToInventory(var13)) {
                        var1.spawnEntityInWorld(new EntityItem(var1, var2.getX() + 0.5, var2.getY() + 1.5, var2.getZ() + 0.5, var13));
                     } else if (var4 instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)var4).sendContainerToPlayer(var4.bj);
                     }

                     var4.triggerAchievement(StatList.field_181728_L);
                     if (!var4.bA.isCreativeMode) {
                        var9.stackSize--;
                     }
                  }

                  if (!var4.bA.isCreativeMode) {
                     this.setWaterLevel(var1, var2, var3, var10 - 1);
                  }

                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   @Override
   public void setBlockBoundsForItemRender() {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }
}
