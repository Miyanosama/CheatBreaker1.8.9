package net.minecraft.init;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockStone$EnumType;
import net.minecraft.client.main.llIlIIlIIllllIlIIllIIIlll;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie$1;
import net.minecraft.item.ItemFireball;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureVillagePieces$House1;

public class Bootstrap$13 extends BehaviorDefaultDispenseItem {
   public llIlIIlIIllllIlIIllIIIlll field_0002;
   public MapGenStructure field_0004;
   public BlockStone$EnumType field_0000;
   public StructureVillagePieces$House1 field_0005;
   public ItemFireball field_0003;
   public EntityZombie$1 field_0001;

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      EnumFacing var3 = BlockDispenser.getFacing(var1.getBlockMetadata());
      double var4 = var1.getX() + var3.getFrontOffsetX();
      double var6 = var1.getBlockPos().getY() + 0.2F;
      double var8 = var1.getZ() + var3.getFrontOffsetZ();
      Entity var10 = ItemMonsterPlacer.spawnCreature(var1.getWorld(), var2.getMetadata(), var4, var6, var8);
      if (var10 instanceof EntityLivingBase && var2.hasDisplayName()) {
         ((EntityLiving)var10).a(var2.getDisplayName());
      }

      var2.splitStack(1);
      return var2;
   }
}
