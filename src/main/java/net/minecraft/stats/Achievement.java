package net.minecraft.stats;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.StatCollector;

public class Achievement extends StatBase {
   public int displayColumn;
   public IStatStringFormat statStringFormatter;
   public ItemStack theItemStack;
   public String achievementDescription;
   public Achievement parentAchievement;
   public boolean isSpecial;
   public int displayRow;

   @Override
   public boolean isAchievement() {
      return true;
   }

   public Achievement(String var1, String var2, int var3, int var4, Item var5, Achievement var6) {
      this(var1, var2, var3, var4, new ItemStack(var5), var6);
   }

   public Achievement setStatStringFormatter(IStatStringFormat var1) {
      this.statStringFormatter = var1;
      return this;
   }

   public Achievement initIndependentStat() {
      this.isIndependent = true;
      return this;
   }

   public Achievement(String var1, String var2, int var3, int var4, ItemStack var5, Achievement var6) {
      super(var1, new ChatComponentTranslation("achievement." + var2));
      this.theItemStack = var5;
      this.achievementDescription = "achievement." + var2 + ".desc";
      this.displayColumn = var3;
      this.displayRow = var4;
      if (var3 < AchievementList.minDisplayColumn) {
         AchievementList.minDisplayColumn = var3;
      }

      if (var4 < AchievementList.minDisplayRow) {
         AchievementList.minDisplayRow = var4;
      }

      if (var3 > AchievementList.maxDisplayColumn) {
         AchievementList.maxDisplayColumn = var3;
      }

      if (var4 > AchievementList.maxDisplayRow) {
         AchievementList.maxDisplayRow = var4;
      }

      this.parentAchievement = var6;
   }

   public Achievement setSpecial() {
      this.isSpecial = true;
      return this;
   }

   public Achievement registerStat() {
      super.registerStat();
      AchievementList.achievementList.add(this);
      return this;
   }

   public Achievement func_150953_b(Class<? extends IJsonSerializable> var1) {
      return (Achievement)super.func_150953_b(var1);
   }

   @Override
   public IChatComponent getStatName() {
      IChatComponent var1 = super.getStatName();
      var1.getChatStyle().setColor(this.getSpecial() ? EnumChatFormatting.DARK_PURPLE : EnumChatFormatting.GREEN);
      return var1;
   }

   public Achievement(String var1, String var2, int var3, int var4, Block var5, Achievement var6) {
      this(var1, var2, var3, var4, new ItemStack(var5), var6);
   }

   public boolean getSpecial() {
      return this.isSpecial;
   }

   public String getDescription() {
      return this.statStringFormatter != null
         ? this.statStringFormatter.formatString(StatCollector.translateToLocal(this.achievementDescription))
         : StatCollector.translateToLocal(this.achievementDescription);
   }
}
