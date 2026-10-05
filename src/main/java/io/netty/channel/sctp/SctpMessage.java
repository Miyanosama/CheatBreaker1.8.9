package io.netty.channel.sctp;

import com.sun.nio.sctp.MessageInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker07;
import net.minecraft.client.gui.inventory.GuiBeacon;

public class SctpMessage extends DefaultByteBufHolder {
   public int streamIdentifier;
   public MessageInfo msgInfo;
   public int protocolIdentifier;

   @Override
   public int hashCode() {
      int var1 = this.streamIdentifier;
      var1 = 31 * var1 + this.protocolIdentifier;
      return 31 * var1 + this.content().hashCode();
   }

   public SctpMessage(MessageInfo var1, ByteBuf var2) {
      super(var2);
      if (var1 == null) {
         throw new NullPointerException("msgInfo");
      } else {
         this.msgInfo = var1;
         this.streamIdentifier = var1.streamNumber();
         this.protocolIdentifier = var1.payloadProtocolID();
      }
   }

   public SctpMessage(int var1, int var2, ByteBuf var3) {
      super(var3);
      this.protocolIdentifier = var1;
      this.streamIdentifier = var2;
      this.msgInfo = null;
   }

   public MessageInfo messageInfo() {
      return this.msgInfo;
   }

   public SctpMessage retain() {
      super.retain();
      return this;
   }

   public boolean isComplete() {
      return this.msgInfo != null ? this.msgInfo.isComplete() : true;
   }

   public int protocolIdentifier() {
      return this.protocolIdentifier;
   }

   public SctpMessage duplicate() {
      return this.msgInfo == null
         ? new SctpMessage(this.protocolIdentifier, this.streamIdentifier, this.content().duplicate())
         : new SctpMessage(this.msgInfo, this.content().copy());
   }

   public SctpMessage copy() {
      return this.msgInfo == null
         ? new SctpMessage(this.protocolIdentifier, this.streamIdentifier, this.content().copy())
         : new SctpMessage(this.msgInfo, this.content().copy());
   }

   public int streamIdentifier() {
      return this.streamIdentifier;
   }

   public SctpMessage retain(int var1) {
      super.retain(var1);
      return this;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         SctpMessage var2 = (SctpMessage)var1;
         if (this.protocolIdentifier != var2.protocolIdentifier) {
            return false;
         } else {
            return this.streamIdentifier != var2.streamIdentifier ? false : this.content().equals(var2.content());
         }
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.refCnt() == 0
         ? "SctpFrame{streamIdentifier=" + this.streamIdentifier + ", protocolIdentifier=" + this.protocolIdentifier + ", data=(FREED)}"
         : "SctpFrame{streamIdentifier="
            + this.streamIdentifier
            + ", protocolIdentifier="
            + this.protocolIdentifier
            + ", data="
            + ByteBufUtil.hexDump(this.content())
            + '}';
   }
}
