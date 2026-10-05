package net.minecraft.client.gui.achievement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.network.PacketThreadUtil$1;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;

public class GuiStats$StatsGeneral extends GuiSlot {
   public PacketThreadUtil$1 field_0000;

   @Override
   public int getSize() {
      return StatList.generalStats.size();
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
   }

   @Override
   public void drawBackground() {
      this.field_148208_k.drawDefaultBackground();
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      StatBase var7 = StatList.generalStats.get(var1);
      this.field_148208_k
         .drawString(GuiStats.access$000(this.field_148208_k), var7.getStatName().getUnformattedText(), var2 + 2, var3 + 1, var1 % 2 == 0 ? 16777215 : 9474192);
      String var8 = var7.format(GuiStats.access$100(this.field_148208_k).a(var7));
      this.field_148208_k
         .drawString(
            GuiStats.access$200(this.field_148208_k),
            var8,
            var2 + 2 + 213 - GuiStats.access$300(this.field_148208_k).getStringWidth(var8),
            var3 + 1,
            var1 % 2 == 0 ? 16777215 : 9474192
         );
   }

   @Override
   public boolean isSelected(int var1) {
      return false;
   }

   public GuiStats$StatsGeneral(GuiStats var1, Minecraft var2) {
      this.field_148208_k = var1;
      super(var2, var1.l, var1.m, 32, var1.m - 64, 10);
      this.setShowSelectionBox(false);
   }

   @Override
   public int getContentHeight() {
      return this.getSize() * 10;
   }
}
