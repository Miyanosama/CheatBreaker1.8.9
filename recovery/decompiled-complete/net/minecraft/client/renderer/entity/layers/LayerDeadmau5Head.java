package net.minecraft.client.renderer.entity.layers;

import io.netty.handler.codec.socks.SocksInitRequestDecoder;
import io.netty.handler.codec.socks.UnknownSocksResponse;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.world.gen.MapGenRavine;
import net.optifine.gui.TooltipProviderEnumShaderOptions;
import recovered.unidentified.UnidentifiedClass4185;

public class LayerDeadmau5Head implements LayerRenderer<AbstractClientPlayer> {
   public TooltipProviderEnumShaderOptions field_0003;
   public UnknownSocksResponse field_0005;
   public RenderPlayer playerRenderer;
   public SocksInitRequestDecoder field_0004;
   public MapGenRavine field_0000;
   public S10PacketSpawnPainting field_0001;
   public UnidentifiedClass4185 field_0006;

   public void doRenderLayer(AbstractClientPlayer var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (var1.z_().equals("deadmau5") && var1.hasSkin() && !var1.isInvisible()) {
         this.playerRenderer.a(var1.getLocationSkin());

         for (int var9 = 0; var9 < 2; var9++) {
            float var10 = var1.A + (var1.y - var1.A) * var4 - (var1.aJ + (var1.aI - var1.aJ) * var4);
            float var11 = var1.B + (var1.z - var1.B) * var4;
            GlStateManager.pushMatrix();
            GlStateManager.rotate(var10, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(var11, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(0.375F * (var9 * 2 - 1), 0.0F, 0.0F);
            GlStateManager.translate(0.0F, -0.375F, 0.0F);
            GlStateManager.rotate(-var11, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-var10, 0.0F, 1.0F, 0.0F);
            float var12 = 1.3333334F;
            GlStateManager.scale(var12, var12, var12);
            this.playerRenderer.getMainModel().renderDeadmau5Head(0.0625F);
            GlStateManager.popMatrix();
         }
      }
   }

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   public LayerDeadmau5Head(RenderPlayer var1) {
      this.playerRenderer = var1;
   }
}
