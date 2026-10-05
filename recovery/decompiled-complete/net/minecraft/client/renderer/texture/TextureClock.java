package net.minecraft.client.renderer.texture;

import io.netty.handler.codec.http.HttpContentEncoder;
import io.netty.util.HashedWheelTimer$HashedWheelBucket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityRabbit$RabbitMoveHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$MonumentBuilding;
import recovered.unidentified.UnidentifiedClass1036;

public class TextureClock extends TextureAtlasSprite {
   public EntityRabbit$RabbitMoveHelper field_0002;
   public UnidentifiedClass1036 field_0001;
   public HttpContentEncoder field_0000;
   public double field_0006;
   public double field_0004;
   public StructureOceanMonumentPieces$MonumentBuilding field_0005;
   public HashedWheelTimer$HashedWheelBucket field_0003;

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

         double var4 = var2 - this.field_0004;

         while (var4 < -0.5) {
            var4++;
         }

         while (var4 >= 0.5) {
            var4--;
         }

         var4 = MathHelper.clamp_double(var4, -1.0, 1.0);
         this.field_0006 += var4 * 0.1;
         this.field_0006 *= 0.8;
         this.field_0004 = this.field_0004 + this.field_0006;
         int var6 = (int)((this.field_0004 + 1.0) * this.framesTextureData.size()) % this.framesTextureData.size();

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
