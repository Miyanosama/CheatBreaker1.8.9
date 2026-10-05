package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;
import com.cheatbreaker.client.nethandler.CBOutboundChannel;

public class RenderWorldEvent extends EventBus$Event {
   public float field_0001;
   public CBOutboundChannel field_0000;

   public float method_00120() {
      return this.field_0001;
   }

   public RenderWorldEvent(float var1) {
      this.field_0001 = var1;
   }
}
