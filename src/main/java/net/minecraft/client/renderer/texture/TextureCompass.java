package net.minecraft.client.renderer.texture;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TextureCompass extends TextureAtlasSprite {
   public double currentAngle;
   public static String locationSprite;
   public double angleDelta;

   public void updateCompass(World var1, double var2, double var4, double var6, boolean var8, boolean var9) {
      if (!this.framesTextureData.isEmpty()) {
         double var10 = 0.0;
         if (var1 != null && !var8) {
            BlockPos var12 = var1.M();
            double var13 = var12.getX() - var2;
            double var15 = var12.getZ() - var4;
            var6 %= 360.0;
            var10 = -((var6 - 90.0) * Math.PI / 180.0 - Math.atan2(var15, var13));
            if (!var1.t.isSurfaceWorld()) {
               var10 = Math.random() * Math.PI * 2.0;
            }
         }

         if (var9) {
            this.currentAngle = var10;
         } else {
            double var18 = var10 - this.currentAngle;

            while (var18 < -Math.PI) {
               var18 += Math.PI * 2;
            }

            while (var18 >= Math.PI) {
               var18 -= Math.PI * 2;
            }

            var18 = MathHelper.clamp_double(var18, -1.0, 1.0);
            this.angleDelta += var18 * 0.1;
            this.angleDelta *= 0.8;
            this.currentAngle = this.currentAngle + this.angleDelta;
         }

         int var20 = (int)((this.currentAngle / (Math.PI * 2) + 1.0) * this.framesTextureData.size()) % this.framesTextureData.size();

         while (var20 < 0) {
            var20 = (var20 + this.framesTextureData.size()) % this.framesTextureData.size();
         }

         if (var20 != this.frameCounter) {
            this.frameCounter = var20;
            TextureUtil.uploadTextureMipmap(this.framesTextureData.get(this.frameCounter), this.width, this.height, this.originX, this.originY, false, false);
         }
      }
   }

   public TextureCompass(String var1) {
      super(var1);
      locationSprite = var1;
   }

   @Override
   public void updateAnimation() {
      Minecraft var1 = Minecraft.getMinecraft();
      if (var1.theWorld != null && var1.thePlayer != null) {
         this.updateCompass(var1.theWorld, var1.thePlayer.s, var1.thePlayer.u, var1.thePlayer.y, false, false);
      } else {
         this.updateCompass((World)null, 0.0, 0.0, 0.0, true, false);
      }
   }
}
