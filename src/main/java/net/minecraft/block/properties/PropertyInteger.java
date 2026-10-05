package net.minecraft.block.properties;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;

public class PropertyInteger extends PropertyHelper<Integer> {
   public ImmutableSet<Integer> allowedValues;

   public static PropertyInteger create(String var0, int var1, int var2) {
      return new PropertyInteger(var0, var1, var2);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + this.allowedValues.hashCode();
   }

   @Override
   public Collection<Integer> getAllowedValues() {
      return this.allowedValues;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null || this.getClass() != var1.getClass()) {
         return false;
      } else if (!super.equals(var1)) {
         return false;
      } else {
         PropertyInteger var2 = (PropertyInteger)var1;
         return this.allowedValues.equals(var2.allowedValues);
      }
   }

   public String getName(Integer var1) {
      return var1.toString();
   }

   public PropertyInteger(String var1, int var2, int var3) {
      super(var1, Integer.class);
      if (var2 < 0) {
         throw new IllegalArgumentException("Min value of " + var1 + " must be 0 or greater");
      } else if (var3 <= var2) {
         throw new IllegalArgumentException("Max value of " + var1 + " must be greater than min (" + var2 + ")");
      } else {
         HashSet var4 = Sets.newHashSet();

         for (int var5 = var2; var5 <= var3; var5++) {
            var4.add(var5);
         }

         this.allowedValues = ImmutableSet.copyOf(var4);
      }
   }
}
