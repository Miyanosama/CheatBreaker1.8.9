package net.optifine.reflect;

import io.netty.handler.codec.spdy.DefaultSpdyRstStreamFrame;
import io.netty.util.internal.chmv8.ForkJoinPool$1;
import java.lang.reflect.Field;
import net.optifine.shaders.config.ScreenShaderOptions;

public class ReflectorField implements IResolvable {
   public boolean checked;
   public DefaultSpdyRstStreamFrame field_0005;
   public IFieldLocator fieldLocator = null;
   public ForkJoinPool$1 field_0004;
   public Field targetField;
   public ScreenShaderOptions field_0001;

   @Override
   public void resolve() {
      Field var1 = this.getTargetField();
   }

   public ReflectorField(ReflectorClass var1, Class var2) {
      this(var1, var2, 0);
   }

   public ReflectorField(IFieldLocator var1) {
      this.checked = false;
      this.targetField = null;
      this.fieldLocator = var1;
      ReflectorResolver.register(this);
   }

   public boolean exists() {
      return this.getTargetField() != null;
   }

   public ReflectorField(Field var1) {
      this(new FieldLocatorFixed(var1));
   }

   public void setValue(Object var1, Object var2) {
      Reflector.setFieldValue(var1, this, var2);
   }

   public ReflectorField(ReflectorClass var1, Class var2, int var3) {
      this(new FieldLocatorType(var1, var2, var3));
   }

   public void setValue(Object var1) {
      Reflector.setFieldValue(null, this, var1);
   }

   public Object getValue() {
      return Reflector.getFieldValue(null, this);
   }

   public Field getTargetField() {
      if (this.checked) {
         return this.targetField;
      } else {
         this.checked = true;
         this.targetField = this.fieldLocator.getField();
         if (this.targetField != null) {
            this.targetField.setAccessible(true);
         }

         return this.targetField;
      }
   }

   public ReflectorField(ReflectorClass var1, String var2) {
      this(new FieldLocatorName(var1, var2));
   }
}
