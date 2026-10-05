package net.minecraft.entity.ai.attributes;

import java.util.Collection;
import java.util.UUID;

public interface IAttributeInstance {
   Collection<AttributeModifier> getModifiersByOperation(int var1);

   void removeModifier(AttributeModifier var1);

   AttributeModifier getModifier(UUID var1);

   void setBaseValue(double var1);

   IAttribute getAttribute();

   void removeAllModifiers();

   Collection<AttributeModifier> func_111122_c();

   double getBaseValue();

   double getAttributeValue();

   void applyModifier(AttributeModifier var1);

   boolean hasModifier(AttributeModifier var1);
}
