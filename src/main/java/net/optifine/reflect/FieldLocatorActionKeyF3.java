package net.optifine.reflect;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import net.minecraft.client.Minecraft;
import net.optifine.reflect.IFieldLocator;
import net.optifine.reflect.ReflectorRaw;

public class FieldLocatorActionKeyF3 implements IFieldLocator {
   @Override
   public Field getField() {
      Class<Minecraft> var1 = Minecraft.class;
      Field var2 = this.method_02843();
      if (var2 == null) {
         return null;
      } else {
         Field var3 = ReflectorRaw.getFieldAfter(Minecraft.class, var2, boolean.class, 0);
         return var3 == null ? null : var3;
      }
   }

   public Field method_02843() {
      Minecraft var1 = Minecraft.getMinecraft();
      boolean var2 = var1.renderChunksMany;
      Field[] var3 = Minecraft.class.getDeclaredFields();
      var1.renderChunksMany = true;
      Field[] var4 = ReflectorRaw.getFields(var1, var3, boolean.class, Boolean.TRUE);
      var1.renderChunksMany = false;
      Field[] var5 = ReflectorRaw.getFields(var1, var3, boolean.class, Boolean.FALSE);
      var1.renderChunksMany = var2;
      HashSet var6 = new HashSet<>(Arrays.asList(var4));
      HashSet var7 = new HashSet<>(Arrays.asList(var5));
      HashSet var8 = new HashSet(var6);
      var8.retainAll(var7);
      Field[] var9 = (java.lang.reflect.Field[])var8.toArray(new Field[var8.size()]);
      return var9.length != 1 ? null : var9[0];
   }
}
