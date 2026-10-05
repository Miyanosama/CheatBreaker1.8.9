package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil$TransferEncodingMechanism;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.particle.EntityCrit2FX$Factory;
import net.minecraft.stats.StatBasic;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.ResourceLocation;
import net.optifine.util.NativeMemory;
import recovered.unidentified.UnidentifiedClass0882;

public class BuildInformationMenu extends MainMenuBase {
   public GradientTextButton field_0004 = new GradientTextButton("BACK");
   public NativeMemory field_0003;
   public EnumTypeAdapterFactory field_0002;
   public EntityCrit2FX$Factory field_0001;
   public StatBasic field_0000;
   public HttpPostBodyUtil$TransferEncodingMechanism field_0005;

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
      this.field_0004.drawElement(var1, var2, true);
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
      String var13 = UnidentifiedClass0882.method_05899(var3).method_29697(40).method_29703(false).method_29693();
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
         .field_0036
         .drawCenteredString(
            "Commit: " + CheatBreaker.getInstance().method_19754() + "/" + CheatBreaker.getInstance().method_19798(),
            this.getScaledWidth() / 2.0F,
            this.getScaledHeight() / 2.0F + 14.0F - var7 * (CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F),
            -1118482
         );
      CheatBreaker.getInstance()
         .field_0036
         .drawCenteredString(
            "Minecraft Version: 1.8.9",
            this.getScaledWidth() / 2.0F,
            this.getScaledHeight() / 2.0F - 6.0F - var7 * (CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F),
            -1118482
         );
      CheatBreaker.getInstance()
         .field_0036
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
      this.field_0004.setElementSize(this.getScaledWidth() / 2.0F - 30.0F, this.getScaledHeight() / 2.0F + 55.0F, 60.0F, 12.0F);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      super.onMouseClicked(var1, var2, var3);
      if (this.field_0004.a_(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new MainMenu());
      }
   }
}
