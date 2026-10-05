package net.minecraft.entity.ai.attributes;

public abstract class BaseAttribute implements IAttribute {
   public IAttribute field_180373_a;
   public String unlocalizedName;
   public double defaultValue;
   public boolean shouldWatch;

   public BaseAttribute(IAttribute var1, String var2, double var3) {
      this.field_180373_a = var1;
      this.unlocalizedName = var2;
      this.defaultValue = var3;
      if (var2 == null) {
         throw new IllegalArgumentException("Name cannot be null!");
      }
   }

   public BaseAttribute setShouldWatch(boolean var1) {
      this.shouldWatch = var1;
      return this;
   }

   @Override
   public int hashCode() {
      return this.unlocalizedName.hashCode();
   }

   @Override
   public String getAttributeUnlocalizedName() {
      return this.unlocalizedName;
   }

   @Override
   public boolean getShouldWatch() {
      return this.shouldWatch;
   }

   @Override
   public double getDefaultValue() {
      return this.defaultValue;
   }

   @Override
   public IAttribute func_180372_d() {
      return this.field_180373_a;
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof IAttribute && this.unlocalizedName.equals(((IAttribute)var1).getAttributeUnlocalizedName());
   }
}
