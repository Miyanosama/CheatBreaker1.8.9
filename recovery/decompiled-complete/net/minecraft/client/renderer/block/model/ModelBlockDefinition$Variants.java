package net.minecraft.client.renderer.block.model;

import java.util.List;
import javazoom.jl.decoder.huffcodetab;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Crossing;
import recovered.unidentified.UnidentifiedClass1222;

public class ModelBlockDefinition$Variants {
   public UnidentifiedClass1222 field_0002;
   public List<ModelBlockDefinition$Variant> listVariants;
   public String name;
   public StructureStrongholdPieces$Crossing field_0003;
   public huffcodetab field_0000;

   public List<ModelBlockDefinition$Variant> getVariants() {
      return this.listVariants;
   }

   public ModelBlockDefinition$Variants(String var1, List<ModelBlockDefinition$Variant> var2) {
      this.name = var1;
      this.listVariants = var2;
   }

   @Override
   public int hashCode() {
      int var1 = this.name.hashCode();
      return 31 * var1 + this.listVariants.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof ModelBlockDefinition$Variants)) {
         return false;
      } else {
         ModelBlockDefinition$Variants var2 = (ModelBlockDefinition$Variants)var1;
         return !this.name.equals(var2.name) ? false : this.listVariants.equals(var2.listVariants);
      }
   }
}
