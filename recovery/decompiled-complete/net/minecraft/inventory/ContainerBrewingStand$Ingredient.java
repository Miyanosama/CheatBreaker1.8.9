package net.minecraft.inventory;

import com.cheatbreaker.client.module.type.MotionBlurModule;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.item.ItemStack;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$3;

public class ContainerBrewingStand$Ingredient extends Slot {
   public EntityAIFindEntityNearest field_0001;
   public MotionBlurModule field_0000;
   public CategoryNodeEditor$3 field_0002;

   public ContainerBrewingStand$Ingredient(ContainerBrewingStand var1, IInventory var2, int var3, int var4, int var5) {
      this.field_75226_a = var1;
      super(var2, var3, var4, var5);
   }

   @Override
   public boolean isItemValid(ItemStack var1) {
      return var1 != null ? var1.getItem().isPotionIngredient(var1) : false;
   }

   @Override
   public int getSlotStackLimit() {
      return 64;
   }
}
