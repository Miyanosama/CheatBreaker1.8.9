package net.minecraft.block.properties;

import com.google.common.base.Objects;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.entity.RenderEndermite;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;

public abstract class PropertyHelper<T extends Comparable<T>> implements IProperty<T> {
   public RenderEndermite field_0004;
   public ModelSilverfish field_0000;
   public Class<T> valueClass;
   public String name;
   public BiomeGenBase$SpawnListEntry field_0001;

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public Class<T> getValueClass() {
      return this.valueClass;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         PropertyHelper var2 = (PropertyHelper)var1;
         return this.valueClass.equals(var2.valueClass) && this.name.equals(var2.name);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return Objects.toStringHelper(this).add("name", this.name).add("clazz", this.valueClass).add("values", this.getAllowedValues()).toString();
   }

   @Override
   public int hashCode() {
      return 31 * this.valueClass.hashCode() + this.name.hashCode();
   }

   public PropertyHelper(String var1, Class<T> var2) {
      this.valueClass = var2;
      this.name = var1;
   }
}
