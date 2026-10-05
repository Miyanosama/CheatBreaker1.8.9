package net.minecraft.item;

import io.netty.channel.group.ChannelMatchers;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRailBase$EnumRailDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecart$EnumMinecartType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.java_websocket.framing.PongFrame;

public class ItemMinecart extends Item {
   public static IBehaviorDispenseItem dispenserMinecartBehavior = new ItemMinecart$1();
   public EntityMinecart$EnumMinecartType minecartType;
   public ChannelMatchers field_0003;
   public PongFrame field_0004;
   public ContainerBrewingStand field_0000;

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      IBlockState var9 = var3.getBlockState(var4);
      if (BlockRailBase.isRailBlock(var9)) {
         if (!var3.D) {
            BlockRailBase$EnumRailDirection var10 = var9.getBlock() instanceof BlockRailBase
               ? var9.getValue(((BlockRailBase)var9.getBlock()).getShapeProperty())
               : BlockRailBase$EnumRailDirection.NORTH_SOUTH;
            double var11 = 0.0;
            if (var10.isAscending()) {
               var11 = 0.5;
            }

            EntityMinecart var13 = EntityMinecart.getMinecart(var3, var4.getX() + 0.5, var4.getY() + 0.0625 + var11, var4.getZ() + 0.5, this.minecartType);
            if (var1.hasDisplayName()) {
               var13.a(var1.getDisplayName());
            }

            var3.spawnEntityInWorld(var13);
         }

         var1.stackSize--;
         return true;
      } else {
         return false;
      }
   }

   public ItemMinecart(EntityMinecart$EnumMinecartType var1) {
      this.h = 1;
      this.minecartType = var1;
      this.setCreativeTab(CreativeTabs.tabTransport);
      BlockDispenser.dispenseBehaviorRegistry.putObject(this, dispenserMinecartBehavior);
   }
}
