package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import recovered.unidentified.UnidentifiedClass4723;

public class RenderItem$5 implements ItemMeshDefinition {
   public UnidentifiedClass4723 field_0000;

   public RenderItem$5(RenderItem var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public ModelResourceLocation getModelLocation(ItemStack var1) {
      return ItemPotion.isSplash(var1.getMetadata())
         ? new ModelResourceLocation("bottle_splash", "inventory")
         : new ModelResourceLocation("bottle_drinkable", "inventory");
   }
}
