package net.optifine.shaders;

import java.io.InputStream;

public interface IShaderPack {
   InputStream getResourceAsStream(String var1);

   String getName();

   void close();

   boolean hasDirectory(String var1);
}
