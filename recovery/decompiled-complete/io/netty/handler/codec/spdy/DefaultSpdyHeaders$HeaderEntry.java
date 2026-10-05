package io.netty.handler.codec.spdy;

import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.socks.SocksMessageType;
import java.util.Map.Entry;
import junit.swingui.DefaultFailureDetailView$StackTraceListModel;
import net.minecraft.block.BlockSilverfish$EnumType$3;

public class DefaultSpdyHeaders$HeaderEntry implements Entry<String, String> {
   public int hash;
   public DatagramPacket __junk4263714719603145284;
   public DefaultSpdyHeaders$HeaderEntry next;
   public BlockSilverfish$EnumType$3 __junk8437266013038498775;
   public DefaultFailureDetailView$StackTraceListModel __junk8317202932070310155;
   public String key;
   public String value;
   public DefaultSpdyHeaders$HeaderEntry before;
   public DefaultSpdyHeaders$HeaderEntry after;
   public SocksMessageType __junk5374846101918989136;

   public void remove() {
      this.before.after = this.after;
      this.after.before = this.before;
   }

   public DefaultSpdyHeaders$HeaderEntry(int var1, String var2, String var3) {
      this.hash = var1;
      this.key = var2;
      this.value = var3;
   }

   public void addBefore(DefaultSpdyHeaders$HeaderEntry var1) {
      this.after = var1;
      this.before = var1.before;
      this.before.after = this;
      this.after.before = this;
   }

   public String getValue() {
      return this.value;
   }

   @Override
   public String toString() {
      return this.key + '=' + this.value;
   }

   public String getKey() {
      return this.key;
   }

   public String setValue(String var1) {
      if (var1 == null) {
         throw new NullPointerException("value");
      } else {
         SpdyCodecUtil.validateHeaderValue(var1);
         String var2 = this.value;
         this.value = var1;
         return var2;
      }
   }
}
