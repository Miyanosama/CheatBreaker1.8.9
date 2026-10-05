package net.minecraft.client.gui;

import net.minecraft.block.BlockRailPowered$2;
import net.minecraft.entity.monster.EntitySilverfish$AISummonSilverfish;
import net.minecraft.item.ItemAnvilBlock;
import net.minecraft.util.IProgressUpdate;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;

public class GuiScreenWorking extends GuiScreen implements IProgressUpdate {
   public String field_146591_a = "";
   public BlockRailPowered$2 field_0006;
   public CustomLoadingScreen customLoadingScreen;
   public boolean doneWorking;
   public EntitySilverfish$AISummonSilverfish field_0000;
   public int progress;
   public String field_146589_f = "";
   public ItemAnvilBlock field_0004;

   @Override
   public void resetProgressAndMessage(String var1) {
      this.field_146591_a = var1;
      this.displayLoadingString("Working...");
   }

   @Override
   public void displayLoadingString(String var1) {
      this.field_146589_f = var1;
      this.setLoadingProgress(0);
   }

   @Override
   public void setDoneWorking() {
      this.doneWorking = true;
   }

   @Override
   public void setLoadingProgress(int var1) {
      this.progress = var1;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      if (this.doneWorking) {
         if (!this.j.isConnectedToRealms()) {
            this.j.displayGuiScreen((GuiScreen)null);
         }
      } else {
         if (this.customLoadingScreen != null && this.j.theWorld == null) {
            this.customLoadingScreen.drawBackground(this.l, this.m);
         } else {
            this.drawDefaultBackground();
         }

         if (this.progress > 0) {
            this.drawCenteredString(this.q, this.field_146591_a, this.l / 2, 70, 16777215);
            this.drawCenteredString(this.q, this.field_146589_f + " " + this.progress + "%", this.l / 2, 90, 16777215);
         }

         super.drawScreen(var1, var2, var3);
      }
   }

   @Override
   public void displaySavingString(String var1) {
      this.resetProgressAndMessage(var1);
   }

   public GuiScreenWorking() {
      this.customLoadingScreen = CustomLoadingScreens.getCustomLoadingScreen();
   }
}
