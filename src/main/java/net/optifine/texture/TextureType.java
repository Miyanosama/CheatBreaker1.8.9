package net.optifine.texture;

public enum TextureType {
   TEXTURE_1D(3552),
   TEXTURE_2D(3553),
   TEXTURE_3D(32879),
   TEXTURE_RECTANGLE(34037);
   public static TextureType[] $VALUES = new TextureType[]{
      TextureType.TEXTURE_1D, TextureType.TEXTURE_2D, TextureType.TEXTURE_3D, TextureType.TEXTURE_RECTANGLE
   };
   public int id;

   public int getId() {
      return this.id;
   }

   TextureType(int var3) {
      this.id = var3;
   }
}
