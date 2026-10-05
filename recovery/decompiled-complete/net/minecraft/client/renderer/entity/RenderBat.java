package net.minecraft.client.renderer.entity;

import com.cheatbreaker.client.util.server.ServerRestrictionAction;
import io.netty.channel.AbstractChannelHandlerContext;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.server.management.PlayerProfileCache$1;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderBat extends RenderLiving<EntityBat> {
   public static ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");
   public ServerSelectionList field_0003;
   public PlayerProfileCache$1 field_0004;
   public AbstractChannelHandlerContext field_0000;
   public ServerRestrictionAction field_0002;

   public RenderBat(RenderManager var1) {
      super(var1, new ModelBat(), 0.25F);
   }

   public void preRenderCallback(EntityBat var1, float var2) {
      GlStateManager.scale(0.35F, 0.35F, 0.35F);
   }

   public void rotateCorpse(EntityBat var1, float var2, float var3, float var4) {
      if (!var1.getIsBatHanging()) {
         GlStateManager.translate(0.0F, MathHelper.cos(var2 * 0.3F) * 0.1F, 0.0F);
      } else {
         GlStateManager.translate(0.0F, -0.1F, 0.0F);
      }

      super.rotateCorpse(var1, var2, var3, var4);
   }

   public ResourceLocation getEntityTexture(EntityBat var1) {
      return batTextures;
   }
}
