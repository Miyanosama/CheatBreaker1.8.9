package net.minecraft.entity;

import net.minecraft.client.particle.EntityHugeExplodeFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.optifine.shaders.ShaderPackNone;

public class NpcMerchant implements IMerchant {
   public ShaderPackNone field_0003;
   public MerchantRecipeList recipeList;
   public EntityHugeExplodeFX field_0002;
   public IChatComponent field_175548_d;
   public EntityPlayer customer;
   public InventoryMerchant theMerchantInventory;

   @Override
   public void setRecipes(MerchantRecipeList var1) {
      this.recipeList = var1;
   }

   @Override
   public void useRecipe(MerchantRecipe var1) {
      var1.incrementToolUses();
   }

   @Override
   public EntityPlayer getCustomer() {
      return this.customer;
   }

   public NpcMerchant(EntityPlayer var1, IChatComponent var2) {
      this.customer = var1;
      this.field_175548_d = var2;
      this.theMerchantInventory = new InventoryMerchant(var1, this);
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.field_175548_d != null ? this.field_175548_d : new ChatComponentTranslation("entity.Villager.name"));
   }

   @Override
   public void verifySellingItem(ItemStack var1) {
   }

   @Override
   public MerchantRecipeList getRecipes(EntityPlayer var1) {
      return this.recipeList;
   }

   @Override
   public void setCustomer(EntityPlayer var1) {
   }
}
