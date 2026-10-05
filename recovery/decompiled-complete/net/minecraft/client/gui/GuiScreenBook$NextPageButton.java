package net.minecraft.client.gui;

import io.netty.handler.codec.socks.SocksInitRequestDecoder$1;
import junit.framework.Assert;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.optifine.gui.GuiScreenOF;
import net.optifine.util.LockCounter;

public class GuiScreenBook$NextPageButton extends GuiButton {
   public LockCounter field_0002;
   public SocksInitRequestDecoder$1 field_0004;
   public GuiScreenOF field_0001;
   public Assert field_0003;
   public boolean field_146151_o;

   public GuiScreenBook$NextPageButton(int var1, int var2, int var3, boolean var4) {
      super(var1, var2, var3, 23, 13, "");
      this.field_146151_o = var4;
   }

   @Override
   public void drawButton(Minecraft var1, int var2, int var3) {
      if (this.m) {
         boolean var4 = var2 >= this.h && var3 >= this.i && var2 < this.h + this.f && var3 < this.i + this.height;
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         var1.getTextureManager().bindTexture(GuiScreenBook.access$000());
         byte var5 = 0;
         short var6 = 192;
         if (var4) {
            var5 += 23;
         }

         if (!this.field_146151_o) {
            var6 += 13;
         }

         this.drawTexturedModalRect(this.h, this.i, var5, var6, 23, 13);
      }
   }
}
