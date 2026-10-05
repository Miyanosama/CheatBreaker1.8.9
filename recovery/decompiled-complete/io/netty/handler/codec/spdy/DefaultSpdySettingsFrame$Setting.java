package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.websocket.client.WSPacketClientJoinServerResponse;
import net.minecraft.client.renderer.block.model.ItemModelGenerator$Span;

public class DefaultSpdySettingsFrame$Setting {
   public int value;
   public boolean persist;
   public boolean persisted;
   public ItemModelGenerator$Span __junk7608123209706432576;
   public WSPacketClientJoinServerResponse __junk6062784042221230254;

   public void setPersisted(boolean var1) {
      this.persisted = var1;
   }

   public boolean isPersisted() {
      return this.persisted;
   }

   public DefaultSpdySettingsFrame$Setting(int var1, boolean var2, boolean var3) {
      this.value = var1;
      this.persist = var2;
      this.persisted = var3;
   }

   public boolean isPersist() {
      return this.persist;
   }

   public int getValue() {
      return this.value;
   }

   public void setValue(int var1) {
      this.value = var1;
   }

   public void setPersist(boolean var1) {
      this.persist = var1;
   }
}
