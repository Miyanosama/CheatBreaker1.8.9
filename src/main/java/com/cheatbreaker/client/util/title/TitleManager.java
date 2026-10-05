package com.cheatbreaker.client.util.title;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.util.title.Title;
import com.google.common.collect.Lists;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class TitleManager {
   public List<Title> recoveredField2353;
   public Minecraft recoveredField2354 = Minecraft.getMinecraft();

   public void method_07639(TickEvent var1) {
      if (!this.recoveredField2353.isEmpty()) {
         this.recoveredField2353.removeIf(var0 -> var0.currentTimeMillis + var0.getDisplayTimeMs() < System.currentTimeMillis());
      }
   }

   public void method_07640(GuiDrawEvent var1) {
      GL11.glEnable(3042);

      for (Title var3 : this.recoveredField2353) {
         this.recoveredField2354.recoveredField3820.method_25825();
         boolean var4 = var3.getTitleEnum() == Title.TitleType.SUBTITLE;
         float var5 = var4 ? 4.0F : 1.5F;
         float var6 = var4 ? -30.0F : 10.0F;
         float var10;
         GL11.glScalef(var10 = var5 * var3.getScale(), var10, var10);
         float var7 = 255.0F;
         if (var3.method_26175()) {
            long var8 = var3.getFadeInTimeMs() - (System.currentTimeMillis() - var3.currentTimeMillis);
            var7 = 1.0F - (float)var8 / (float)var3.getFadeInTimeMs();
         } else if (var3.method_26170()) {
            long var12 = var3.getDisplayTimeMs() - (System.currentTimeMillis() - var3.currentTimeMillis);
            var7 = (float)var12 <= 0.0F ? 0.0F : (float)var12 / (float)var3.getFadeOutTimeMs();
         }

         var7 = Math.min(1.0F, Math.max(0.0F, var7));
         if (var7 <= 0.15) {
            var7 = 0.15F;
         }

         this.recoveredField2354
            .fontRendererObj
            .method_08776(
               var3.getMessage(),
               (int)(var1.getResolution().getScaledWidth() / 2 / var10),
               (int)((var1.getResolution().getScaledHeight() / 2 - this.recoveredField2354.fontRendererObj.FONT_HEIGHT / 2 + var6) / var10),
               new Color(1.0F, 1.0F, 1.0F, var7).getRGB()
            );
         GL11.glScalef(1.0F / var10, 1.0F / var10, 1.0F / var10);
      }

      GL11.glDisable(3042);
   }

   public TitleManager() {
      this.recoveredField2353 = Lists.newArrayList();
      CheatBreaker.getInstance().recoveredField1579.info(CheatBreaker.getInstance().recoveredField1553 + "Created Title Manager");
   }

   public List<Title> method_07638() {
      return this.recoveredField2353;
   }
}
