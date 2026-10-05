package net.minecraft.client.renderer.block.statemap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class StateMap extends StateMapperBase {
   public List<IProperty<?>> ignored;
   public String suffix;
   public IProperty<?> name;

   public StateMap(IProperty<?> var1, String var2, List<IProperty<?>> var3) {
      this.name = var1;
      this.suffix = var2;
      this.ignored = var3;
   }

   @Override
   public ModelResourceLocation getModelResourceLocation(IBlockState var1) {
      LinkedHashMap var2 = Maps.newLinkedHashMap(var1.getProperties());
      String var3;
      if (this.name == null) {
         var3 = Block.blockRegistry.getNameForObject(var1.getBlock()).toString();
      } else {
         var3 = ((IProperty)this.name).getName((Comparable)var2.remove(this.name));
      }

      if (this.suffix != null) {
         var3 = var3 + this.suffix;
      }

      for (IProperty var5 : this.ignored) {
         var2.remove(var5);
      }

      return new ModelResourceLocation(var3, this.getPropertyString(var2));
   }

   public static class Builder {
      public IProperty<?> name;
      public String suffix;
      public List<IProperty<?>> ignored = Lists.newArrayList();

      public StateMap.Builder ignore(IProperty<?>... var1) {
         Collections.addAll(this.ignored, var1);
         return this;
      }

      public StateMap build() {
         return new StateMap(this.name, this.suffix, this.ignored);
      }

      public StateMap.Builder withName(IProperty<?> var1) {
         this.name = var1;
         return this;
      }

      public StateMap.Builder withSuffix(String var1) {
         this.suffix = var1;
         return this;
      }
   }
}
