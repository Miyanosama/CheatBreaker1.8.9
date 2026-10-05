package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.client.multiplayer.WorldClient;

public class LoadWorldEvent extends EventBus$Event {
   public WorldClient recoveredField470;

   public LoadWorldEvent(WorldClient var1) {
      this.recoveredField470 = var1;
   }

   public WorldClient method_29800() {
      return this.recoveredField470;
   }
}
