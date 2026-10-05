package net.optifine.shaders.config;

public class ScreenShaderOptions {
   public String name;
   public int columns;
   public ShaderOption[] shaderOptions;

   public ShaderOption[] getShaderOptions() {
      return this.shaderOptions;
   }

   public String getName() {
      return this.name;
   }

   public ScreenShaderOptions(String var1, ShaderOption[] var2, int var3) {
      this.name = var1;
      this.shaderOptions = var2;
      this.columns = var3;
   }

   public int getColumns() {
      return this.columns;
   }
}
