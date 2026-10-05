package org.apache.log4j.helpers;

import net.minecraft.item.ItemLead;
import net.minecraft.item.crafting.RecipesTools;

public class FormattingInfo {
   public int min = -1;
   public ItemLead field_0004;
   public boolean leftAlign;
   public int max = Integer.MAX_VALUE;
   public RecipesTools field_0000;

   public FormattingInfo() {
      this.leftAlign = false;
   }

   public void reset() {
      this.min = -1;
      this.max = Integer.MAX_VALUE;
      this.leftAlign = false;
   }

   public void dump() {
      LogLog.debug("min=" + this.min + ", max=" + this.max + ", leftAlign=" + this.leftAlign);
   }
}
