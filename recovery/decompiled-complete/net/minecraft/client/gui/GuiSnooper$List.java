package net.minecraft.client.gui;

import com.cheatbreaker.client.ui.overlay.Alert;
import io.netty.channel.DefaultChannelHandlerContext;
import net.minecraft.potion.Potion;

public class GuiSnooper$List extends GuiSlot {
   public Potion field_0001;
   public Alert field_0000;
   public DefaultChannelHandlerContext field_0002;

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_148206_k.q.drawString((String)GuiSnooper.access$000(this.field_148206_k).get(var1), 10, var3, 16777215);
      this.field_148206_k.q.drawString((String)GuiSnooper.access$100(this.field_148206_k).get(var1), 230, var3, 16777215);
   }

   @Override
   public boolean isSelected(int var1) {
      return false;
   }

   @Override
   public int getSize() {
      return GuiSnooper.access$000(this.field_148206_k).size();
   }

   @Override
   public int getScrollBarX() {
      return this.b - 10;
   }

   public GuiSnooper$List(GuiSnooper var1) {
      this.field_148206_k = var1;
      super(var1.j, var1.l, var1.m, 80, var1.m - 40, var1.q.FONT_HEIGHT + 1);
   }

   @Override
   public void drawBackground() {
   }
}
