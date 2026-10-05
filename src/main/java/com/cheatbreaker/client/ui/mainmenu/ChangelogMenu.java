package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.mainmenu.NewsPreviewElement;

public class ChangelogMenu extends MainMenuBase {
   public List<NewsPreviewElement> recoveredField2864 = new ArrayList<>();
   public static JsonObject recoveredField2865;
   public ScrollableElement recoveredField2866;
   public GradientTextButton recoveredField2867 = new GradientTextButton("BACK");

   @Override
   public void initGui() {
      super.initGui();
      float var1 = Math.min(240.0F, this.getScaledWidth() - 10.0F);
      float var2 = this.getScaledWidth() / 2.0F - var1 / 2.0F;
      float var3 = this.getScaledWidth() / 2.0F + var1 / 2.0F;
      float var4 = 40.0F;
      float var5 = this.getScaledHeight() - 40.0F;
      this.recoveredField2866.setElementSize(var3 - 8.0F, var4 + 18.0F, 4.0F, var5 - var4 - 28.0F);
      int var6 = 0;
      float var7 = 22.0F;

      for (NewsPreviewElement var9 : this.recoveredField2864) {
         var9.setElementSize(var2 + 8.0F, var4 + 20.0F + var6 * (var7 + 1.0F), var1 - 16.0F, var7);
         var6++;
      }

      this.recoveredField2866.setScrollAmount(this.recoveredField2864.size() * (var7 + 1.0F) + 2.0F);
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.recoveredField2866.handleElementMouse();
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      super.onMouseClicked(var1, var2, var3);
      if (this.recoveredField2867.a_(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new MainMenu());
      } else if (this.recoveredField2866.a_(var1, var2)) {
         this.recoveredField2866.handleElementMouseClicked(var1, var2, var3, true);
      } else {
         float var4 = Math.min(240.0F, this.getScaledWidth() - 10.0F);
         float var5 = this.getScaledWidth() / 2.0F - var4 / 2.0F;
         float var6 = this.getScaledWidth() / 2.0F + var4 / 2.0F;
         float var7 = 40.0F;
         float var8 = this.getScaledHeight() - 40.0F;
         if (var1 > var5 && var1 < var6 && var2 > var7 && var2 < var8) {
            for (NewsPreviewElement var10 : this.recoveredField2864) {
               var10.handleElementMouseClicked(
                  var1,
                  var2 - this.recoveredField2866.method_12074(),
                  var3,
                  var10.a_(var1, var2 - this.recoveredField2866.method_12074()) && !this.recoveredField2866.isDragClick()
               );
            }
         }
      }
   }

   @Override
   public void drawMenu(float var1, float var2) {
      super.drawMenu(var1, var2);
      float var3 = Math.min(240.0F, this.getScaledWidth() - 10.0F);
      float var4 = this.getScaledWidth() / 2.0F - var3 / 2.0F;
      float var5 = this.getScaledWidth() / 2.0F + var3 / 2.0F;
      float var6 = 40.0F;
      float var7 = this.getScaledHeight() - 40.0F;
      Gui.drawRect(var4, var6, var5, var7, 788529152);
      Gui.drawRect(var4 + 8.0F, var6 + 16.0F, var5 - 8.0F, var6 + 16.5F, 452984831);
      CheatBreaker.getInstance().recoveredField1548.drawString("CHANGELOG", var4 + 8.0F, var6 + 5.0F, -1);
      if (this.recoveredField2864.size() < 1) {
         CheatBreaker.getInstance()
            .recoveredField1548
            .drawCenteredString("This branch does not contain any changes.", this.getScaledWidth() / 2.0F, this.getScaledHeight() / 2.0F + 4.0F, -6381922);
      }

      this.recoveredField2867.setElementSize(this.getScaledWidth() / 2.0F - 30.0F, this.getScaledHeight() - 35.0F, 60.0F, 12.0F);
      this.recoveredField2867.drawElement(var1, var2, true);
      this.recoveredField2866.drawScrollable(var1, var2, true);
      GL11.glPushMatrix();
      GL11.glEnable(3089);
      RenderUtil.method_22061(
         (int)var4, (int)var6 + 18, (int)var5, (int)var7 - 8, (int)(this.getResolution().getScaleFactor() * this.getScaleFactor()), (int)this.getScaledHeight()
      );

      for (NewsPreviewElement var9 : this.recoveredField2864) {
         var9.drawElement(var1, var2 - this.recoveredField2866.method_12074(), !this.recoveredField2866.isDragClick());
      }

      GL11.glDisable(3089);
      GL11.glPopMatrix();
      this.recoveredField2866.drawElement(var1, var2, true);
   }

   public void method_10615() {
      try {
         String var1 = CheatBreaker.getInstance().method_19798();
         if (recoveredField2865 != null) {
            if (recoveredField2865.toString().contains("\"?\"") && CheatBreaker.getInstance().method_19798().equalsIgnoreCase("dev")) {
               var1 = "?";
            } else if (recoveredField2865 == null || !recoveredField2865.toString().contains("\"" + CheatBreaker.getInstance().method_19798() + "\"")) {
               return;
            }

            if (recoveredField2865.getAsJsonObject(var1) != null) {
               for (Entry var4 : recoveredField2865.getAsJsonObject(var1).entrySet()) {
                  JsonObject var5 = ((JsonElement)var4.getValue()).getAsJsonObject();
                  String var6 = (String)var4.getKey();
                  String var7 = var5.get("author").getAsString();
                  String var8 = var5.get("description").getAsString();
                  this.recoveredField2864.add(new NewsPreviewElement(var6, var7, var8));
               }
            }
         }
      } catch (Throwable var9) {
         throw var9;
      }
   }

   public ChangelogMenu() {
      this.recoveredField2866 = new ScrollableElement(null);
      this.method_10615();
   }
}
