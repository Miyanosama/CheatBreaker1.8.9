package io.netty.handler.codec.spdy;

import net.minecraft.command.server.CommandTestFor;
import net.minecraft.dispenser.BehaviorProjectileDispense;

public abstract class DefaultSpdyStreamFrame implements SpdyStreamFrame {
   public BehaviorProjectileDispense __junk9207549595409800975;
   public boolean last;
   public int streamId;
   public CommandTestFor __junk8061770354898921053;

   @Override
   public int streamId() {
      return this.streamId;
   }

   @Override
   public boolean isLast() {
      return this.last;
   }

   @Override
   public SpdyStreamFrame setLast(boolean var1) {
      this.last = var1;
      return this;
   }

   public DefaultSpdyStreamFrame(int var1) {
      this.setStreamId(var1);
   }

   @Override
   public SpdyStreamFrame setStreamId(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Stream-ID must be positive: " + var1);
      } else {
         this.streamId = var1;
         return this;
      }
   }
}
