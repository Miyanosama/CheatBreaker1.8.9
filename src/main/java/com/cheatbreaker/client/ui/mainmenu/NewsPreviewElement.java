package com.cheatbreaker.client.ui.mainmenu;

import org.davidmoten.text.utils.WordWrap;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.mainmenu.NewsMenu;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class NewsPreviewElement extends AbstractElement {
   public String recoveredField1245;
   public String recoveredField1246;
   public String recoveredField1247;
   public ColorFade recoveredField1248 = new ColorFade(520093696, 1056964608);

   public NewsPreviewElement(String var1, String var2, String var3) {
      this.recoveredField1247 = var1;
      this.recoveredField1245 = var2;
      this.recoveredField1246 = var3;
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.recoveredField1248.method_25066(this.a_(var1, var2) && var3).getRGB());
      String var4 = WordWrap.method_05899(this.recoveredField1246).method_29697(60).method_29703(false).method_29693();
      String[] var5 = var4.split("\n");
      int var6 = 2;
      float var7 = CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F;
      CheatBreaker.getInstance().playRegular14px.drawString(EnumChatFormatting.BOLD + this.recoveredField1247, 1.0F + this.x, this.y, -1);
      CheatBreaker.getInstance()
         .recoveredField1554
         .drawString(EnumChatFormatting.ITALIC + "Posted by " + EnumChatFormatting.BOLD + this.recoveredField1245, 1.0F + this.x, this.y + var7, -1);

      for (String var11 : var5) {
         if (var6 < 3) {
            if (var6 == 2) {
               var11 = var11 + "...";
            }

            float var10002 = 1.0F + this.x;
            CheatBreaker.getInstance().playRegular14px.drawString(var11, var10002, this.y + var6 * var7 - 1.0F, -1);
         }

         var6++;
      }
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (this.a_(var1, var2)) {
         this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.mc.displayGuiScreen(new NewsMenu(this.recoveredField1247, this.recoveredField1245, this.recoveredField1246));
      }

      return false;
   }
}
