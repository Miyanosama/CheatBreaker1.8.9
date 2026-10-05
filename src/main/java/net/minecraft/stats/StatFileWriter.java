package net.minecraft.stats;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.TupleIntJsonSerializable;

public class StatFileWriter {
   public Map<StatBase, TupleIntJsonSerializable> a = Maps.newConcurrentMap();

   public <T extends IJsonSerializable> T b(StatBase var1) {
      TupleIntJsonSerializable var2 = this.a.get(var1);
      return var2 != null ? var2.getJsonSerializableValue() : null;
   }

   public int func_150874_c(Achievement var1) {
      if (this.hasAchievementUnlocked(var1)) {
         return 0;
      } else {
         int var2 = 0;

         for (Achievement var3 = var1.parentAchievement; var3 != null && !this.hasAchievementUnlocked(var3); var2++) {
            var3 = var3.parentAchievement;
         }

         return var2;
      }
   }

   public int a(StatBase var1) {
      TupleIntJsonSerializable var2 = this.a.get(var1);
      return var2 == null ? 0 : var2.getIntegerValue();
   }

   public void unlockAchievement(EntityPlayer var1, StatBase var2, int var3) {
      TupleIntJsonSerializable var4 = this.a.get(var2);
      if (var4 == null) {
         var4 = new TupleIntJsonSerializable();
         this.a.put(var2, var4);
      }

      var4.setIntegerValue(var3);
   }

   public boolean hasAchievementUnlocked(Achievement var1) {
      return this.a(var1) > 0;
   }

   public void increaseStat(EntityPlayer var1, StatBase var2, int var3) {
      if (!var2.isAchievement() || this.canUnlockAchievement((Achievement)var2)) {
         this.unlockAchievement(var1, var2, this.a(var2) + var3);
      }
   }

   public boolean canUnlockAchievement(Achievement var1) {
      return var1.parentAchievement == null || this.hasAchievementUnlocked(var1.parentAchievement);
   }

   public <T extends IJsonSerializable> T a(StatBase var1, T var2) {
      TupleIntJsonSerializable var3 = this.a.get(var1);
      if (var3 == null) {
         var3 = new TupleIntJsonSerializable();
         this.a.put(var1, var3);
      }

      var3.setJsonSerializableValue(var2);
      return (T)var2;
   }
}
