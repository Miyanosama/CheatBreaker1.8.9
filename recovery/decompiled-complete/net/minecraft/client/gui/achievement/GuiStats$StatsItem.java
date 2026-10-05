package net.minecraft.client.gui.achievement;

import com.google.common.collect.Lists;
import io.netty.util.internal.chmv8.CountedCompleter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.Item;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;
import net.optifine.CustomGuiProperties;

public class GuiStats$StatsItem extends GuiStats$Stats {
   public CustomGuiProperties field_0002;
   public CountedCompleter field_0000;

   public GuiStats$StatsItem(GuiStats var1, Minecraft var2) {
      this.field_148220_k = var1;
      super(var1, var2);
      this.w = Lists.newArrayList();

      for (StatCrafting var4 : StatList.itemStats) {
         boolean var5 = false;
         int var6 = Item.getIdFromItem(var4.func_150959_a());
         if (GuiStats.access$100(var1).a(var4) > 0) {
            var5 = true;
         } else if (StatList.objectBreakStats[var6] != null && GuiStats.access$100(var1).a(StatList.objectBreakStats[var6]) > 0) {
            var5 = true;
         } else if (StatList.objectCraftStats[var6] != null && GuiStats.access$100(var1).a(StatList.objectCraftStats[var6]) > 0) {
            var5 = true;
         }

         if (var5) {
            this.w.add(var4);
         }
      }

      this.x = new GuiStats$StatsItem$1(this, var1);
   }

   @Override
   public String func_148210_b(int var1) {
      return var1 == 1 ? "stat.crafted" : (var1 == 2 ? "stat.used" : "stat.depleted");
   }

   @Override
   public void drawListHeader(int var1, int var2, Tessellator var3) {
      super.drawListHeader(var1, var2, var3);
      if (this.v == 0) {
         GuiStats.access$400(this.field_148220_k, var1 + 115 - 18 + 1, var2 + 1 + 1, 72, 18);
      } else {
         GuiStats.access$400(this.field_148220_k, var1 + 115 - 18, var2 + 1, 72, 18);
      }

      if (this.v == 1) {
         GuiStats.access$400(this.field_148220_k, var1 + 165 - 18 + 1, var2 + 1 + 1, 18, 18);
      } else {
         GuiStats.access$400(this.field_148220_k, var1 + 165 - 18, var2 + 1, 18, 18);
      }

      if (this.v == 2) {
         GuiStats.access$400(this.field_148220_k, var1 + 215 - 18 + 1, var2 + 1 + 1, 36, 18);
      } else {
         GuiStats.access$400(this.field_148220_k, var1 + 215 - 18, var2 + 1, 36, 18);
      }
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      StatCrafting var7 = this.c(var1);
      Item var8 = var7.func_150959_a();
      GuiStats.access$1500(this.field_148220_k, var2 + 40, var3, var8);
      int var9 = Item.getIdFromItem(var8);
      this.func_148209_a(StatList.objectBreakStats[var9], var2 + 115, var3, var1 % 2 == 0);
      this.func_148209_a(StatList.objectCraftStats[var9], var2 + 165, var3, var1 % 2 == 0);
      this.func_148209_a(var7, var2 + 215, var3, var1 % 2 == 0);
   }
}
