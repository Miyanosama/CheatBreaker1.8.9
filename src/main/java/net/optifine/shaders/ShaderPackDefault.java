package net.optifine.shaders;

import java.io.InputStream;

public class ShaderPackDefault implements IShaderPack {
   @Override
   public void close() {
   }

   @Override
   public InputStream getResourceAsStream(String var1) {
      return ShaderPackDefault.class.getResourceAsStream(var1);
   }

   @Override
   public boolean hasDirectory(String var1) {
      return false;
   }

   @Override
   public String getName() {
      return "(internal)";
   }
}
