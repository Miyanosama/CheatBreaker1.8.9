package net.minecraft.client.gui.achievement;

import java.util.Comparator;
import net.minecraft.block.BlockHopper$1;
import net.minecraft.item.Item;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatList;
import net.minecraft.world.gen.layer.IntCache;
import org.apache.log4j.helpers.RelativeTimeDateFormat;

public class GuiStats$StatsBlock$1 implements Comparator<StatCrafting> {
   public IntCache field_0002;
   public BlockHopper$1 field_0004;
   public RelativeTimeDateFormat field_0003;

   public int compare(StatCrafting var1, StatCrafting var2) {
      int var3 = Item.getIdFromItem(var1.func_150959_a());
      int var4 = Item.getIdFromItem(var2.func_150959_a());
      StatBase var5 = null;
      StatBase var6 = null;
      if (this.field_148340_b.y == 2) {
         var5 = StatList.mineBlockStatArray[var3];
         var6 = StatList.mineBlockStatArray[var4];
      } else if (this.field_148340_b.y == 0) {
         var5 = StatList.objectCraftStats[var3];
         var6 = StatList.objectCraftStats[var4];
      } else if (this.field_148340_b.y == 1) {
         var5 = StatList.objectUseStats[var3];
         var6 = StatList.objectUseStats[var4];
      }

      if (var5 != null || var6 != null) {
         if (var5 == null) {
            return 1;
         }

         if (var6 == null) {
            return -1;
         }

         int var7 = GuiStats.access$100(this.field_148340_b.field_148221_k).a(var5);
         int var8 = GuiStats.access$100(this.field_148340_b.field_148221_k).a(var6);
         if (var7 != var8) {
            return (var7 - var8) * this.field_148340_b.z;
         }
      }

      return var3 - var4;
   }

   public GuiStats$StatsBlock$1(GuiStats$StatsBlock var1, GuiStats var2) {
      this.field_148340_b = var1;
      this.field_148341_a = var2;
      super();
   }
}
