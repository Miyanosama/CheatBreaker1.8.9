package net.minecraft.entity.passive;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityMooshroom extends EntityCow {
   @Override
   public boolean interact(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      if (var2 != null && var2.getItem() == Items.bowl && this.l() >= 0) {
         if (var2.stackSize == 1) {
            var1.bi.setInventorySlotContents(var1.bi.currentItem, new ItemStack(Items.mushroom_stew));
            return true;
         }

         if (var1.bi.addItemStackToInventory(new ItemStack(Items.mushroom_stew)) && !var1.bA.isCreativeMode) {
            var1.bi.decrStackSize(var1.bi.currentItem, 1);
            return true;
         }
      }

      if (var2 != null && var2.getItem() == Items.shears && this.l() >= 0) {
         this.setDead();
         this.o.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.s, this.t + this.K / 2.0F, this.u, 0.0, 0.0, 0.0);
         if (!this.o.D) {
            EntityCow var3 = new EntityCow(this.o);
            var3.a_(this.s, this.t, this.u, this.y, this.z);
            var3.setHealth(this.getHealth());
            var3.aI = this.aI;
            if (this.u_()) {
               var3.a(this.aM());
            }

            this.o.spawnEntityInWorld(var3);

            for (int var4 = 0; var4 < 5; var4++) {
               this.o.spawnEntityInWorld(new EntityItem(this.o, this.s, this.t + this.K, this.u, new ItemStack(Blocks.red_mushroom)));
            }

            var2.damageItem(1, var1);
            this.playSound("mob.sheep.shear", 1.0F, 1.0F);
         }

         return true;
      } else {
         return super.interact(var1);
      }
   }

   public EntityMooshroom(World var1) {
      super(var1);
      this.setSize(0.9F, 1.3F);
      this.bn = Blocks.mycelium;
   }

   public EntityMooshroom createChild(EntityAgeable var1) {
      return new EntityMooshroom(this.o);
   }
}
