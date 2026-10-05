package net.minecraft.item;

import io.netty.util.internal.MpscLinkedQueueHeadRef;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.FlatLayerInfo;
import net.optifine.Log;

public class ItemBlock extends Item {
   public NBTTagIntArray field_0004;
   public Log field_0002;
   public FlatLayerInfo field_0001;
   public MpscLinkedQueueHeadRef field_0003;
   public Block a;

   public Block getBlock() {
      return this.a;
   }

   public static boolean setTileEntityNBT(World var0, EntityPlayer var1, BlockPos var2, ItemStack var3) {
      MinecraftServer var4 = MinecraftServer.getServer();
      if (var4 == null) {
         return false;
      } else {
         if (var3.hasTagCompound() && var3.getTagCompound().hasKey("BlockEntityTag", 10)) {
            TileEntity var5 = var0.getTileEntity(var2);
            if (var5 != null) {
               if (!var0.D && var5.func_183000_F() && !var4.getConfigurationManager().canSendCommands(var1.getGameProfile())) {
                  return false;
               }

               NBTTagCompound var6 = new NBTTagCompound();
               NBTTagCompound var7 = (NBTTagCompound)var6.copy();
               var5.writeToNBT(var6);
               NBTTagCompound var8 = (NBTTagCompound)var3.getTagCompound().getTag("BlockEntityTag");
               var6.merge(var8);
               var6.setInteger("x", var2.getX());
               var6.setInteger("y", var2.getY());
               var6.setInteger("z", var2.getZ());
               if (!var6.equals(var7)) {
                  var5.readFromNBT(var6);
                  var5.markDirty();
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      IBlockState var9 = var3.getBlockState(var4);
      Block var10 = var9.getBlock();
      if (!var10.isReplaceable(var3, var4)) {
         var4 = var4.a(var5);
      }

      if (var1.stackSize == 0) {
         return false;
      } else if (!var2.canPlayerEdit(var4, var5, var1)) {
         return false;
      } else if (var3.canBlockBePlaced(this.a, var4, false, var5, (Entity)null, var1)) {
         int var11 = this.getMetadata(var1.getMetadata());
         IBlockState var12 = this.a.onBlockPlaced(var3, var4, var5, var6, var7, var8, var11, var2);
         if (var3.a(var4, var12, 3)) {
            var12 = var3.getBlockState(var4);
            if (var12.getBlock() == this.a) {
               setTileEntityNBT(var3, var2, var4, var1);
               this.a.onBlockPlacedBy(var3, var4, var12, var2, var1);
            }

            var3.playSoundEffect(
               var4.getX() + 0.5F,
               var4.getY() + 0.5F,
               var4.getZ() + 0.5F,
               this.a.stepSound.getPlaceSound(),
               (this.a.stepSound.getVolume() + 1.0F) / 2.0F,
               this.a.stepSound.getFrequency() * 0.8F
            );
            var1.stackSize--;
         }

         return true;
      } else {
         return false;
      }
   }

   public ItemBlock setUnlocalizedName(String var1) {
      super.setUnlocalizedName(var1);
      return this;
   }

   @Override
   public void getSubItems(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      this.a.getSubBlocks(var1, var2, var3);
   }

   public ItemBlock(Block var1) {
      this.a = var1;
   }

   public boolean canPlaceBlockOnSide(World var1, BlockPos var2, EnumFacing var3, EntityPlayer var4, ItemStack var5) {
      Block var6 = var1.getBlockState(var2).getBlock();
      if (var6 == Blocks.snow_layer) {
         var3 = EnumFacing.UP;
      } else if (!var6.isReplaceable(var1, var2)) {
         var2 = var2.a(var3);
      }

      return var1.canBlockBePlaced(this.a, var2, false, var3, (Entity)null, var5);
   }

   @Override
   public String getUnlocalizedName() {
      return this.a.getUnlocalizedName();
   }

   @Override
   public CreativeTabs getCreativeTab() {
      return this.a.getCreativeTabToDisplayOn();
   }

   @Override
   public String getUnlocalizedName(ItemStack var1) {
      return this.a.getUnlocalizedName();
   }
}
