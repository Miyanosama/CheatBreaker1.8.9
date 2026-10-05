package net.minecraft.client.gui;

import io.netty.channel.CombinedChannelDuplexHandler;
import net.minecraft.client.multiplayer.WorldClient$1;
import net.minecraft.client.resources.I18n;

public class GuiKeyBindingList$CategoryEntry implements GuiListExtended$IGuiListEntry {
   public String labelText;
   public int labelWidth;
   public WorldClient$1 field_0003;
   public CombinedChannelDuplexHandler field_0000;

   @Override
   public void setSelected(int var1, int var2, int var3) {
   }

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      return false;
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
   }

   public GuiKeyBindingList$CategoryEntry(GuiKeyBindingList var1, String var2) {
      this.field_148287_a = var1;
      super();
      this.labelText = I18n.format(var2);
      this.labelWidth = GuiKeyBindingList.access$100(var1).fontRendererObj.getStringWidth(this.labelText);
   }

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      GuiKeyBindingList.access$100(this.field_148287_a)
         .fontRendererObj
         .drawString(
            this.labelText,
            GuiKeyBindingList.access$100(this.field_148287_a).currentScreen.l / 2 - this.labelWidth / 2,
            var3 + var5 - GuiKeyBindingList.access$100(this.field_148287_a).fontRendererObj.FONT_HEIGHT - 1,
            16777215
         );
   }
}
