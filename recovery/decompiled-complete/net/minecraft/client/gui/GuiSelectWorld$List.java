package net.minecraft.client.gui;

import java.util.Date;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Bootstrap$1;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.storage.SaveFormatComparator;
import org.apache.commons.lang3.StringUtils;

public class GuiSelectWorld$List extends GuiSlot {
   public Bootstrap$1 field_0001;

   @Override
   public int getSize() {
      return GuiSelectWorld.access$000(this.field_148207_k).size();
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      SaveFormatComparator var7 = (SaveFormatComparator)GuiSelectWorld.access$000(this.field_148207_k).get(var1);
      String var8 = var7.getDisplayName();
      if (StringUtils.isEmpty(var8)) {
         var8 = GuiSelectWorld.access$600(this.field_148207_k) + " " + (var1 + 1);
      }

      String var9 = var7.getFileName();
      var9 = var9 + " (" + GuiSelectWorld.access$700(this.field_148207_k).format(new Date(var7.getLastTimePlayed()));
      var9 = var9 + ")";
      String var10 = "";
      if (var7.requiresConversion()) {
         var10 = GuiSelectWorld.access$800(this.field_148207_k) + " " + var10;
      } else {
         var10 = GuiSelectWorld.access$900(this.field_148207_k)[var7.getEnumGameType().getID()];
         if (var7.isHardcoreModeEnabled()) {
            var10 = EnumChatFormatting.DARK_RED + I18n.format("gameMode.hardcore") + EnumChatFormatting.RESET;
         }

         if (var7.getCheatsEnabled()) {
            var10 = var10 + ", " + I18n.format("selectWorld.cheats");
         }
      }

      this.field_148207_k.drawString(this.field_148207_k.q, var8, var2 + 2, var3 + 1, 16777215);
      this.field_148207_k.drawString(this.field_148207_k.q, var9, var2 + 2, var3 + 12, 8421504);
      this.field_148207_k.drawString(this.field_148207_k.q, var10, var2 + 2, var3 + 12 + 10, 8421504);
   }

   @Override
   public int getContentHeight() {
      return GuiSelectWorld.access$000(this.field_148207_k).size() * 36;
   }

   @Override
   public boolean isSelected(int var1) {
      return var1 == GuiSelectWorld.access$100(this.field_148207_k);
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      GuiSelectWorld.access$102(this.field_148207_k, var1);
      boolean var5 = GuiSelectWorld.access$100(this.field_148207_k) >= 0 && GuiSelectWorld.access$100(this.field_148207_k) < this.getSize();
      GuiSelectWorld.access$200(this.field_148207_k).l = var5;
      GuiSelectWorld.access$300(this.field_148207_k).l = var5;
      GuiSelectWorld.access$400(this.field_148207_k).l = var5;
      GuiSelectWorld.access$500(this.field_148207_k).l = var5;
      if (var2 && var5) {
         this.field_148207_k.func_146615_e(var1);
      }
   }

   @Override
   public void drawBackground() {
      this.field_148207_k.drawDefaultBackground();
   }

   public GuiSelectWorld$List(GuiSelectWorld var1, Minecraft var2) {
      this.field_148207_k = var1;
      super(var2, var1.l, var1.m, 32, var1.m - 64, 36);
   }
}
