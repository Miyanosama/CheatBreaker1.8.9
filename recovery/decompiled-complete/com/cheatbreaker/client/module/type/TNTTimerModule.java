package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import java.awt.Color;
import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass3697;

public class TNTTimerModule extends AbstractModule {
   public boolean field_0002;
   public ClickEvent field_0003;
   public Setting field_0000;
   public Setting field_0001;
   public Setting field_0005;
   public Setting field_0004;
   public Setting field_0006;

   public String method_23092(int var1, float var2) {
      return new DecimalFormat("0.00").format((var1 - var2) / 20.0F);
   }

   public Color method_23091(int var1) {
      return this.field_0005.method_08908()
         ? new Color(1.0F - Math.min(var1 / (this.field_0002 ? 52.0F : 80.0F), 1.0F), Math.min(var1 / (this.field_0002 ? 52.0F : 80.0F), 1.0F), 0.0F)
         : this.field_0000.method_08913(this.field_0000.method_08901());
   }

   public void method_23093(UnidentifiedClass3697 var1, EntityTNTPrimed var2, double var3, double var5, double var7, float var9) {
      if (Minecraft.getMinecraft().currentServerData != null && Minecraft.getMinecraft().theWorld != null) {
         this.field_0002 = Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
      }

      float var10 = 0.02666667F;
      int var11 = this.field_0002 ? var2.fuse - 28 : var2.fuse;
      if (var11 >= 1 && var2.h(var1.getRenderManager().livingPlayer) <= 4095.0) {
         FontRenderer var12 = var1.c();
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)var3 + 0.0F, (float)var5 + var2.K + 0.5F, (float)var7);
         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(-var1.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(
            var1.getRenderManager().playerViewX * (Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? -1.0F : 1.0F), 1.0F, 0.0F, 0.0F
         );
         GlStateManager.scale(-var10, -var10, var10);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.disableDepth();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         Tessellator var13 = Tessellator.getInstance();
         WorldRenderer var14 = var13.getWorldRenderer();
         GlStateManager.enableDepth();
         GlStateManager.depthMask(true);
         GlStateManager.disableTexture2D();
         int var15 = var12.getStringWidth(this.method_23092(var11, var9)) >> 1;
         float var16 = (this.field_0001.method_08901() >> 24 & 0xFF) / 255.0F;
         float var17 = (this.field_0001.method_08901() >> 16 & 0xFF) / 255.0F;
         float var18 = (this.field_0001.method_08901() >> 8 & 0xFF) / 255.0F;
         float var19 = (this.field_0001.method_08901() & 0xFF) / 255.0F;
         if (this.field_0004.method_08908()) {
            var14.begin(7, DefaultVertexFormats.POSITION_COLOR);
            var14.pos(-var15 - 1, -1.0, 0.0).color(var17, var18, var19, var16).endVertex();
            var14.pos(-var15 - 1, 8.0, 0.0).color(var17, var18, var19, var16).endVertex();
            var14.pos(var15 + 1, 8.0, 0.0).color(var17, var18, var19, var16).endVertex();
            var14.pos(var15 + 1, -1.0, 0.0).color(var17, var18, var19, var16).endVertex();
            var13.draw();
         }

         GlStateManager.enableTexture2D();
         var12.drawString(
            this.method_23092(var11, var9),
            -var12.getStringWidth(this.method_23092(var11, var9)) >> 1,
            0.0F,
            this.method_23091(var11).getRGB(),
            this.field_0006.method_08908()
         );
         GlStateManager.enableLighting();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.popMatrix();
      }
   }

   public TNTTimerModule() {
      super("TNT Timer");
      this.setDefaultState(false);
      this.method_28821("Shows a timer above TNT.");
      this.method_28829("Sk1er");
      this.method_28807("TNT Countdown");
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/tnt.png"), 36, 40);
      new Setting(this, "label").setValue("Text Options");
      this.field_0006 = new Setting(this, "Text Shadow").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0005 = new Setting(this, "Dynamic Text Color").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0000 = new Setting(this, "Static Text Color")
         .setValue(-1)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> !this.field_0005.method_08908());
      new Setting(this, "label").setValue("Background Options");
      this.field_0004 = new Setting(this, "Show Background").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0001 = new Setting(this, "Background Color")
         .setValue(1862270976)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0004.method_08908());
   }
}
