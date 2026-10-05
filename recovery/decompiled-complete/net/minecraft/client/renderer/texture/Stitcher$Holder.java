package net.minecraft.client.renderer.texture;

import com.cheatbreaker.client.module.type.AnimationsModule;
import com.cheatbreaker.client.module.type.EnchantmentGlintModule;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$JunglePyramid$Stones;

public class Stitcher$Holder implements Comparable<Stitcher$Holder> {
   public int width;
   public TextureAtlasSprite theTexture;
   public boolean rotated;
   public int mipmapLevelHolder;
   public EnchantmentGlintModule field_0000;
   public ComponentScatteredFeaturePieces$JunglePyramid$Stones field_0001;
   public AnimationsModule field_0008;
   public int height;
   public float scaleFactor = 1.0F;

   public Stitcher$Holder(TextureAtlasSprite var1, int var2) {
      this.theTexture = var1;
      this.width = var1.getIconWidth();
      this.height = var1.getIconHeight();
      this.mipmapLevelHolder = var2;
      this.rotated = Stitcher.access$000(this.height, var2) > Stitcher.access$000(this.width, var2);
   }

   public TextureAtlasSprite getAtlasSprite() {
      return this.theTexture;
   }

   public boolean isRotated() {
      return this.rotated;
   }

   @Override
   public String toString() {
      return "Holder{width=" + this.width + ", height=" + this.height + '}';
   }

   public void rotate() {
      this.rotated = !this.rotated;
   }

   public int getHeight() {
      return this.rotated
         ? Stitcher.access$000((int)(this.width * this.scaleFactor), this.mipmapLevelHolder)
         : Stitcher.access$000((int)(this.height * this.scaleFactor), this.mipmapLevelHolder);
   }

   public void setNewDimension(int var1) {
      if (this.width > var1 && this.height > var1) {
         this.scaleFactor = (float)var1 / Math.min(this.width, this.height);
      }
   }

   public int compareTo(Stitcher$Holder var1) {
      int var2;
      if (this.getHeight() == var1.getHeight()) {
         if (this.getWidth() == var1.getWidth()) {
            if (this.theTexture.getIconName() == null) {
               return var1.theTexture.getIconName() == null ? 0 : -1;
            }

            return this.theTexture.getIconName().compareTo(var1.theTexture.getIconName());
         }

         var2 = this.getWidth() < var1.getWidth() ? 1 : -1;
      } else {
         var2 = this.getHeight() < var1.getHeight() ? 1 : -1;
      }

      return var2;
   }

   public int getWidth() {
      return this.rotated
         ? Stitcher.access$000((int)(this.height * this.scaleFactor), this.mipmapLevelHolder)
         : Stitcher.access$000((int)(this.width * this.scaleFactor), this.mipmapLevelHolder);
   }
}
