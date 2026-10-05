package net.minecraft.event;

import net.minecraft.client.gui.GuiCreateFlatWorld$Details;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ReportedException;
import net.optifine.GlErrors;

public class HoverEvent {
   public IChatComponent value;
   public HoverEvent$Action action;
   public GlErrors field_0001;
   public GuiCreateFlatWorld$Details field_0003;
   public ReportedException field_0000;

   @Override
   public int hashCode() {
      int var1 = this.action.hashCode();
      return 31 * var1 + (this.value != null ? this.value.hashCode() : 0);
   }

   public HoverEvent$Action getAction() {
      return this.action;
   }

   @Override
   public String toString() {
      return "HoverEvent{action=" + this.action + ", value='" + this.value + '\'' + '}';
   }

   public HoverEvent(HoverEvent$Action var1, IChatComponent var2) {
      this.action = var1;
      this.value = var2;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         HoverEvent var2 = (HoverEvent)var1;
         if (this.action != var2.action) {
            return false;
         } else {
            if (this.value != null) {
               if (!this.value.equals(var2.value)) {
                  return false;
               }
            } else if (var2.value != null) {
               return false;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public IChatComponent getValue() {
      return this.value;
   }
}
