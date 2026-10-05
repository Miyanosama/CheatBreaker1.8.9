package net.optifine.reflect;

import java.lang.reflect.Field;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class FieldLocatorFixed implements IFieldLocator {
   public Field field;
   public ModelAdapterQuadruped field_0001;

   public FieldLocatorFixed(Field var1) {
      this.field = var1;
   }

   @Override
   public Field getField() {
      return this.field;
   }
}
