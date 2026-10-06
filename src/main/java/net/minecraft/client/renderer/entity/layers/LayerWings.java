package net.minecraft.client.renderer.entity.layers;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class LayerWings implements LayerRenderer<AbstractClientPlayer> {
   private final RenderPlayer playerRenderer;

   public LayerWings(RenderPlayer playerRenderer) {
      this.playerRenderer = playerRenderer;
   }

   @Override
   public boolean shouldCombineTextures() {
      return false;
   }

   @Override
   public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks,
      float ageInTicks, float headYaw, float headPitch, float scale) {
      ModelPlayer model = this.playerRenderer.getMainModel();
      ClientResourceManager wings = player == Minecraft.getMinecraft().thePlayer
         ? CheatBreaker.getInstance().method_19791().getLocalCosmetics().getEquipped(CosmeticType.WINGS)
         : player.method_00329();
      if (!player.isInvisible() && !player.isSpectator() && !model.o && wings != null && wings.method_20848() == CosmeticType.WINGS
         && CheatBreaker.getInstance().getGlobalSettings().recoveredField543.method_08908()) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.pushMatrix();
         if (model.r) {
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
         } else if (player.isSneaking()) {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
         }
         model.recoveredField3328.method_20013(player, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch,
            scale, wings.method_20846(), wings.method_20859());
         GlStateManager.popMatrix();
      }
   }
}
