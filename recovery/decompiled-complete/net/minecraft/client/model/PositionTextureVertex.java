package net.minecraft.client.model;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$KeySpliterator;
import net.minecraft.util.Vec3;
import recovered.unidentified.UnidentifiedClass3153;

public class PositionTextureVertex {
   public ConcurrentHashMapV8$KeySpliterator field_0002;
   public UnidentifiedClass3153 field_0004;
   public Vec3 vector3D;
   public float texturePositionY;
   public float texturePositionX;

   public PositionTextureVertex setTexturePosition(float var1, float var2) {
      return new PositionTextureVertex(this, var1, var2);
   }

   public PositionTextureVertex(float var1, float var2, float var3, float var4, float var5) {
      this(new Vec3(var1, var2, var3), var4, var5);
   }

   public PositionTextureVertex(Vec3 var1, float var2, float var3) {
      this.vector3D = var1;
      this.texturePositionX = var2;
      this.texturePositionY = var3;
   }

   public PositionTextureVertex(PositionTextureVertex var1, float var2, float var3) {
      this.vector3D = var1.vector3D;
      this.texturePositionX = var2;
      this.texturePositionY = var3;
   }
}
