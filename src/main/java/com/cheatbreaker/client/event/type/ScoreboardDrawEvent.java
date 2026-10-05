package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.client.gui.ScaledResolution;

public class ScoreboardDrawEvent extends EventBus$Event {
   public ScaledResolution recoveredField1249;

   public ScoreboardDrawEvent(ScaledResolution var1) {
      this.recoveredField1249 = var1;
   }

   public ScaledResolution method_12440() {
      return this.recoveredField1249;
   }
}
