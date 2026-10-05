package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;

public class PluginMessageEvent extends EventBus$Event {
   public byte[] recoveredField1693;
   public String recoveredField1694;

   public String method_26236() {
      return this.recoveredField1694;
   }

   public byte[] method_26237() {
      return this.recoveredField1693;
   }

   public PluginMessageEvent(String var1, byte[] var2) {
      this.recoveredField1694 = var1;
      this.recoveredField1693 = var2;
   }
}
