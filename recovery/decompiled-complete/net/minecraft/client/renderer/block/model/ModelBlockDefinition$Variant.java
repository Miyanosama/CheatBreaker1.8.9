package net.minecraft.client.renderer.block.model;

import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.ResourceLocation;

public class ModelBlockDefinition$Variant {
   public ModelRotation modelRotation;
   public boolean uvLock;
   public ResourceLocation modelLocation;
   public int weight;

   @Override
   public int hashCode() {
      int var1 = this.modelLocation.hashCode();
      var1 = 31 * var1 + (this.modelRotation != null ? this.modelRotation.hashCode() : 0);
      return 31 * var1 + (this.uvLock ? 1 : 0);
   }

   public ModelBlockDefinition$Variant(ResourceLocation var1, ModelRotation var2, boolean var3, int var4) {
      this.modelLocation = var1;
      this.modelRotation = var2;
      this.uvLock = var3;
      this.weight = var4;
   }

   public ModelRotation getRotation() {
      return this.modelRotation;
   }

   public int getWeight() {
      return this.weight;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ModelBlockDefinition$Variant)) {
         return false;
      } else {
         ModelBlockDefinition$Variant var2 = (ModelBlockDefinition$Variant)var1;
         return this.modelLocation.equals(var2.modelLocation) && this.modelRotation == var2.modelRotation && this.uvLock == var2.uvLock;
      }
   }

   public ResourceLocation getModelLocation() {
      return this.modelLocation;
   }

   public boolean isUvLocked() {
      return this.uvLock;
   }
}
