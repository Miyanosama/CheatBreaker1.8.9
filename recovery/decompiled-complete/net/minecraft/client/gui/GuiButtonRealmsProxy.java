package net.minecraft.client.gui;

import io.netty.channel.ChannelOutboundBuffer$Entry;
import io.netty.handler.codec.EncoderException;
import net.minecraft.client.Minecraft;
import net.minecraft.realms.RealmsButton;

public class GuiButtonRealmsProxy extends GuiButton {
   public EncoderException field_0001;
   public RealmsButton realmsButton;
   public ChannelOutboundBuffer$Entry field_0000;

   public boolean getEnabled() {
      return super.l;
   }

   public GuiButtonRealmsProxy(RealmsButton var1, int var2, int var3, int var4, String var5) {
      super(var2, var3, var4, var5);
      this.realmsButton = var1;
   }

   @Override
   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      if (super.mousePressed(var1, var2, var3)) {
         this.realmsButton.clicked(var2, var3);
      }

      return super.mousePressed(var1, var2, var3);
   }

   public GuiButtonRealmsProxy(RealmsButton var1, int var2, int var3, int var4, String var5, int var6, int var7) {
      super(var2, var3, var4, var6, var7, var5);
      this.realmsButton = var1;
   }

   @Override
   public int getHoverState(boolean var1) {
      return this.realmsButton.getYImage(var1);
   }

   @Override
   public int method_11184() {
      return this.height;
   }

   @Override
   public void mouseDragged(Minecraft var1, int var2, int var3) {
      this.realmsButton.renderBg(var2, var3);
   }

   @Override
   public void mouseReleased(int var1, int var2) {
      this.realmsButton.released(var1, var2);
   }

   @Override
   public int getButtonWidth() {
      return super.getButtonWidth();
   }

   public int func_154312_c(boolean var1) {
      return super.getHoverState(var1);
   }

   public void setEnabled(boolean var1) {
      super.l = var1;
   }

   public int method_27358() {
      return super.k;
   }

   public RealmsButton getRealmsButton() {
      return this.realmsButton;
   }

   public int method_27362() {
      return super.i;
   }

   public void setText(String var1) {
      super.j = var1;
   }
}
