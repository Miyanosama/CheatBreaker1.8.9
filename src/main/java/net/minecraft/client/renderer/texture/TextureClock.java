package net.minecraft.client.renderer.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class TextureClock extends TextureAtlasSprite {
   public double recoveredField141;
   public double recoveredField142;

   public TextureClock(String var1) {
      super(var1);
   }

   @Override
   public void updateAnimation() {
      if (!this.framesTextureData.isEmpty()) {
         Minecraft var1 = Minecraft.getMinecraft();
         double var2 = 0.0;
         if (var1.theWorld != null && var1.thePlayer != null) {
            var2 = var1.theWorld.getCelestialAngle(1.0F);
            if (!var1.theWorld.t.isSurfaceWorld()) {
               var2 = Math.random();
            }
         }

         double var4 = var2 - this.recoveredField142;

         while (var4 < -0.5) {
            var4++;
         }

         while (var4 >= 0.5) {
            var4--;
         }

         var4 = MathHelper.clamp_double(var4, -1.0, 1.0);
         this.recoveredField141 += var4 * 0.1;
         this.recoveredField141 *= 0.8;
         this.recoveredField142 = this.recoveredField142 + this.recoveredField141;
         int var6 = (int)((this.recoveredField142 + 1.0) * this.framesTextureData.size()) % this.framesTextureData.size();

         while (var6 < 0) {
            var6 = (var6 + this.framesTextureData.size()) % this.framesTextureData.size();
         }

         if (var6 != this.frameCounter) {
            this.frameCounter = var6;
            TextureUtil.uploadTextureMipmap(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
         }
      }
   }
}
