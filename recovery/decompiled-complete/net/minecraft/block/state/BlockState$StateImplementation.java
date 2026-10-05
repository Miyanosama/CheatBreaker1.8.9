package net.minecraft.block.state;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$8;

public class BlockState$StateImplementation extends BlockStateBase {
   public LogBrokerMonitor$8 field_0001;
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

   public BlockState$StateImplementation(Block var1, ImmutableMap<IProperty, Comparable> var2) {
      this.block = var1;
      this.properties = var2;
   }

   public Map<IProperty, Comparable> getPropertiesWithValue(IProperty var1, Comparable var2) {
      HashMap var3 = Maps.newHashMap(this.properties);
      var3.put(var1, var2);
      return var3;
   }

   public void buildPropertyValueTable(Map<Map<IProperty, Comparable>, BlockState$StateImplementation> var1) {
      if (this.propertyValueTable != null) {
         throw new IllegalStateException();
      } else {
         HashBasedTable var2 = HashBasedTable.create();
         UnmodifiableIterator var3 = this.properties.keySet().iterator();

         while (var3.hasNext()) {
            IProperty var4 = (IProperty)var3.next();

            for (Comparable var6 : var4.getAllowedValues()) {
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
