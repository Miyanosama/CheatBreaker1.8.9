package net.minecraft.block.properties;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;

public class PropertyBool extends PropertyHelper<Boolean> {
   public ImmutableSet<Boolean> allowedValues = ImmutableSet.of(true, false);

   public String getName(Boolean var1) {
      return var1.toString();
   }

   public static PropertyBool create(String var0) {
      return new PropertyBool(var0);
   }

   @Override
   public Collection<Boolean> getAllowedValues() {
      return this.allowedValues;
   }

   public PropertyBool(String var1) {
      super(var1, Boolean.class);
   }
}
