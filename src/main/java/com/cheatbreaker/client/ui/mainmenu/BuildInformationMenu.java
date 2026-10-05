package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.davidmoten.text.utils.WordWrap;

public class BuildInformationMenu extends MainMenuBase {
   public GradientTextButton recoveredField987 = new GradientTextButton("BACK");

   @Override
   public void drawMenu(float var1, float var2) {
      super.drawMenu(var1, var2);
      Gui.drawRect(
         this.getScaledWidth() / 2.0F - 80.0F,
         this.getScaledHeight() / 2.0F - 40.0F,
         this.getScaledWidth() / 2.0F + 80.0F,
         this.getScaledHeight() / 2.0F + 50.0F,
         788529152
      );
      this.recoveredField987.drawElement(var1, var2, true);
      String var3 = "";
      String var4 = CheatBreaker.getInstance().method_19784();
      switch (var4) {
         case "Dev":
            var3 = "Development builds are not intended for bug testing purposes; however, expect bugs to appear.";
            break;
         case "Beta":
            var3 = "Beta builds are used to test for potential issues. Expect bugs to occur over time.";
            break;
         case "Preview":
            var3 = "Preview builds are intended to become the next stable build. Bugs should be less prominent.";
      }

      var4 = CheatBreaker.getInstance().method_19784().isEmpty() ? "Production" : CheatBreaker.getInstance().method_19784();
      String var13 = WordWrap.method_05899(var3).method_29697(40).method_29703(false).method_29693();
      String[] var6 = var13.split("\n");
      int var7 = 0;

      for (String var11 : var6) {
         CheatBreaker.getInstance()
            .playRegular14px
            .drawCenteredString(
               var11,
               this.getScaledWidth() / 2.0F,
               this.getScaledHeight() / 2.0F + 14.0F + var7 * CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F,
               -2236963
            );
         var7++;
      }

      CheatBreaker.getInstance()
         .recoveredField1548
         .drawCenteredString(
            "Commit: " + CheatBreaker.getInstance().method_19754() + "/" + CheatBreaker.getInstance().method_19798(),
            this.getScaledWidth() / 2.0F,
            this.getScaledHeight() / 2.0F + 14.0F - var7 * (CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F),
            -1118482
         );
      CheatBreaker.getInstance()
         .recoveredField1548
         .drawCenteredString(
            "Minecraft Version: 1.8.9",
            this.getScaledWidth() / 2.0F,
            this.getScaledHeight() / 2.0F - 6.0F - var7 * (CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F),
            -1118482
         );
      CheatBreaker.getInstance()
         .recoveredField1548
         .drawCenteredString(
            "Build Version: " + var4,
            this.getScaledWidth() / 2.0F,
            this.getScaledHeight() / 2.0F + 4.0F - var7 * (CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F),
            -1118482
         );
   }

   @Override
   public void initGui() {
      super.initGui();
      this.recoveredField987.setElementSize(this.getScaledWidth() / 2.0F - 30.0F, this.getScaledHeight() / 2.0F + 55.0F, 60.0F, 12.0F);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      super.onMouseClicked(var1, var2, var3);
      if (this.recoveredField987.a_(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new MainMenu());
      }
   }
}
