package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiWinGame extends GuiScreen {
   public static Logger logger = LogManager.getLogger();
   public static ResourceLocation MINECRAFT_LOGO = new ResourceLocation("textures/gui/title/minecraft.png");
   public static ResourceLocation VIGNETTE_TEXTURE = new ResourceLocation("textures/misc/vignette.png");
   public List<String> field_146582_i;
   public int field_146581_h;
   public int field_146579_r;
   public float field_146578_s = 0.5F;

   public void method_24260(int var1, int var2, float var3) {
      Tessellator var4 = Tessellator.getInstance();
      WorldRenderer var5 = var4.getWorldRenderer();
      this.j.getTextureManager().bindTexture(Gui.b);
      var5.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      int var6 = this.l;
      float var7 = 0.0F - (this.field_146581_h + var3) * 0.5F * this.field_146578_s;
      float var8 = this.m - (this.field_146581_h + var3) * 0.5F * this.field_146578_s;
      float var9 = 0.015625F;
      float var10 = (this.field_146581_h + var3 - 0.0F) * 0.02F;
      float var11 = (this.field_146579_r + this.m + this.m + 24) / this.field_146578_s;
      float var12 = (var11 - 20.0F - (this.field_146581_h + var3)) * 0.005F;
      if (var12 < var10) {
         var10 = var12;
      }

      if (var10 > 1.0F) {
         var10 = 1.0F;
      }

      var10 *= var10;
      var10 = var10 * 96.0F / 255.0F;
      var5.pos(0.0, this.m, recoveredField2942).tex(0.0, var7 * var9).color(var10, var10, var10, 1.0F).endVertex();
      var5.pos(var6, this.m, recoveredField2942).tex(var6 * var9, var7 * var9).color(var10, var10, var10, 1.0F).endVertex();
      var5.pos(var6, 0.0, recoveredField2942).tex(var6 * var9, var8 * var9).color(var10, var10, var10, 1.0F).endVertex();
      var5.pos(0.0, 0.0, recoveredField2942).tex(0.0, var8 * var9).color(var10, var10, var10, 1.0F).endVertex();
      var4.draw();
   }

   @Override
   public void initGui() {
      if (this.field_146582_i == null) {
         this.field_146582_i = Lists.newArrayList();

         try {
            String var1 = "";
            String var2 = "" + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + EnumChatFormatting.GREEN + EnumChatFormatting.AQUA;
            short var3 = 274;
            InputStream var4 = this.j.getResourceManager().getResource(new ResourceLocation("texts/end.txt")).getInputStream();
            BufferedReader var5 = new BufferedReader(new InputStreamReader(var4, Charsets.UTF_8));
            Random var6 = new Random(8124371L);

            while ((var1 = var5.readLine()) != null) {
               var1 = var1.replaceAll("PLAYERNAME", this.j.getSession().getUsername());

               while (var1.contains(var2)) {
                  int var9 = var1.indexOf(var2);
                  java.lang.String var7 = var1.substring(0, var9);
                  String var8 = var1.substring(var9 + var2.length());
                  var1 = var7 + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + "XXXXXXXX".substring(0, var6.nextInt(4) + 3) + var8;
               }

               this.field_146582_i.addAll(this.j.fontRendererObj.listFormattedStringToWidth(var1, var3));
               this.field_146582_i.add("");
            }

            var4.close();

            for (int var18 = 0; var18 < 8; var18++) {
               this.field_146582_i.add("");
            }

            var4 = this.j.getResourceManager().getResource(new ResourceLocation("texts/credits.txt")).getInputStream();
            var5 = new BufferedReader(new InputStreamReader(var4, Charsets.UTF_8));

            while ((var1 = var5.readLine()) != null) {
               var1 = var1.replaceAll("PLAYERNAME", this.j.getSession().getUsername());
               var1 = var1.replaceAll("\t", "    ");
               this.field_146582_i.addAll(this.j.fontRendererObj.listFormattedStringToWidth(var1, var3));
               this.field_146582_i.add("");
            }

            var4.close();
            this.field_146579_r = this.field_146582_i.size() * 12;
         } catch (Exception var10) {
            logger.error("Couldn't load credits", var10);
         }
      }
   }

   public void sendRespawnPacket() {
      this.j.thePlayer.sendQueue.addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
      this.j.displayGuiScreen((GuiScreen)null);
   }

   @Override
   public boolean b_() {
      return true;
   }

   @Override
   public void updateScreen() {
      MusicTicker var1 = this.j.getMusicTicker();
      SoundHandler var2 = this.j.getSoundHandler();
      if (this.field_146581_h == 0) {
         var1.func_181557_a();
         var1.func_181558_a(MusicTicker.MusicType.CREDITS);
         var2.resumeSounds();
      }

      var2.update();
      this.field_146581_h++;
      float var3 = (this.field_146579_r + this.m + this.m + 24) / this.field_146578_s;
      if (this.field_146581_h > var3) {
         this.sendRespawnPacket();
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.method_24260(var1, var2, var3);
      Tessellator var4 = Tessellator.getInstance();
      WorldRenderer var5 = var4.getWorldRenderer();
      short var6 = 274;
      int var7 = this.l / 2 - var6 / 2;
      int var8 = this.m + 50;
      float var9 = -(this.field_146581_h + var3) * this.field_146578_s;
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, var9, 0.0F);
      this.j.getTextureManager().bindTexture(MINECRAFT_LOGO);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawTexturedModalRect(var7, var8, 0, 0, 155, 44);
      this.drawTexturedModalRect(var7 + 155, var8, 0, 45, 155, 44);
      int var10 = var8 + 200;

      for (int var11 = 0; var11 < this.field_146582_i.size(); var11++) {
         if (var11 == this.field_146582_i.size() - 1) {
            float var12 = var10 + var9 - (this.m / 2 - 6);
            if (var12 < 0.0F) {
               GlStateManager.translate(0.0F, -var12, 0.0F);
            }
         }

         if (var10 + var9 + 12.0F + 8.0F > 0.0F && var10 + var9 < this.m) {
            String var14 = this.field_146582_i.get(var11);
            if (var14.startsWith("[C]")) {
               this.q.drawStringWithShadow(var14.substring(3), var7 + (var6 - this.q.getStringWidth(var14.substring(3))) / 2, var10, 16777215);
            } else {
               this.q.fontRandom.setSeed(var11 * 4238972211L + this.field_146581_h / 4);
               this.q.drawString(var14, var7, var10, 16777215);
            }
         }

         var10 += 12;
      }

      GlStateManager.popMatrix();
      this.j.getTextureManager().bindTexture(VIGNETTE_TEXTURE);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(0, 769);
      int var13 = this.l;
      int var15 = this.m;
      var5.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      var5.pos(0.0, var15, recoveredField2942).tex(0.0, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(var13, var15, recoveredField2942).tex(1.0, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(var13, 0.0, recoveredField2942).tex(1.0, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(0.0, 0.0, recoveredField2942).tex(0.0, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var4.draw();
      GlStateManager.disableBlend();
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      if (var2 == 1) {
         this.sendRespawnPacket();
      }
   }
}
