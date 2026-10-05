package net.minecraft.block.properties;

import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import net.minecraft.entity.monster.EntityEnderman$1;
import org.apache.log4j.helpers.PatternParser$LiteralPatternConverter;

public class PropertyBool extends PropertyHelper<Boolean> {
   public EntityEnderman$1 field_0001;
   public ImmutableSet<Boolean> allowedValues = ImmutableSet.of(true, false);
   public PatternParser$LiteralPatternConverter field_0000;

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
