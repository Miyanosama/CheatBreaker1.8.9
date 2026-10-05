package net.optifine.shaders.config;

import net.minecraft.src.Config;
import net.optifine.util.StrUtils;
import org.lwjgl.util.vector.Vector4f;

public class ShaderLine {
   public String line;
   public static final int recoveredField3298 = 1;
   public static final int recoveredField3299 = 6;
   public String value;
   public static final int recoveredField3300 = 8;
   public static final int recoveredField3301 = 5;
   public int type;
   public String name;
   public static final int recoveredField3302 = 7;
   public static final int recoveredField3303 = 4;
   public static final int recoveredField3304 = 3;
   public static final int recoveredField3305 = 2;

   public String getValue() {
      return this.value;
   }

   public boolean isConstBool() {
      return this.type == 5;
   }

   public boolean method_09522(String var1) {
      return this.isUniform() && var1.equals(this.name);
   }

   public ShaderLine(int var1, String var2, String var3, String var4) {
      this.type = var1;
      this.name = var2;
      this.value = var3;
      this.line = var4;
   }

   public boolean isConstBool(String var1, String var2, String var3) {
      return this.isConstBool(var1) || this.isConstBool(var2) || this.isConstBool(var3);
   }

   public boolean method_09536(String var1) {
      return this.isConstInt() && var1.equals(this.name);
   }

   public boolean getValueBool() {
      String var1 = this.value.toLowerCase();
      if (!var1.equals("true") && !var1.equals("false")) {
         throw new RuntimeException("Invalid boolean: " + this.value + ", line: " + this.line);
      } else {
         return Boolean.valueOf(this.value);
      }
   }

   public boolean isAttribute() {
      return this.type == 2;
   }

   public Vector4f getValueVec4() {
      if (this.value == null) {
         return null;
      } else {
         String var1 = this.value.trim();
         var1 = StrUtils.removePrefix(var1, "vec4");
         var1 = StrUtils.trim(var1, " ()");
         String[] var2 = Config.tokenize(var1, ", ");
         if (var2.length != 4) {
            return null;
         } else {
            float[] var3 = new float[4];

            for (int var4 = 0; var4 < var2.length; var4++) {
               String var5 = var2[var4];
               var5 = StrUtils.removeSuffix(var5, new String[]{"F", "f"});
               float var6 = Config.parseFloat(var5, Float.MAX_VALUE);
               if (var6 == Float.MAX_VALUE) {
                  return null;
               }

               var3[var4] = var6;
            }

            return new Vector4f(var3[0], var3[1], var3[2], var3[3]);
         }
      }
   }

   public float getValueFloat() {
      try {
         return Float.parseFloat(this.value);
      } catch (NumberFormatException var2) {
         throw new NumberFormatException("Invalid float: " + this.value + ", line: " + this.line);
      }
   }

   public boolean isUniform() {
      return this.type == 1;
   }

   public int getType() {
      return this.type;
   }

   public boolean isConstBool(String var1, String var2) {
      return this.isConstBool(var1) || this.isConstBool(var2);
   }

   public boolean method_09520(String var1) {
      return this.isConstVec4() && this.name.endsWith(var1);
   }

   public boolean isProperty(String var1) {
      return this.isProperty() && var1.equals(this.name);
   }

   public boolean method_09513(String var1) {
      return this.isConstInt() && this.name.endsWith(var1);
   }

   public String getName() {
      return this.name;
   }

   public boolean isExtension() {
      return this.type == 7;
   }

   public int getValueInt() {
      try {
         return Integer.parseInt(this.value);
      } catch (NumberFormatException var2) {
         throw new NumberFormatException("Invalid integer: " + this.value + ", line: " + this.line);
      }
   }

   public boolean isConstVec4() {
      return this.type == 8;
   }

   public boolean isConstFloat() {
      return this.type == 4;
   }

   public boolean isConstInt() {
      return this.type == 3;
   }

   public boolean isProperty() {
      return this.type == 6;
   }

   public boolean isConstBool(String var1, String var2, boolean var3) {
      return this.isConstBool(var1, var2) && this.getValueBool() == var3;
   }

   public boolean method_09527(String var1, boolean var2) {
      return this.method_09515(var1) && this.getValueBool() == var2;
   }

   public boolean method_09515(String var1) {
      return this.isConstBool() && this.name.endsWith(var1);
   }

   public boolean isProperty(String var1, String var2) {
      return this.isProperty(var1) && var2.equals(this.value);
   }

   public boolean isConstBool(String var1) {
      return this.isConstBool() && var1.equals(this.name);
   }

   public boolean method_09531(String var1) {
      return this.isAttribute() && var1.equals(this.name);
   }

   public boolean isConstBool(String var1, String var2, String var3, boolean var4) {
      return this.isConstBool(var1, var2, var3) && this.getValueBool() == var4;
   }

   public boolean method_09538(String var1, boolean var2) {
      return this.isConstBool(var1) && this.getValueBool() == var2;
   }

   public boolean method_09517(String var1) {
      return this.isExtension() && var1.equals(this.name);
   }

   public boolean method_09542(String var1) {
      return this.isConstFloat() && var1.equals(this.name);
   }
}
