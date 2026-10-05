package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.command.CommandSetSpawnpoint;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.CustomEntityRenderer;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass3255 extends GuiScreen {
   public static ResourceLocation field_0004 = new ResourceLocation("textures/gui/title/minecraft.png");
   public float field_0007 = 0.5F;
   public int field_0003;
   public GuiCreateFlatWorld field_0006;
   public CustomEntityRenderer field_0000;
   public List field_0001;
   public static ResourceLocation field_0008 = new ResourceLocation("textures/misc/vignette.png");
   public int field_0005;
   public CommandSetSpawnpoint field_0002;
   public static Logger field_0009 = LogManager.getLogger();

   public void method_20230() {
      this.j.displayGuiScreen(CheatBreaker.getInstance().field_0020);
   }

   public void method_20231(int var1, int var2, float var3) {
      Tessellator var4 = Tessellator.getInstance();
      WorldRenderer var5 = var4.getWorldRenderer();
      this.j.getTextureManager().bindTexture(Gui.b);
      var5.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      int var6 = this.l;
      float var7 = 0.0F - (this.field_0003 + var3) * 0.5F * 0.5F;
      float var8 = this.m - (this.field_0003 + var3) * 0.5F * 0.5F;
      float var9 = 0.015625F;
      float var10 = (this.field_0003 + var3 - 0.0F) * 0.02F;
      float var11 = (this.field_0005 + this.m + this.m + 24) / 0.5F;
      float var12 = (var11 - 20.0F - (this.field_0003 + var3)) * 0.005F;
      if (var12 < var10) {
         var10 = var12;
      }

      if (var10 > 1.0F) {
         var10 = 1.0F;
      }

      var10 *= var10;
      var10 = var10 * 96.0F / 255.0F;
      var5.pos(0.0, this.m, field_0003).tex(0.0, var7 * var9).color(var10, var10, var10, 1.0F).endVertex();
      var5.pos(var6, this.m, field_0003).tex(var6 * var9, var7 * var9).color(var10, var10, var10, 1.0F).endVertex();
      var5.pos(var6, 0.0, field_0003).tex(var6 * var9, var8 * var9).color(var10, var10, var10, 1.0F).endVertex();
      var5.pos(0.0, 0.0, field_0003).tex(0.0, var8 * var9).color(var10, var10, var10, 1.0F).endVertex();
      var4.draw();
   }

   @Override
   public void updateScreen() {
      this.field_0003++;
      float var1 = (this.field_0005 + this.m + this.m + 24) / 0.5F;
      if (this.field_0003 > var1) {
         this.method_20230();
      }
   }

   @Override
   public void initGui() {
      if (this.field_0001 == null) {
         this.field_0001 = new ArrayList();

         try {
            short var2 = 274;
            BufferedReader var3 = new BufferedReader(
               new InputStreamReader(this.j.getResourceManager().getResource(new ResourceLocation("texts/credits.txt")).getInputStream(), Charsets.UTF_8)
            );

            String var1;
            while ((var1 = var3.readLine()) != null) {
               var1 = var1.replaceAll("PLAYERNAME", this.j.getSession().getUsername());
               var1 = var1.replaceAll("\t", "    ");
               this.field_0001.addAll(this.j.fontRendererObj.listFormattedStringToWidth(var1, var2));
               this.field_0001.add("");
            }

            this.field_0005 = this.field_0001.size() * 12;
         } catch (Exception var4) {
            field_0009.error("Couldn't load credits", var4);
         }
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.method_20231(var1, var2, var3);
      Tessellator var4 = Tessellator.getInstance();
      WorldRenderer var5 = var4.getWorldRenderer();
      short var6 = 274;
      int var7 = this.l / 2 - var6 / 2;
      int var8 = this.m + 50;
      float var9 = -(this.field_0003 + var3) * 0.5F;
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, var9, 0.0F);
      this.j.getTextureManager().bindTexture(field_0004);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3008);
      this.drawTexturedModalRect(var7, var8, 0, 0, 155, 44);
      this.drawTexturedModalRect(var7 + 155, var8, 0, 45, 155, 44);
      GL11.glDisable(3008);
      int var10 = var8 + 200;

      for (int var11 = 0; var11 < this.field_0001.size(); var11++) {
         if (var11 == this.field_0001.size() - 1) {
            float var12 = var10 + var9 - (this.m / 2 - 6);
            if (var12 < 0.0F) {
               GL11.glTranslatef(0.0F, -var12, 0.0F);
            }
         }

         if (var10 + var9 + 12.0F + 8.0F > 0.0F && var10 + var9 < this.m) {
            String var14 = (String)this.field_0001.get(var11);
            if (var14.startsWith("[C]")) {
               this.q.drawStringWithShadow(var14.substring(3), var7 + (var6 - this.q.getStringWidth(var14.substring(3))) / 2, var10, 16777215);
            } else {
               this.q.fontRandom.setSeed(var11 * (-2556200389575076553L & 4256151347L) + this.field_0003 / 4);
               this.q.drawStringWithShadow(var14, var7, var10, 16777215);
            }
         }

         var10 += 12;
      }

      GL11.glPopMatrix();
      this.j.getTextureManager().bindTexture(field_0008);
      GL11.glEnable(3042);
      GL11.glBlendFunc(0, 769);
      int var15 = this.l;
      int var13 = this.m;
      var5.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      var5.pos(0.0, var13, field_0003).tex(0.0, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(var15, var13, field_0003).tex(1.0, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(var15, 0.0, field_0003).tex(1.0, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(0.0, 0.0, field_0003).tex(0.0, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var4.draw();
      GL11.glDisable(3042);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void keyTyped(char var1, int var2) {
      if (var2 == 1) {
         this.method_20230();
      }
   }
}
