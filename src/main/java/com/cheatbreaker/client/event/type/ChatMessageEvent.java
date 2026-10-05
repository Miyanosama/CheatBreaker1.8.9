package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;

public class ChatMessageEvent extends EventBus$Event {
   public String recoveredField3672;

   public String method_00228() {
      return this.recoveredField3672;
   }

   public ChatMessageEvent(String var1) {
      this.recoveredField3672 = var1;
   }
}
