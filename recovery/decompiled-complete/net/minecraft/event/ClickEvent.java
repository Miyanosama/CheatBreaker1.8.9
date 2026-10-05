package net.minecraft.event;

import net.minecraft.world.gen.structure.StructureStrongholdPieces$RoomCrossing;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditorRenderer;

public class ClickEvent {
   public ClickEvent$Action action;
   public String value;
   public CategoryNodeEditorRenderer field_0000;
   public StructureStrongholdPieces$RoomCrossing field_0002;

   @Override
   public int hashCode() {
      int var1 = this.action.hashCode();
      return 31 * var1 + (this.value != null ? this.value.hashCode() : 0);
   }

   public String getValue() {
      return this.value;
   }

   public ClickEvent$Action getAction() {
      return this.action;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         ClickEvent var2 = (ClickEvent)var1;
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

   public ClickEvent(ClickEvent$Action var1, String var2) {
      this.action = var1;
      this.value = var2;
   }

   @Override
   public String toString() {
      return "ClickEvent{action=" + this.action + ", value='" + this.value + '\'' + '}';
   }
}
