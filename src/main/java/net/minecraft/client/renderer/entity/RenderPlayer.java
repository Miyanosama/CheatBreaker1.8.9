package net.minecraft.client.renderer.entity;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCape;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ResourceLocation;

public class RenderPlayer extends RendererLivingEntity<AbstractClientPlayer> {
   public boolean smallArms;

   public ModelPlayer getMainModel() {
      return (ModelPlayer)super.getMainModel();
   }

   public void setModelVisibilities(AbstractClientPlayer var1) {
      ModelPlayer var2 = this.getMainModel();
      if (var1.isSpectator()) {
         var2.setInvisible(false);
         var2.e.showModel = true;
         var2.f.showModel = true;
      } else {
         ItemStack var3 = var1.bi.getCurrentItem();
         var2.setInvisible(true);
         var2.f.showModel = var1.isWearing(EnumPlayerModelParts.HAT);
         var2.bipedBodyWear.showModel = var1.isWearing(EnumPlayerModelParts.JACKET);
         var2.bipedLeftLegwear.showModel = var1.isWearing(EnumPlayerModelParts.LEFT_PANTS_LEG);
         var2.bipedRightLegwear.showModel = var1.isWearing(EnumPlayerModelParts.RIGHT_PANTS_LEG);
         var2.bipedLeftArmwear.showModel = var1.isWearing(EnumPlayerModelParts.LEFT_SLEEVE);
         var2.bipedRightArmwear.showModel = var1.isWearing(EnumPlayerModelParts.RIGHT_SLEEVE);
         var2.l = 0;
         var2.o = false;
         var2.n = var1.isSneaking();
         if (var3 == null) {
            var2.m = 0;
         } else {
            var2.m = 1;
            if (var1.getItemInUseCount() > 0) {
               EnumAction var4 = var3.getItemUseAction();
               if (var4 == EnumAction.BLOCK) {
                  var2.m = 3;
               } else if (var4 == EnumAction.BOW) {
                  var2.o = true;
               }
            }
         }
      }
   }

   public void doRender(AbstractClientPlayer var1, double var2, double var4, double var6, float var8, float var9) {
      if (!var1.isUser() || this.b.livingPlayer == var1) {
         double var10 = var4;
         if (var1.isSneaking() && !(var1 instanceof EntityPlayerSP)) {
            var10 = var4 - 0.125;
         }

         this.setModelVisibilities(var1);
         super.doRender(var1, var2, var10, var6, var8, var9);
      }
   }

   public void renderLivingAt(AbstractClientPlayer var1, double var2, double var4, double var6) {
      if (var1.isEntityAlive() && var1.bJ()) {
         super.renderLivingAt(var1, var2 + var1.renderOffsetX, var4 + var1.bZ, var6 + var1.renderOffsetZ);
      } else {
         super.renderLivingAt(var1, var2, var4, var6);
      }
   }

   public void rotateCorpse(AbstractClientPlayer var1, float var2, float var3, float var4) {
      if (var1.isEntityAlive() && var1.bJ()) {
         GlStateManager.rotate(var1.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(this.getDeathMaxRotation(var1), 0.0F, 0.0F, 1.0F);
         GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
      } else {
         super.rotateCorpse(var1, var2, var3, var4);
      }
   }

   @Override
   public void y_() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   public void renderRightArm(AbstractClientPlayer var1) {
      float var2 = 1.0F;
      GlStateManager.color(var2, var2, var2);
      ModelPlayer var3 = this.getMainModel();
      this.setModelVisibilities(var1);
      var3.p = 0.0F;
      var3.n = false;
      var3.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, var1);
      var3.renderRightArm();
   }

   public void renderLeftArm(AbstractClientPlayer var1) {
      float var2 = 1.0F;
      GlStateManager.color(var2, var2, var2);
      ModelPlayer var3 = this.getMainModel();
      this.setModelVisibilities(var1);
      var3.n = false;
      var3.p = 0.0F;
      var3.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, var1);
      var3.renderLeftArm();
   }

   public void preRenderCallback(AbstractClientPlayer var1, float var2) {
      float var3 = 0.9375F;
      GlStateManager.scale(var3, var3, var3);
   }

   public RenderPlayer(RenderManager var1, boolean var2) {
      super(var1, new ModelPlayer(0.0F, var2), 0.5F);
      this.smallArms = var2;
      this.a(new LayerBipedArmor(this));
      this.a(new LayerHeldItem(this));
      this.a(new LayerArrow(this));
      this.a(new LayerDeadmau5Head(this));
      this.a(new LayerCape(this));
      this.a(new LayerCustomHead(this.getMainModel().e));
   }

   public RenderPlayer(RenderManager var1) {
      this(var1, false);
   }

   public ResourceLocation getEntityTexture(AbstractClientPlayer var1) {
      return var1.getLocationSkin();
   }

   public void renderOffsetLivingLabel(AbstractClientPlayer var1, double var2, double var4, double var6, String var8, float var9, double var10) {
      if (var10 < 100.0) {
         Scoreboard var12 = var1.getWorldScoreboard();
         ScoreObjective var13 = var12.getObjectiveInDisplaySlot(2);
         if (var13 != null) {
            Score var14 = var12.getValueFromObjective(var1.z_(), var13);
            this.renderLivingLabel(var1, var14.getScorePoints() + " " + var13.getDisplayName(), var2, var4, var6, 64);
            var4 += this.c().FONT_HEIGHT * 1.15F * var9;
         }
      }

      super.renderOffsetLivingLabel(var1, var2, var4, var6, var8, var9, var10);
      if (CheatBreaker.getInstance().getNetHandler().getNametagsMap().containsKey(var1.aK())) {
         for (String var16 : CheatBreaker.getInstance().getNetHandler().getNametagsMap().get(var1.aK())) {
            if (!var16.contains(var1.getNameClear())) {
               var4 += this.c().FONT_HEIGHT * 1.15F * var9;
               this.renderLivingLabel(var1, var16, var2, var4, var6, 64);
            }
         }
      }
   }
}
