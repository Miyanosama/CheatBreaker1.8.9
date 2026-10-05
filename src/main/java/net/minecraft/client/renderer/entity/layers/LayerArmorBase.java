package net.minecraft.client.renderer.entity.layers;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.EnchantmentGlintModule;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomItems;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersRender;

public abstract class LayerArmorBase<T extends ModelBase> implements LayerRenderer<EntityLivingBase> {
   public float colorB;
   public float colorG;
   public RendererLivingEntity<?> renderer;
   public boolean skipRenderGlint;
   public static ResourceLocation ENCHANTED_ITEM_GLINT_RES = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   public float alpha = 1.0F;
   public static Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP = Maps.newHashMap();
   public float colorR = 1.0F;
   public T d;
   public T c;

   public abstract void setModelPartVisible(T var1, int var2);

   public T getArmorModelHook(EntityLivingBase var1, ItemStack var2, int var3, T var4) {
      return (T)var4;
   }

   public T getArmorModel(int var1) {
      return this.isSlotForLeggings(var1) ? this.c : this.d;
   }

   public LayerArmorBase(RendererLivingEntity<?> var1) {
      this.colorG = 1.0F;
      this.colorB = 1.0F;
      this.renderer = var1;
      this.initArmor();
   }

   public ResourceLocation getArmorResource(ItemArmor var1, boolean var2, String var3) {
      String var4 = String.format(
         "textures/models/armor/%s_layer_%d%s.png", var1.getArmorMaterial().getName(), var2 ? 2 : 1, var3 == null ? "" : String.format("_%s", var3)
      );
      ResourceLocation var5 = ARMOR_TEXTURE_RES_MAP.get(var4);
      if (var5 == null) {
         var5 = new ResourceLocation(var4);
         ARMOR_TEXTURE_RES_MAP.put(var4, var5);
      }

      return var5;
   }

   public abstract void initArmor();

   public boolean isSlotForLeggings(int var1) {
      return var1 == 2;
   }

   public ResourceLocation getArmorResource(Entity var1, ItemStack var2, int var3, String var4) {
      ItemArmor var5 = (ItemArmor)var2.getItem();
      String var6 = var5.getArmorMaterial().getName();
      String var7 = "minecraft";
      int var8 = var6.indexOf(58);
      if (var8 != -1) {
         var7 = var6.substring(0, var8);
         var6 = var6.substring(var8 + 1);
      }

      String var9 = String.format(
         "%s:textures/models/armor/%s_layer_%d%s.png", var7, var6, this.isSlotForLeggings(var3) ? 2 : 1, var4 == null ? "" : String.format("_%s", var4)
      );
      var9 = Reflector.callString(Reflector.ForgeHooksClient_getArmorTexture, var1, var2, var9, var3, var4);
      ResourceLocation var10 = ARMOR_TEXTURE_RES_MAP.get(var9);
      if (var10 == null) {
         var10 = new ResourceLocation(var9);
         ARMOR_TEXTURE_RES_MAP.put(var9, var10);
      }

      return var10;
   }

   public void renderGlint(EntityLivingBase var1, T var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9) {
      EnchantmentGlintModule var10 = CheatBreaker.getInstance().getModuleManager().recoveredField1714;
      if ((!Config.isShaders() || !Shaders.isShadowPass) && var10.isEnabled() && var10.recoveredField3402.method_08908()) {
         int var11 = var10.recoveredField3409.method_08901();
         float var12 = (var11 >> 24 & 0xFF) / 255.0F;
         float var13 = (var11 >> 16 & 0xFF) / 255.0F;
         float var14 = (var11 >> 8 & 0xFF) / 255.0F;
         float var15 = (var11 & 0xFF) / 255.0F;
         float var16 = var1.W + var5;
         this.renderer.a(ENCHANTED_ITEM_GLINT_RES);
         if (Config.isShaders()) {
            ShadersRender.method_06622();
         }

         GlStateManager.enableBlend();
         GlStateManager.depthFunc(514);
         GlStateManager.depthMask(false);
         GlStateManager.color(var13, var14, var15, var12);

         for (int var17 = 0; var17 < 2; var17++) {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(768, 1);
            GlStateManager.color(var13, var14, var15, var12);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float var18 = 0.33333334F;
            GlStateManager.scale(var18, var18, var18);
            GlStateManager.rotate(30.0F - var17 * 60.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translate(0.0F, var16 * (0.001F + var17 * 0.003F) * 20.0F, 0.0F);
            GlStateManager.matrixMode(5888);
            var2.render(var1, var3, var4, var6, var7, var8, var9);
         }

         GlStateManager.matrixMode(5890);
         GlStateManager.loadIdentity();
         GlStateManager.matrixMode(5888);
         GlStateManager.enableLighting();
         GlStateManager.depthMask(true);
         GlStateManager.depthFunc(515);
         GlStateManager.disableBlend();
         if (Config.isShaders()) {
            ShadersRender.renderEnchantedGlintEnd();
         }
      }
   }

   public ItemStack getCurrentArmor(EntityLivingBase var1, int var2) {
      return var1.getCurrentArmor(var2 - 1);
   }

   @Override
   public void doRenderLayer(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.renderLayer(var1, var2, var3, var4, var5, var6, var7, var8, 4);
      this.renderLayer(var1, var2, var3, var4, var5, var6, var7, var8, 3);
      this.renderLayer(var1, var2, var3, var4, var5, var6, var7, var8, 2);
      this.renderLayer(var1, var2, var3, var4, var5, var6, var7, var8, 1);
   }

   public void renderLayer(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, int var9) {
      ItemStack var10 = this.getCurrentArmor(var1, var9);
      if (var10 != null && var10.getItem() instanceof ItemArmor) {
         ItemArmor var11 = (ItemArmor)var10.getItem();
         ModelBase var12 = this.getArmorModel(var9);
         var12.a(this.renderer.getMainModel());
         var12.setLivingAnimations(var1, var2, var3, var4);
         if (Reflector.ForgeHooksClient.exists()) {
            var12 = this.getArmorModelHook(var1, var10, var9, (T)var12);
         }

         this.setModelPartVisible((T)var12, var9);
         boolean var13 = this.isSlotForLeggings(var9);
         if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(var10, var13 ? 2 : 1, (String)null)) {
            if (Reflector.ForgeHooksClient_getArmorTexture.exists()) {
               this.renderer.a(this.getArmorResource(var1, var10, var13 ? 2 : 1, (String)null));
            } else {
               this.renderer.a(this.getArmorResource(var11, var13));
            }
         }

         if (Reflector.ForgeHooksClient_getArmorTexture.exists()) {
            if (ReflectorForge.armorHasOverlay(var11, var10)) {
               int var18 = var11.getColor(var10);
               float var19 = (var18 >> 16 & 0xFF) / 255.0F;
               float var20 = (var18 >> 8 & 0xFF) / 255.0F;
               float var21 = (var18 & 0xFF) / 255.0F;
               GlStateManager.color(this.colorR * var19, this.colorG * var20, this.colorB * var21, this.alpha);
               var12.render(var1, var2, var3, var5, var6, var7, var8);
               if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(var10, var13 ? 2 : 1, "overlay")) {
                  this.renderer.a(this.getArmorResource(var1, var10, var13 ? 2 : 1, "overlay"));
               }
            }

            GlStateManager.color(this.colorR, this.colorG, this.colorB, this.alpha);
            var12.render(var1, var2, var3, var5, var6, var7, var8);
            if (!this.skipRenderGlint
               && var10.hasEffect()
               && (!Config.isCustomItems() || !CustomItems.renderCustomArmorEffect(var1, var10, var12, var2, var3, var4, var5, var6, var7, var8))) {
               this.renderGlint(var1, (T)var12, var2, var3, var4, var5, var6, var7, var8);
            }

            return;
         }

         switch (var11.getArmorMaterial()) {
            case LEATHER:
               int var14 = var11.getColor(var10);
               float var15 = (var14 >> 16 & 0xFF) / 255.0F;
               float var16 = (var14 >> 8 & 0xFF) / 255.0F;
               float var17 = (var14 & 0xFF) / 255.0F;
               GlStateManager.color(this.colorR * var15, this.colorG * var16, this.colorB * var17, this.alpha);
               var12.render(var1, var2, var3, var5, var6, var7, var8);
               if (!Config.isCustomItems() || !CustomItems.bindCustomArmorTexture(var10, var13 ? 2 : 1, "overlay")) {
                  this.renderer.a(this.getArmorResource(var11, var13, "overlay"));
               }
            case CHAIN:
            case IRON:
            case GOLD:
            case DIAMOND:
               GlStateManager.color(this.colorR, this.colorG, this.colorB, this.alpha);
               var12.render(var1, var2, var3, var5, var6, var7, var8);
         }

         if (!this.skipRenderGlint
            && var10.method_27847()
            && (!Config.isCustomItems() || !CustomItems.renderCustomArmorEffect(var1, var10, var12, var2, var3, var4, var5, var6, var7, var8))) {
            this.renderGlint(var1, (T)var12, var2, var3, var4, var5, var6, var7, var8);
         }
      }
   }

   @Override
   public boolean shouldCombineTextures() {
      return CheatBreaker.getInstance().getModuleManager().recoveredField1731.recoveredField3678.method_08908()
         && CheatBreaker.getInstance().getModuleManager().recoveredField1731.isEnabled();
   }

   public ResourceLocation getArmorResource(ItemArmor var1, boolean var2) {
      return this.getArmorResource(var1, var2, (String)null);
   }
}
