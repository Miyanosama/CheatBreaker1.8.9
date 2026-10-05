package net.minecraft.init;

import java.util.Random;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeFireworks;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.optifine.shaders.uniform.ShaderUniforms;

public class Bootstrap$15 extends BehaviorDefaultDispenseItem {
   public EntityLivingBase field_0001;
   public S20PacketEntityProperties field_0002;
   public ShaderUniforms field_0000;
   public RecipeFireworks field_0003;

   @Override
   public void playDispenseSound(IBlockSource var1) {
      var1.getWorld().b(1009, var1.getBlockPos(), 0);
   }

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      EnumFacing var3 = BlockDispenser.getFacing(var1.getBlockMetadata());
      IPosition var4 = BlockDispenser.getDispensePosition(var1);
      double var5 = var4.getX() + var3.getFrontOffsetX() * 0.3F;
      double var7 = var4.getY() + var3.getFrontOffsetY() * 0.3F;
      double var9 = var4.getZ() + var3.getFrontOffsetZ() * 0.3F;
      World var11 = var1.getWorld();
      Random var12 = var11.s;
      double var13 = var12.nextGaussian() * 0.05 + var3.getFrontOffsetX();
      double var15 = var12.nextGaussian() * 0.05 + var3.getFrontOffsetY();
      double var17 = var12.nextGaussian() * 0.05 + var3.getFrontOffsetZ();
      var11.spawnEntityInWorld(new EntitySmallFireball(var11, var5, var7, var9, var13, var15, var17));
      var2.splitStack(1);
      return var2;
   }
}
