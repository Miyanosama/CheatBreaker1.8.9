package net.optifine.shaders;

public interface ICustomTexture {
   int getTextureId();

   int getTextureUnit();

   int getTarget();

   void deleteTexture();
}
