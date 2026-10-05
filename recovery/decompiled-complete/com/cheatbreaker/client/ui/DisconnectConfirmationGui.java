package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import com.cheatbreaker.client.ui.mainmenu.LegacyMainMenu;
import io.netty.channel.AbstractChannel$AbstractUnsafe;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayer$1;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityBreakingFX$SlimeFactory;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class DisconnectConfirmationGui extends AbstractGui {
   public EntityBreakingFX$SlimeFactory field_0002;
   public GradientTextButton field_0008;
   public GradientTextButton field_0001;
   public AbstractChannel$AbstractUnsafe field_0004;
   public boolean field_0005;
   public ColorFade field_0003;
   public TeleportToPlayer$1 field_0006;
   public GradientTextButton field_0007;
   public GuiScreen field_0000;

   @Override
   public void initGui() {
      this.method_11296();
      this.field_0005 = true;
      float var1 = this.getScaledWidth() / 2.0F;
      float var2 = this.getScaledHeight() / 2.0F - 50.0F;
      if (!this.j.isSingleplayer() && CheatBreaker.getInstance().getGlobalSettings().field_0005.method_08908()) {
         this.field_0007.setElementSize(var1 - 75.0F, var2 + 50.0F, 74.0F, 12.0F);
         this.field_0008.setElementSize(var1 + 1.0F, var2 + 50.0F, 74.0F, 12.0F);
      } else {
         this.field_0007.setElementSize(var1 - 75.0F, var2 + 50.0F, 150.0F, 12.0F);
      }

      this.field_0001.setElementSize(var1 - 75.0F, var2 + 64.0F, 150.0F, 12.0F);
      this.field_0001.method_25133();
   }

   public DisconnectConfirmationGui(GuiScreen var1) {
      this.field_0000 = var1;
      this.field_0003 = new ColorFade(-5083961718525323311L & 536889298L, -1, -52429);
      this.field_0001 = new GradientTextButton("Back to Game Menu");
      this.field_0007 = new GradientTextButton(I18n.format("menu.disconnect"));
      this.field_0008 = new GradientTextButton("Reconnect");
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      if (this.field_0001.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(this.field_0000);
      } else if (this.field_0007.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.theWorld.method_05035();
         this.j.loadWorld(null);
         this.j.displayGuiScreen(new LegacyMainMenu());
      } else if (this.field_0008.a_(var1, var2)) {
         if (this.j.currentServerData != null && this.j.theWorld != null) {
            this.j.theWorld.method_05035();
            this.j.loadWorld((WorldClient)null);
         }

         if (this.j.currentServerData != null) {
            this.j.displayGuiScreen(new GuiConnecting(this, this.j, this.j.currentServerData));
         }
      }
   }

   @Override
   public void a_() {
      this.j.entityRenderer.stopUseShader();
   }

   @Override
   public void drawMenu(float var1, float var2) {
      if (this.field_0005 && this.field_0003.method_21210()) {
         this.field_0005 = false;
      } else if (!this.field_0005 && this.field_0003.method_21210()) {
         this.field_0005 = true;
      }

      this.method_11295(this.getScaledWidth(), this.getScaledHeight());
      float var3 = this.getScaledWidth() / 2.0F;
      float var4 = this.getScaledHeight() / 2.0F - 50.0F;
      CheatBreaker.getInstance().field_0039.drawCenteredString("WARNING!", var3, var4, this.field_0003.method_25066(this.field_0005).getRGB());
      CheatBreaker.getInstance().field_0036.drawCenteredString("Are you sure you want to disconnect?", var3, var4 + 15.0F, -1);
      this.field_0001.drawElement(var1, var2, true);
      this.field_0007.drawElement(var1, var2, true);
      if (!this.j.isSingleplayer() && CheatBreaker.getInstance().getGlobalSettings().field_0005.method_08908()) {
         this.field_0008.drawElement(var1, var2, true);
      }
   }

   @Override
   public void onMouseReleased(float var1, float var2, int var3) {
   }
}
