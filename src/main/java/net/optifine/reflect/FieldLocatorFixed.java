package net.optifine.reflect;

import java.lang.reflect.Field;

public class FieldLocatorFixed implements IFieldLocator {
   public Field field;

   public FieldLocatorFixed(Field var1) {
      this.field = var1;
   }

   @Override
   public Field getField() {
      return this.field;
   }
}
