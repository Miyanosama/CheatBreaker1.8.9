package net.optifine.texture;

import net.optifine.expr.ExpressionFloatArrayCached;

public enum TextureType {
   TEXTURE_1D(3552),
   TEXTURE_2D(3553),
   TEXTURE_3D(32879),
   TEXTURE_RECTANGLE(34037);

   public PixelFormat field_0003;
   // $VF: synthetic field
   public static TextureType[] $VALUES = new TextureType[]{
      TextureType.TEXTURE_1D, TextureType.TEXTURE_2D, TextureType.TEXTURE_3D, TextureType.TEXTURE_RECTANGLE
   };
   public int id;
   public ExpressionFloatArrayCached field_0004;

   public int getId() {
      return this.id;
   }

   public TextureType(int var3) {
      this.id = var3;
   }
}
