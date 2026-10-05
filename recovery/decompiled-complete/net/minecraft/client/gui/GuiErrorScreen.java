package net.minecraft.client.gui;

import io.netty.handler.codec.spdy.SpdyProtocolException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import net.minecraft.client.renderer.BlockModelRenderer$Orientation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.shader.ShaderLoader$ShaderType;
import net.minecraft.network.play.client.C0CPacketInput;

public class GuiErrorScreen extends GuiScreen {
   public C0CPacketInput field_0003;
   public ShaderLoader$ShaderType field_0005;
   public String field_146312_f;
   public AdvancedPlayer field_0004;
   public BlockModelRenderer$Orientation field_0000;
   public String field_146313_a;
   public SpdyProtocolException field_0006;

   public GuiErrorScreen(String var1, String var2) {
      this.field_146313_a = var1;
      this.field_146312_f = var2;
   }

   @Override
   public void initGui() {
      super.initGui();
      this.n.add(new GuiButton(0, this.l / 2 - 100, 140, I18n.format("gui.cancel")));
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawGradientRect(0, 0, this.l, this.m, -12574688, -11530224);
      this.drawCenteredString(this.q, this.field_146313_a, this.l / 2, 90, 16777215);
      this.drawCenteredString(this.q, this.field_146312_f, this.l / 2, 110, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      this.j.displayGuiScreen((GuiScreen)null);
   }

   @Override
   public void keyTyped(char var1, int var2) {
   }
}
