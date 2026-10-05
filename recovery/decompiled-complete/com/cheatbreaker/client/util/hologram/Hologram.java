package com.cheatbreaker.client.util.hologram;

import io.netty.util.concurrent.AbstractEventExecutor$1;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemFirework;
import net.minecraft.util.ChatComponentStyle$1;
import org.lwjgl.opengl.GL11;

public class Hologram {
   public ChatComponentStyle$1 field_0004;
   public AbstractEventExecutor$1 field_0007;
   public String[] field_0003;
   public double field_0006;
   public double field_0000;
   public UUID field_0001;
   public ItemFirework field_0008;
   public double field_0005;
   public static List<Hologram> holograms = new ArrayList<>();

   public UUID method_28149() {
      return this.field_0001;
   }

   public static List<Hologram> method_28152() {
      return holograms;
   }

   public double method_28148() {
      return this.field_0006;
   }

   public static void method_28151() {
      FontRenderer var0 = Minecraft.getMinecraft().fontRendererObj;
      RenderManager var1 = RenderManager.method_21288();

      for (Hologram var3 : holograms) {
         if (var3.method_28153() != null && var3.method_28153().length > 0) {
            for (int var4 = var3.method_28153().length - 1; var4 >= 0; var4--) {
               String var5 = var3.method_28153()[var3.method_28153().length - var4 - 1];
               float var6 = (float)(var3.method_28148() - (float)var1.method_21304());
               float var7 = (float)(var3.method_28147() + 1.0 + var4 * 0.25F - (float)var1.method_21286());
               float var8 = (float)(var3.method_28146() - (float)var1.method_21285());
               float var9 = 1.6F;
               float var10 = 0.016666668F * var9;
               GL11.glPushMatrix();
               GL11.glTranslatef(var6, var7, var8);
               GL11.glNormal3f(0.0F, 1.0F, 0.0F);
               GL11.glRotatef(-var1.playerViewY, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(var1.playerViewX, 1.0F, 0.0F, 0.0F);
               GL11.glScalef(-var10, -var10, var10);
               GL11.glDisable(2896);
               GL11.glDepthMask(false);
               GL11.glDisable(2929);
               GL11.glEnable(3042);
               OpenGlHelper.glBlendFunc(770, 771, 1, 0);
               Tessellator var11 = Tessellator.getInstance();
               WorldRenderer var12 = var11.getWorldRenderer();
               byte var13 = 0;
               GL11.glDisable(3553);
               var12.begin(7, DefaultVertexFormats.POSITION_COLOR);
               int var14 = var0.getStringWidth(var5) / 2;
               var12.pos(-var14 - 1, -1 + var13, 0.0).color(0.0F, 1.0F, 0.0F, 0.25F).endVertex();
               var12.pos(-var14 - 1, 8 + var13, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               var12.pos(var14 + 1, 8 + var13, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               var12.pos(var14 + 1, -1 + var13, 0.0).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
               var11.draw();
               GL11.glEnable(3553);
               var0.drawString(var5, -var0.getStringWidth(var5) / 2, var13, 553648127);
               GL11.glEnable(2929);
               GL11.glDepthMask(true);
               var0.drawString(var5, -var0.getStringWidth(var5) / 2, var13, -1);
               GL11.glEnable(2896);
               GL11.glDisable(3042);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glPopMatrix();
            }
         }
      }
   }

   public Hologram(UUID var1, double var2, double var4, double var6) {
      this.field_0001 = var1;
      this.field_0006 = var2;
      this.field_0005 = var4;
      this.field_0000 = var6;
   }

   public double method_28146() {
      return this.field_0000;
   }

   public double method_28147() {
      return this.field_0005;
   }

   public void method_28150(String[] var1) {
      this.field_0003 = var1;
   }

   public String[] method_28153() {
      return this.field_0003;
   }
}
