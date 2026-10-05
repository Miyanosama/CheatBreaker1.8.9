package net.minecraft.block.state;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.MapPopulator;
import net.minecraft.util.Cartesian;

public class BlockState {
   public static Joiner COMMA_JOINER = Joiner.on(", ");
   public ImmutableList<IProperty> properties;
   public ImmutableList<IBlockState> validStates;
   public static Function<IProperty, String> GET_NAME_FUNC = new Function<IProperty, String>() {
      public String apply(IProperty var1) {
         return var1 == null ? "<NULL>" : var1.getName();
      }
   };
   public Block block;

   @Override
   public String toString() {
      return Objects.toStringHelper(this)
         .add("block", Block.blockRegistry.getNameForObject(this.block))
         .add("properties", Iterables.transform(this.properties, GET_NAME_FUNC))
         .toString();
   }

   public IBlockState getBaseState() {
      return this.validStates.get(0);
   }

   public BlockState(Block var1, IProperty... var2) {
      this.block = var1;
      Arrays.sort(var2, new Comparator<IProperty>() {
         public int compare(IProperty var1, IProperty var2x) {
            return var1.getName().compareTo(var2x.getName());
         }
      });
      this.properties = ImmutableList.copyOf(var2);
      LinkedHashMap var3 = Maps.newLinkedHashMap();
      ArrayList var4 = Lists.newArrayList();

      for (List var6 : Cartesian.method_08279(this.getAllowedValues())) {
         Map var7 = MapPopulator.createMap(this.properties, var6);
         BlockState.StateImplementation var8 = new BlockState.StateImplementation(var1, ImmutableMap.copyOf(var7));
         var3.put(var7, var8);
         var4.add(var8);
      }

      for (BlockState.StateImplementation var10 : (Iterable<BlockState.StateImplementation>)(Iterable<?>)(var4)) {
         var10.buildPropertyValueTable(var3);
      }

      this.validStates = ImmutableList.copyOf(var4);
   }

   public List<Iterable<Comparable>> getAllowedValues() {
      ArrayList var1 = Lists.newArrayList();

      for (int var2 = 0; var2 < this.properties.size(); var2++) {
         var1.add(this.properties.get(var2).getAllowedValues());
      }

      return var1;
   }

   public Block getBlock() {
      return this.block;
   }

   public Collection<IProperty> getProperties() {
      return this.properties;
   }

   public ImmutableList<IBlockState> getValidStates() {
      return this.validStates;
   }

   public static class StateImplementation extends BlockStateBase {
      public ImmutableTable<IProperty, Comparable, IBlockState> propertyValueTable;
      public Block block;
      public ImmutableMap<IProperty, Comparable> properties;

      @Override
      public Collection<IProperty> getPropertyNames() {
         return Collections.unmodifiableCollection(this.properties.keySet());
      }

      @Override
      public ImmutableMap<IProperty, Comparable> getProperties() {
         return this.properties;
      }

      public StateImplementation(Block var1, ImmutableMap<IProperty, Comparable> var2) {
         this.block = var1;
         this.properties = var2;
      }

      public Map<IProperty, Comparable> getPropertiesWithValue(IProperty var1, Comparable var2) {
         HashMap var3 = Maps.newHashMap(this.properties);
         var3.put(var1, var2);
         return var3;
      }

      public void buildPropertyValueTable(Map<Map<IProperty, Comparable>, BlockState.StateImplementation> var1) {
         if (this.propertyValueTable != null) {
            throw new IllegalStateException();
         } else {
            HashBasedTable var2 = HashBasedTable.create();

            for (IProperty var4 : this.properties.keySet()) {
               for (Comparable var6 : (Iterable<Comparable>)(Iterable<?>)(var4.getAllowedValues())) {
                  if (var6 != this.properties.get(var4)) {
                     var2.put(var4, var6, var1.get(this.getPropertiesWithValue(var4, var6)));
                  }
               }
            }

            this.propertyValueTable = ImmutableTable.copyOf(var2);
         }
      }

      @Override
      public int hashCode() {
         return this.properties.hashCode();
      }

      @Override
      public <T extends Comparable<T>, V extends T> IBlockState withProperty(IProperty<T> var1, V var2) {
         if (!this.properties.containsKey(var1)) {
            throw new IllegalArgumentException("Cannot set property " + var1 + " as it does not exist in " + this.block.P());
         } else if (!var1.getAllowedValues().contains(var2)) {
            throw new IllegalArgumentException(
               "Cannot set property " + var1 + " to " + var2 + " on block " + Block.blockRegistry.getNameForObject(this.block) + ", it is not an allowed value"
            );
         } else {
            return (IBlockState)(this.properties.get(var1) == var2 ? this : (IBlockState)this.propertyValueTable.get(var1, var2));
         }
      }

      @Override
      public boolean equals(Object var1) {
         return this == var1;
      }

      @Override
      public <T extends Comparable<T>> T getValue(IProperty<T> var1) {
         if (!this.properties.containsKey(var1)) {
            throw new IllegalArgumentException("Cannot get property " + var1 + " as it does not exist in " + this.block.P());
         } else {
            return (T)var1.getValueClass().cast(this.properties.get(var1));
         }
      }

      @Override
      public Block getBlock() {
         return this.block;
      }
   }
}
