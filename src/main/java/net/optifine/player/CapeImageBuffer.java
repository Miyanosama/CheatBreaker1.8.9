package net.optifine.player;

import java.awt.image.BufferedImage;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.util.ResourceLocation;

public class CapeImageBuffer extends ImageBufferDownload {
   public AbstractClientPlayer player;
   public boolean elytraOfCape;
   public ResourceLocation resourceLocation;

   public CapeImageBuffer(AbstractClientPlayer var1, ResourceLocation var2) {
      this.player = var1;
      this.resourceLocation = var2;
   }

   @Override
   public void skinAvailable() {
      if (this.player != null) {
         this.player.setLocationOfCape(this.resourceLocation);
         this.player.setElytraOfCape(this.elytraOfCape);
      }

      this.cleanup();
   }

   public void cleanup() {
      this.player = null;
   }

   @Override
   public BufferedImage parseUserSkin(BufferedImage var1) {
      BufferedImage var2 = CapeUtils.parseCape(var1);
      this.elytraOfCape = CapeUtils.isElytraCape(var1, var2);
      return var2;
   }

   public boolean isElytraOfCape() {
      return this.elytraOfCape;
   }
}
