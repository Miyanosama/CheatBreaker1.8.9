package net.optifine.shaders.config;

public class ShaderMacro {
   public String value;
   public String name;

   public ShaderMacro(String var1, String var2) {
      this.name = var1;
      this.value = var2;
   }

   public String getValue() {
      return this.value;
   }

   @Override
   public String toString() {
      return this.getSourceLine();
   }

   public String getSourceLine() {
      return "#define " + this.name + " " + this.value;
   }

   public String getName() {
      return this.name;
   }
}
