package net.minecraft.client.gui;

import io.netty.handler.traffic.AbstractTrafficShapingHandler$ReopenReadTimerTask;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;

public class GuiDownloadTerrain extends GuiScreen {
   public NetHandlerPlayClient netHandlerPlayClient;
   public CustomLoadingScreen customLoadingScreen = CustomLoadingScreens.getCustomLoadingScreen();
   public AbstractTrafficShapingHandler$ReopenReadTimerTask field_0001;
   public EntityAIAttackOnCollide field_0003;
   public int progress;

   @Override
   public void updateScreen() {
      this.progress++;
      if (this.progress % 20 == 0) {
         this.netHandlerPlayClient.addToSendQueue(new C00PacketKeepAlive());
      }
   }

   @Override
   public void initGui() {
      this.n.clear();
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      if (this.customLoadingScreen != null) {
         this.customLoadingScreen.drawBackground(this.l, this.m);
      } else {
         this.c(0);
      }

      this.drawCenteredString(this.q, I18n.format("multiplayer.downloadingTerrain"), this.l / 2, this.m / 2 - 50, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public boolean b_() {
      return false;
   }

   @Override
   public void keyTyped(char var1, int var2) {
   }

   public GuiDownloadTerrain(NetHandlerPlayClient var1) {
      this.netHandlerPlayClient = var1;
   }
}
