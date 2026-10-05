package net.minecraft.inventory;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;

public class SlotFurnaceOutput extends Slot {
   public int field_75228_b;
   public EntityPlayer thePlayer;

   @Override
   public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
      this.onCrafting(var2);
      super.onPickupFromSlot(var1, var2);
   }

   @Override
   public void onCrafting(ItemStack var1, int var2) {
      this.field_75228_b += var2;
      this.onCrafting(var1);
   }

   @Override
   public ItemStack decrStackSize(int var1) {
      if (this.getHasStack()) {
         this.field_75228_b = this.field_75228_b + Math.min(var1, this.getStack().stackSize);
      }

      return super.decrStackSize(var1);
   }

   @Override
   public boolean isItemValid(ItemStack var1) {
      return false;
   }

   @Override
   public void onCrafting(ItemStack var1) {
      var1.onCrafting(this.thePlayer.o, this.thePlayer, this.field_75228_b);
      if (!this.thePlayer.o.D) {
         int var2 = this.field_75228_b;
         float var3 = FurnaceRecipes.instance().getSmeltingExperience(var1);
         if (var3 == 0.0F) {
            var2 = 0;
         } else if (var3 < 1.0F) {
            int var4 = MathHelper.floor_float(var2 * var3);
            if (var4 < MathHelper.ceiling_float_int(var2 * var3) && Math.random() < var2 * var3 - var4) {
               var4++;
            }

            var2 = var4;
         }

         while (var2 > 0) {
            int var5 = EntityXPOrb.getXPSplit(var2);
            var2 -= var5;
            this.thePlayer.o.spawnEntityInWorld(new EntityXPOrb(this.thePlayer.o, this.thePlayer.s, this.thePlayer.t + 0.5, this.thePlayer.u + 0.5, var5));
         }
      }

      this.field_75228_b = 0;
      if (var1.getItem() == Items.iron_ingot) {
         this.thePlayer.triggerAchievement(AchievementList.acquireIron);
      }

      if (var1.getItem() == Items.cooked_fish) {
         this.thePlayer.triggerAchievement(AchievementList.cookFish);
      }
   }

   public SlotFurnaceOutput(EntityPlayer var1, IInventory var2, int var3, int var4, int var5) {
      super(var2, var3, var4, var5);
      this.thePlayer = var1;
   }
}
