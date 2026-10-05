package net.minecraft.entity.ai.attributes;

public interface IAttribute {
   boolean getShouldWatch();

   double clampValue(double var1);

   String getAttributeUnlocalizedName();

   IAttribute func_180372_d();

   double getDefaultValue();
}
