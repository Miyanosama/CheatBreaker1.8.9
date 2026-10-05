package net.minecraft.block.state;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.MapPopulator;
import net.optifine.shaders.uniform.ShaderUniform1f;
import recovered.unidentified.UnidentifiedClass1117;
import recovered.unidentified.UnidentifiedClass1222;

public class BlockState {
   public static Joiner COMMA_JOINER = Joiner.on(", ");
   public ImmutableList<IProperty> properties;
   public ImmutableList<IBlockState> validStates;
   public static Function<IProperty, String> GET_NAME_FUNC = new BlockState$1();
   public Block block;
   public ShaderUniform1f field_0001;
   public UnidentifiedClass1117 field_0006;

   @Override
   public String toString() {
      return Objects.toStringHelper(this)
         .add("block", Block.blockRegistry.getNameForObject(this.block))
         .add("properties", Iterables.transform(this.properties, GET_NAME_FUNC))
         .toString();
   }

   public IBlockState getBaseState() {
      return (IBlockState)this.validStates.get(0);
   }

   public BlockState(Block var1, IProperty... var2) {
      this.block = var1;
      Arrays.sort(var2, new BlockState$2(this));
      this.properties = ImmutableList.copyOf(var2);
      LinkedHashMap var3 = Maps.newLinkedHashMap();
      ArrayList var4 = Lists.newArrayList();

      for (List var6 : UnidentifiedClass1222.method_08279(this.getAllowedValues())) {
         Map var7 = MapPopulator.createMap(this.properties, var6);
         BlockState$StateImplementation var8 = new BlockState$StateImplementation(var1, ImmutableMap.copyOf(var7), null);
         var3.put(var7, var8);
         var4.add(var8);
      }

      for (BlockState$StateImplementation var10 : var4) {
         var10.buildPropertyValueTable(var3);
      }

      this.validStates = ImmutableList.copyOf(var4);
   }

   public List<Iterable<Comparable>> getAllowedValues() {
      ArrayList var1 = Lists.newArrayList();

      for (int var2 = 0; var2 < this.properties.size(); var2++) {
         var1.add(((IProperty)this.properties.get(var2)).getAllowedValues());
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
}
