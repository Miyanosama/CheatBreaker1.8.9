package net.minecraft.client.audio;

import com.cheatbreaker.client.ui.overlay.element.RadioStationElement;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.MapPopulator;

public enum ISound$AttenuationType {
   LINEAR(2),
   NONE(0);

   public MapPopulator field_0002;
   public int type;
   public EntityTameable field_0001;
   public RadioStationElement field_0006;

   public int getTypeInt() {
      return this.type;
   }

   public ISound$AttenuationType(int var3) {
      this.type = var3;
   }
}
