package net.minecraft.stats;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.event.HoverEvent;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IJsonSerializable;

public class StatBase {
   public boolean isIndependent;
   public static NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.US);
   public Class<? extends IJsonSerializable> field_150956_d;
   public static IStatType simpleStatType = new IStatType() {
      @Override
      public String format(int var1) {
         return StatBase.numberFormat.format((long)var1);
      }
   };
   public static DecimalFormat decimalFormat = new DecimalFormat("########0.00");
   public IStatType type;
   public IChatComponent statName;
   public static IStatType timeStatType = new IStatType() {
      @Override
      public String format(int var1) {
         double var2 = var1 / 20.0;
         double var4 = var2 / 60.0;
         double var6 = var4 / 60.0;
         double var8 = var6 / 24.0;
         double var10 = var8 / 365.0;
         return var10 > 0.5
            ? StatBase.decimalFormat.format(var10) + " y"
            : (
               var8 > 0.5
                  ? StatBase.decimalFormat.format(var8) + " d"
                  : (var6 > 0.5 ? StatBase.decimalFormat.format(var6) + " h" : (var4 > 0.5 ? StatBase.decimalFormat.format(var4) + " m" : var2 + " s"))
            );
      }
   };
   public static IStatType distanceStatType = new IStatType() {
      @Override
      public String format(int var1) {
         double var2 = var1 / 100.0;
         double var4 = var2 / 1000.0;
         return var4 > 0.5 ? StatBase.decimalFormat.format(var4) + " km" : (var2 > 0.5 ? StatBase.decimalFormat.format(var2) + " m" : var1 + " cm");
      }
   };
   public IScoreObjectiveCriteria objectiveCriteria;
   public static IStatType field_111202_k = new IStatType() {
      @Override
      public String format(int var1) {
         return StatBase.decimalFormat.format(var1 * 0.1);
      }
   };
   public String statId;

   @Override
   public int hashCode() {
      return this.statId.hashCode();
   }

   public String format(int var1) {
      return this.type.format(var1);
   }

   public StatBase initIndependentStat() {
      this.isIndependent = true;
      return this;
   }

   public IScoreObjectiveCriteria getCriteria() {
      return this.objectiveCriteria;
   }

   public Class<? extends IJsonSerializable> func_150954_l() {
      return this.field_150956_d;
   }

   public StatBase(String var1, IChatComponent var2, IStatType var3) {
      this.statId = var1;
      this.statName = var2;
      this.type = var3;
      this.objectiveCriteria = new ObjectiveStat(this);
      IScoreObjectiveCriteria.INSTANCES.put(this.objectiveCriteria.getName(), this.objectiveCriteria);
   }

   public IChatComponent getStatName() {
      IChatComponent var1 = this.statName.createCopy();
      var1.getChatStyle().setColor(EnumChatFormatting.GRAY);
      var1.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ACHIEVEMENT, new ChatComponentText(this.statId)));
      return var1;
   }

   public IChatComponent createChatComponent() {
      IChatComponent var1 = this.getStatName();
      IChatComponent var2 = new ChatComponentText("[").appendSibling(var1).appendText("]");
      var2.setChatStyle(var1.getChatStyle());
      return var2;
   }

   public StatBase(String var1, IChatComponent var2) {
      this(var1, var2, simpleStatType);
   }

   public StatBase func_150953_b(Class<? extends IJsonSerializable> var1) {
      this.field_150956_d = var1;
      return this;
   }

   public StatBase registerStat() {
      if (StatList.oneShotStats.containsKey(this.statId)) {
         throw new RuntimeException(
            "Duplicate stat id: \"" + StatList.oneShotStats.get(this.statId).statName + "\" and \"" + this.statName + "\" at id " + this.statId
         );
      } else {
         StatList.allStats.add(this);
         StatList.oneShotStats.put(this.statId, this);
         return this;
      }
   }

   public boolean isAchievement() {
      return false;
   }

   @Override
   public String toString() {
      return "Stat{id="
         + this.statId
         + ", nameId="
         + this.statName
         + ", awardLocallyOnly="
         + this.isIndependent
         + ", formatter="
         + this.type
         + ", objectiveCriteria="
         + this.objectiveCriteria
         + '}';
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         StatBase var2 = (StatBase)var1;
         return this.statId.equals(var2.statId);
      } else {
         return false;
      }
   }
}
